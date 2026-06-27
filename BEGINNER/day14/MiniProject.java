/**
 * Day 14 Mini Project — Interactive Library Management System
 * Full CLI app: add books, checkout, return, search, save/load to file.
 *
 * Run: java MiniProject
 */
import java.util.*;
import java.util.stream.*;
import java.io.*;

// ── Domain classes ─────────────────────────────────────────────────────────────
class LibBook {
    private static int nextId = 1;
    private int    id;
    private String title;
    private String author;
    private String type; // "Physical" or "eBook"
    private boolean available = true;

    LibBook(String title, String author, String type) {
        this.id     = nextId++;
        this.title  = title;
        this.author = author;
        this.type   = type;
    }

    LibBook(int id, String title, String author, String type, boolean available) {
        this.id        = id;
        this.title     = title;
        this.author    = author;
        this.type      = type;
        this.available = available;
        if (id >= nextId) nextId = id + 1;
    }

    int getId()        { return id; }
    String getTitle()  { return title; }
    String getAuthor() { return author; }
    boolean isAvailable() { return available; }
    void setAvailable(boolean v) { available = v; }

    String toFileLine() {
        return id + "|" + title + "|" + author + "|" + type + "|" + available;
    }

    static LibBook fromFileLine(String line) {
        String[] p = line.split("\\|", 5);
        return new LibBook(Integer.parseInt(p[0]), p[1], p[2], p[3], Boolean.parseBoolean(p[4]));
    }

    @Override
    public String toString() {
        return String.format("[%s] #%-3d %-35s %-20s %s",
            available ? "✓" : "✗", id, title, author, type);
    }
}

// ── Library ────────────────────────────────────────────────────────────────────
class LibSystem {
    private List<LibBook> books = new ArrayList<>();
    private static final String FILE = "library_data.txt";

    void add(LibBook b) { books.add(b); }

    Optional<LibBook> find(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst();
    }

    List<LibBook> search(String q) {
        String lq = q.toLowerCase();
        return books.stream()
            .filter(b -> b.getTitle().toLowerCase().contains(lq) ||
                         b.getAuthor().toLowerCase().contains(lq))
            .collect(Collectors.toList());
    }

    boolean checkout(int id) {
        Optional<LibBook> opt = find(id);
        if (opt.isEmpty()) { System.out.println("Book #" + id + " not found."); return false; }
        LibBook b = opt.get();
        if (!b.isAvailable()) { System.out.println("\"" + b.getTitle() + "\" is not available."); return false; }
        b.setAvailable(false);
        System.out.println("Checked out: " + b.getTitle());
        return true;
    }

    boolean returnBook(int id) {
        Optional<LibBook> opt = find(id);
        if (opt.isEmpty()) { System.out.println("Book #" + id + " not found."); return false; }
        opt.get().setAvailable(true);
        System.out.println("Returned: " + opt.get().getTitle());
        return true;
    }

    void list() {
        if (books.isEmpty()) { System.out.println("No books in catalog."); return; }
        books.forEach(System.out::println);
    }

    void stats() {
        long avail = books.stream().filter(LibBook::isAvailable).count();
        System.out.printf("Total: %d  Available: %d  Checked out: %d%n",
            books.size(), avail, books.size() - avail);
    }

    void save() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            books.forEach(b -> pw.println(b.toFileLine()));
        }
        System.out.println("Saved " + books.size() + " books to " + FILE);
    }

    void load() throws IOException {
        File f = new File(FILE);
        if (!f.exists()) { System.out.println("No saved data found."); return; }
        books.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) books.add(LibBook.fromFileLine(line));
            }
        }
        System.out.println("Loaded " + books.size() + " books from " + FILE);
    }
}

// ── Main ──────────────────────────────────────────────────────────────────────
public class MiniProject {
    static LibSystem lib = new LibSystem();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Seed some books
        lib.add(new LibBook("Clean Code", "Robert Martin", "Physical"));
        lib.add(new LibBook("Effective Java", "Joshua Bloch", "Physical"));
        lib.add(new LibBook("Java 21 in Practice", "Various", "eBook"));
        lib.add(new LibBook("Spring Boot in Action", "Craig Walls", "eBook"));
        lib.add(new LibBook("The Pragmatic Programmer", "Hunt & Thomas", "Physical"));

        int choice;
        do {
            System.out.println("\n=== Library System ===");
            System.out.println("1. List all books");
            System.out.println("2. Search books");
            System.out.println("3. Add book");
            System.out.println("4. Checkout book");
            System.out.println("5. Return book");
            System.out.println("6. Stats");
            System.out.println("7. Save to file");
            System.out.println("8. Load from file");
            System.out.println("9. Quit");
            System.out.print("Choice: ");
            choice = Integer.parseInt(sc.nextLine().trim());

            try {
                switch (choice) {
                    case 1 -> lib.list();
                    case 2 -> {
                        System.out.print("Search: "); String q = sc.nextLine();
                        List<LibBook> results = lib.search(q);
                        if (results.isEmpty()) System.out.println("No results.");
                        else results.forEach(System.out::println);
                    }
                    case 3 -> {
                        System.out.print("Title: ");  String title  = sc.nextLine();
                        System.out.print("Author: "); String author = sc.nextLine();
                        System.out.print("Type (Physical/eBook): "); String type = sc.nextLine();
                        lib.add(new LibBook(title, author, type));
                        System.out.println("Book added.");
                    }
                    case 4 -> { System.out.print("Book ID: "); lib.checkout(Integer.parseInt(sc.nextLine())); }
                    case 5 -> { System.out.print("Book ID: "); lib.returnBook(Integer.parseInt(sc.nextLine())); }
                    case 6 -> lib.stats();
                    case 7 -> lib.save();
                    case 8 -> lib.load();
                    case 9 -> System.out.println("Goodbye!");
                }
            } catch (IOException e) {
                System.out.println("File error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        } while (choice != 9);
        sc.close();
    }
}
