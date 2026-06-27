/**
 * Day 12 Mini Project — Simple To-Do List Manager
 * Uses ArrayList and HashMap to manage tasks.
 *
 * Run: java MiniProject
 */
import java.util.*;

class Task {
    private static int nextId = 1;
    private int     id;
    private String  title;
    private String  category;
    private boolean done;

    Task(String title, String category) {
        this.id       = nextId++;
        this.title    = title;
        this.category = category;
        this.done     = false;
    }

    int    getId()       { return id; }
    String getCategory() { return category; }
    boolean isDone()     { return done; }
    void    markDone()   { done = true; }

    @Override
    public String toString() {
        return String.format("[%s] #%d %-30s (%s)",
            done ? "X" : " ", id, title, category);
    }
}

public class MiniProject {
    static List<Task> tasks = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Pre-load tasks
        tasks.add(new Task("Buy groceries", "Personal"));
        tasks.add(new Task("Finish Java Day 12", "Study"));
        tasks.add(new Task("Call dentist", "Health"));
        tasks.add(new Task("Review pull request", "Work"));

        int choice;
        do {
            System.out.println("\n=== To-Do Manager ===");
            System.out.println("1. Show all tasks");
            System.out.println("2. Add task");
            System.out.println("3. Mark done");
            System.out.println("4. Tasks by category");
            System.out.println("5. Summary stats");
            System.out.println("6. Quit");
            System.out.print("Choice: ");
            choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1 -> showAll();
                case 2 -> addTask();
                case 3 -> markDone();
                case 4 -> byCategory();
                case 5 -> stats();
                case 6 -> System.out.println("Goodbye!");
            }
        } while (choice != 6);
    }

    static void showAll() {
        if (tasks.isEmpty()) { System.out.println("No tasks."); return; }
        tasks.forEach(System.out::println);
    }

    static void addTask() {
        System.out.print("Title: "); String title = sc.nextLine();
        System.out.print("Category: "); String cat = sc.nextLine();
        tasks.add(new Task(title, cat));
        System.out.println("Task added.");
    }

    static void markDone() {
        System.out.print("Task ID to mark done: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        tasks.stream().filter(t -> t.getId() == id).findFirst()
            .ifPresentOrElse(t -> { t.markDone(); System.out.println("Marked done."); },
                             () -> System.out.println("Task not found."));
    }

    static void byCategory() {
        Map<String, List<Task>> byCategory = new HashMap<>();
        for (Task t : tasks)
            byCategory.computeIfAbsent(t.getCategory(), k -> new ArrayList<>()).add(t);
        byCategory.forEach((cat, list) -> {
            System.out.println("\n" + cat + ":");
            list.forEach(t -> System.out.println("  " + t));
        });
    }

    static void stats() {
        long done = tasks.stream().filter(Task::isDone).count();
        System.out.println("Total: " + tasks.size() + "  Done: " + done +
                           "  Pending: " + (tasks.size() - done));
    }
}
