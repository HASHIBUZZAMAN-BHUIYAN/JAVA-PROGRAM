import java.util.*;

/**
 * Day 02 Lesson: Advanced Collections
 *
 * Java's Collections Framework offers many specialized implementations.
 * Choosing the right one matters for performance and correctness.
 *
 * Run: java Lesson
 */
public class Lesson {

    // =========================================================
    // SECTION 1: LinkedList — doubly-linked list / Deque
    // =========================================================
    static void linkedListDemo() {
        System.out.println("--- LinkedList ---");

        // LinkedList implements both List and Deque
        LinkedList<String> list = new LinkedList<>();

        // Add at tail (like ArrayList)
        list.add("B");
        list.add("C");

        // O(1) add at head — this is where LinkedList shines over ArrayList
        list.addFirst("A");
        list.addLast("D");

        System.out.println("List: " + list);              // [A, B, C, D]
        System.out.println("First: " + list.getFirst());  // A
        System.out.println("Last:  " + list.getLast());   // D

        // Remove from either end — O(1)
        System.out.println("removeFirst: " + list.removeFirst()); // A
        System.out.println("removeLast:  " + list.removeLast());  // D
        System.out.println("After removes: " + list);             // [B, C]

        // Used as a Queue (FIFO)
        Queue<String> queue = new LinkedList<>();
        queue.offer("task1");
        queue.offer("task2");
        queue.offer("task3");
        System.out.println("Queue poll: " + queue.poll());  // task1
        System.out.println("Queue peek: " + queue.peek());  // task2

        // Performance note: LinkedList random access is O(n), not O(1) like ArrayList
        // So list.get(500) on a 1000-element list traverses 500 nodes!
    }

    // =========================================================
    // SECTION 2: TreeSet — sorted unique elements
    // =========================================================
    static void treeSetDemo() {
        System.out.println("\n--- TreeSet ---");

        // TreeSet keeps elements sorted (natural order = Comparable)
        TreeSet<Integer> ts = new TreeSet<>(List.of(5, 2, 8, 1, 9, 3, 7, 4, 6));
        System.out.println("TreeSet: " + ts);           // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        // Powerful range/navigation methods (NavigableSet)
        System.out.println("first():    " + ts.first());           // 1
        System.out.println("last():     " + ts.last());            // 9
        System.out.println("floor(5):   " + ts.floor(5));          // 5  (<=5)
        System.out.println("ceiling(5): " + ts.ceiling(5));        // 5  (>=5)
        System.out.println("lower(5):   " + ts.lower(5));          // 4  (<5)
        System.out.println("higher(5):  " + ts.higher(5));         // 6  (>5)
        System.out.println("headSet(5): " + ts.headSet(5));        // [1,2,3,4] (< 5)
        System.out.println("tailSet(5): " + ts.tailSet(5));        // [5,6,7,8,9] (>= 5)
        System.out.println("subSet(3,7):" + ts.subSet(3, 7));      // [3,4,5,6] (3<=x<7)

        // Custom Comparator — reverse order
        TreeSet<String> rev = new TreeSet<>(Comparator.reverseOrder());
        rev.addAll(List.of("banana", "apple", "cherry", "date"));
        System.out.println("Reversed: " + rev); // [date, cherry, banana, apple]
    }

    // =========================================================
    // SECTION 3: TreeMap — sorted key-value pairs
    // =========================================================
    static void treeMapDemo() {
        System.out.println("\n--- TreeMap ---");

        // Keys always sorted (natural order)
        TreeMap<String, Integer> scores = new TreeMap<>();
        scores.put("Charlie", 85);
        scores.put("Alice",   92);
        scores.put("Bob",     78);
        scores.put("Diana",   95);
        scores.put("Eve",     88);

        System.out.println("All (sorted by name): " + scores);
        System.out.println("firstKey():   " + scores.firstKey());         // Alice
        System.out.println("lastKey():    " + scores.lastKey());          // Eve
        System.out.println("floorKey(C):  " + scores.floorKey("C"));     // Charlie
        System.out.println("ceilingKey(B):" + scores.ceilingKey("B"));   // Bob
        System.out.println("headMap(C):   " + scores.headMap("C"));      // {Alice=92, Bob=78}
        System.out.println("tailMap(D):   " + scores.tailMap("D"));      // {Diana=95, Eve=88}
        System.out.println("subMap(B,D):  " + scores.subMap("B", "D")); // {Bob=78, Charlie=85}

        // First/last entries
        System.out.println("firstEntry(): " + scores.firstEntry()); // Alice=92
        System.out.println("lastEntry():  " + scores.lastEntry());  // Eve=88
    }

    // =========================================================
    // SECTION 4: ArrayDeque — preferred stack/queue implementation
    // =========================================================
    static void arrayDequeDemo() {
        System.out.println("\n--- ArrayDeque ---");

        // As a Stack (LIFO) — faster than java.util.Stack (which is synchronized)
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);  // same as addFirst
        stack.push(2);
        stack.push(3);
        System.out.println("Stack (top first): " + stack);   // [3, 2, 1]
        System.out.println("pop: " + stack.pop());            // 3
        System.out.println("peek: " + stack.peek());          // 2

        // As a Queue (FIFO) — use offer/poll/peek
        Deque<String> queue = new ArrayDeque<>();
        queue.offer("first");   // same as addLast
        queue.offer("second");
        queue.offer("third");
        System.out.println("Queue: " + queue);
        System.out.println("poll: " + queue.poll());   // first
        System.out.println("peek: " + queue.peek());   // second

        // As a Deque — add/remove from both ends
        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst("B");
        deque.addFirst("A");
        deque.addLast("C");
        deque.addLast("D");
        System.out.println("Deque: " + deque);             // [A, B, C, D]
        System.out.println("pollFirst: " + deque.pollFirst()); // A
        System.out.println("pollLast:  " + deque.pollLast());  // D
    }

    // =========================================================
    // SECTION 5: PriorityQueue — min-heap
    // =========================================================
    static void priorityQueueDemo() {
        System.out.println("\n--- PriorityQueue ---");

        // Default: min-heap (smallest element at head)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.addAll(List.of(5, 1, 8, 3, 7, 2));
        System.out.print("Min-heap poll order: ");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");
        }
        System.out.println();  // 1 2 3 5 7 8

        // Max-heap: reverse comparator
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.addAll(List.of(5, 1, 8, 3, 7, 2));
        System.out.print("Max-heap poll order: ");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");
        }
        System.out.println();  // 8 7 5 3 2 1

        // Custom objects — PriorityQueue with Comparator
        record Task(String name, int priority) {}
        PriorityQueue<Task> tasks = new PriorityQueue<>(
            Comparator.comparingInt(Task::priority)
        );
        tasks.offer(new Task("Send email",  3));
        tasks.offer(new Task("Fix bug",     1));
        tasks.offer(new Task("Write test",  2));
        tasks.offer(new Task("Deploy",      1));

        System.out.println("Tasks by priority:");
        while (!tasks.isEmpty()) {
            Task t = tasks.poll();
            System.out.println("  P" + t.priority() + " " + t.name());
        }
    }

    // =========================================================
    // SECTION 6: Choosing the Right Collection
    // =========================================================
    static void choosingDemo() {
        System.out.println("\n--- Choosing the Right Collection ---");
        System.out.println("ArrayList   — fast get(i), slow insert middle, ordered");
        System.out.println("LinkedList  — fast addFirst/addLast, slow get(i), ordered");
        System.out.println("HashSet     — O(1) add/contains, NO order");
        System.out.println("TreeSet     — O(log n) add/contains, SORTED, range queries");
        System.out.println("LinkedHashSet — insertion order, O(1) add/contains");
        System.out.println("HashMap     — O(1) get/put, NO order");
        System.out.println("TreeMap     — O(log n) get/put, SORTED keys, range queries");
        System.out.println("ArrayDeque  — O(1) push/pop both ends, better than Stack/LinkedList");
        System.out.println("PriorityQueue — O(log n) poll, smallest-first (or custom)");
    }

    public static void main(String[] args) {
        System.out.println("===== DAY 02: ADVANCED COLLECTIONS =====\n");
        linkedListDemo();
        treeSetDemo();
        treeMapDemo();
        arrayDequeDemo();
        priorityQueueDemo();
        choosingDemo();
        System.out.println("\n===== END OF LESSON =====");
    }
}
