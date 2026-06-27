import java.util.*;

/**
 * Day 02 Mini-Project: Task Scheduler
 *
 * A simple task scheduler that uses:
 *   - PriorityQueue for priority-based scheduling
 *   - TreeMap for scheduling tasks by time slot
 *   - ArrayDeque for a waiting queue
 *   - LinkedList for completed task history
 *
 * Run: java MiniProject
 */
public class MiniProject {

    enum Priority { LOW, MEDIUM, HIGH, CRITICAL }

    static class Task implements Comparable<Task> {
        private static int idCounter = 1;

        final int    id;
        final String name;
        final Priority priority;
        final int    estimatedMinutes;
        String status;

        Task(String name, Priority priority, int estimatedMinutes) {
            this.id                = idCounter++;
            this.name              = name;
            this.priority          = priority;
            this.estimatedMinutes  = estimatedMinutes;
            this.status            = "PENDING";
        }

        @Override
        public int compareTo(Task other) {
            // Higher priority enum ordinal = higher priority = process first
            return other.priority.ordinal() - this.priority.ordinal();
        }

        @Override
        public String toString() {
            return String.format("[T%02d|%-8s|%-8s|%3dmin]",
                id, priority, status, estimatedMinutes);
        }
    }

    // =========================================================
    // TaskScheduler — core class
    // =========================================================
    static class TaskScheduler {
        // Ready-to-run tasks, highest priority first
        private final PriorityQueue<Task> readyQueue = new PriorityQueue<>();

        // Tasks scheduled for a specific time slot (minute offset from now)
        private final TreeMap<Integer, Task> scheduledTasks = new TreeMap<>();

        // Waiting because a dependency hasn't finished
        private final Deque<Task> waitingQueue = new ArrayDeque<>();

        // Completed tasks (most recent first = stack behavior)
        private final LinkedList<Task> completedHistory = new LinkedList<>();

        private int currentTime = 0;  // simulated clock in minutes

        public void addReady(Task task) {
            readyQueue.offer(task);
            System.out.println("  ADDED to ready: " + task.name + " [" + task.priority + "]");
        }

        public void scheduleAt(int minuteOffset, Task task) {
            scheduledTasks.put(minuteOffset, task);
            System.out.println("  SCHEDULED at T+" + minuteOffset + "min: " + task.name);
        }

        public void addWaiting(Task task) {
            task.status = "WAITING";
            waitingQueue.offer(task);
            System.out.println("  WAITING: " + task.name);
        }

        /** Advance time and move scheduled tasks into ready queue */
        public void tick(int minutes) {
            currentTime += minutes;
            System.out.println("\n  [Clock T+" + currentTime + "min]");

            // Move all tasks scheduled <= currentTime into readyQueue
            NavigableMap<Integer, Task> due = scheduledTasks.headMap(currentTime, true);
            for (Map.Entry<Integer, Task> e : due.entrySet()) {
                Task t = e.getValue();
                t.status = "PENDING";
                readyQueue.offer(t);
                System.out.println("  SCHEDULED task now ready: " + t.name);
            }
            due.clear();
        }

        /** Process the next highest-priority task */
        public Task processNext() {
            if (readyQueue.isEmpty()) {
                System.out.println("  No tasks ready.");
                return null;
            }
            Task t = readyQueue.poll();
            t.status = "DONE";
            completedHistory.addFirst(t);   // most-recent at head
            System.out.println("  PROCESSING: " + t.name +
                " (" + t.priority + ", " + t.estimatedMinutes + "min)");
            return t;
        }

        /** Release a waiting task (dependency resolved) */
        public void releaseWaiting() {
            Task t = waitingQueue.poll();
            if (t != null) {
                t.status = "PENDING";
                readyQueue.offer(t);
                System.out.println("  RELEASED from waiting: " + t.name);
            }
        }

        public void printStatus() {
            System.out.println("\n  === Scheduler Status ===");
            System.out.println("  Time: T+" + currentTime + "min");
            System.out.println("  Ready queue size:   " + readyQueue.size());
            System.out.println("  Scheduled (future): " + scheduledTasks.size());
            System.out.println("  Waiting:            " + waitingQueue.size());
            System.out.println("  Completed:          " + completedHistory.size());
        }

        public void printCompletedHistory() {
            System.out.println("\n  === Completed Tasks (most recent first) ===");
            for (Task t : completedHistory) {
                System.out.println("    " + t);
            }
        }

        public void printScheduled() {
            System.out.println("\n  === Future Scheduled Tasks ===");
            for (Map.Entry<Integer, Task> e : scheduledTasks.entrySet()) {
                System.out.println("    T+" + e.getKey() + "min: " + e.getValue().name);
            }
        }

        /** Next task scheduled (TreeMap.firstKey) */
        public void printNextScheduled() {
            if (scheduledTasks.isEmpty()) {
                System.out.println("  No future tasks scheduled.");
            } else {
                int nextTime = scheduledTasks.firstKey();
                System.out.println("  Next scheduled: T+" + nextTime + "min — "
                    + scheduledTasks.get(nextTime).name);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("===== MINI-PROJECT: Task Scheduler =====\n");

        TaskScheduler scheduler = new TaskScheduler();

        // --- Setup tasks ---
        System.out.println("--- Adding tasks ---");
        Task deploy   = new Task("Deploy to production", Priority.CRITICAL, 30);
        Task bugfix   = new Task("Fix login bug",        Priority.HIGH,     45);
        Task docs     = new Task("Update documentation", Priority.LOW,      60);
        Task backup   = new Task("Run database backup",  Priority.MEDIUM,   20);
        Task email    = new Task("Send status email",    Priority.LOW,      10);
        Task hotfix   = new Task("Apply security patch", Priority.CRITICAL, 15);
        Task meeting  = new Task("Team standup",         Priority.MEDIUM,   30);

        // Some are ready now
        scheduler.addReady(bugfix);
        scheduler.addReady(backup);
        scheduler.addReady(email);

        // Some are scheduled for the future
        scheduler.scheduleAt(30, deploy);
        scheduler.scheduleAt(60, hotfix);

        // Some are waiting for a dependency
        scheduler.addWaiting(docs);
        scheduler.addWaiting(meeting);

        scheduler.printStatus();
        scheduler.printScheduled();
        scheduler.printNextScheduled();

        // --- Simulate processing ---
        System.out.println("\n--- Processing cycle 1 (T+0) ---");
        scheduler.processNext();  // CRITICAL? No, highest is HIGH (bugfix)
        scheduler.processNext();  // MEDIUM (backup)

        // Advance time 30 minutes
        scheduler.tick(30);
        System.out.println("--- Processing cycle 2 (T+30) ---");
        scheduler.processNext();  // deploy (CRITICAL, just became ready)
        scheduler.processNext();  // email (LOW)

        // Release a waiting task (pretend dependency is done)
        System.out.println("\n--- Releasing a waiting task ---");
        scheduler.releaseWaiting();

        // Advance time
        scheduler.tick(30);
        System.out.println("--- Processing cycle 3 (T+60) ---");
        scheduler.processNext();  // hotfix (CRITICAL, just scheduled)
        scheduler.processNext();  // docs (just released from waiting)
        scheduler.processNext();  // nothing

        scheduler.printStatus();
        scheduler.printCompletedHistory();

        // TreeMap demo: find tasks scheduled in next N minutes
        System.out.println("\n--- Demonstrating TreeMap range query ---");
        // Re-add for demonstration
        TaskScheduler s2 = new TaskScheduler();
        s2.scheduleAt(10,  new Task("Task A", Priority.LOW,  5));
        s2.scheduleAt(25,  new Task("Task B", Priority.MEDIUM, 10));
        s2.scheduleAt(50,  new Task("Task C", Priority.HIGH, 15));
        s2.scheduleAt(90,  new Task("Task D", Priority.LOW, 30));
        System.out.println("All future tasks:");
        s2.printScheduled();
        System.out.println("Tasks due within 30 minutes:");
        s2.scheduledTasks.headMap(30, true)
            .forEach((t, task) -> System.out.println("  T+" + t + ": " + task.name));

        System.out.println("\n===== Mini-Project Complete! =====");
    }
}
