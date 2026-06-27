/**
 * Day 14 Capstone — Library System Walkthrough
 * Demonstrates combining all concepts from Days 1-13.
 */
import java.util.*;
import java.util.stream.*;

// ── Base class ────────────────────────────────────────────────────────────────
abstract class Book {
    private static int nextId = 1;
    private   int    id;
    protected String title;
    protected String author;
    protected String isbn;
    private   boolean available;

    Book(String title, String author, String isbn) {
        this.id        = nextId++;
        this.title     = title;
        this.author    = author;
        this.isbn      = isbn;
        this.available = true;
    }

    int     getId()        { return id; }
    String  getTitle()     { return title; }
    String  getAuthor()    { return author; }
    boolean isAvailable()  { return available; }
    void    setAvailable(boolean v) { available = v; }

    abstract String bookType();

    @Override
    public String toString() {
        return String.format("[%s] #%d %-35s %-20s (%s)",
            available ? "✓" : "✗", id, title, author, bookType());
    }
}

class PhysicalBook extends Book {
    private int pages;
    PhysicalBook(String title, String author, String isbn, int pages) {
        super(title, author, isbn);
        this.pages = pages;
    }
    @Override String bookType() { return "Physical, " + pages + "pp"; }
}

class EBook extends Book {
    private String format; // PDF, EPUB, etc.
    EBook(String title, String author, String isbn, String format) {
        super(title, author, isbn);
        this.format = format;
    }
    @Override String bookType() { return "eBook/" + format; }
}

// ── Custom exceptions ─────────────────────────────────────────────────────────
class BookNotFoundException extends RuntimeException {
    BookNotFoundException(int id) { super("Book #" + id + " not found"); }
}

class BookNotAvailableException extends RuntimeException {
    BookNotAvailableException(String title) { super("\"" + title + "\" is not available"); }
}

// ── Library ───────────────────────────────────────────────────────────────────
class Library {
    private List<Book>            catalog   = new ArrayList<>();
    private Map<Integer, Integer> checkouts = new HashMap<>(); // bookId -> memberId
    private String                name;

    Library(String name) { this.name = name; }

    void addBook(Book b)    { catalog.add(b); }

    Book findById(int id) {
        return catalog.stream()
            .filter(b -> b.getId() == id)
            .findFirst()
            .orElseThrow(() -> new BookNotFoundException(id));
    }

    List<Book> search(String query) {
        String q = query.toLowerCase();
        return catalog.stream()
            .filter(b -> b.title.toLowerCase().contains(q) ||
                         b.author.toLowerCase().contains(q))
            .collect(Collectors.toList());
    }

    void checkout(int bookId, int memberId) {
        Book b = findById(bookId);
        if (!b.isAvailable()) throw new BookNotAvailableException(b.getTitle());
        b.setAvailable(false);
        checkouts.put(bookId, memberId);
        System.out.println("  Checked out: " + b.getTitle());
    }

    void returnBook(int bookId) {
        Book b = findById(bookId);
        b.setAvailable(true);
        checkouts.remove(bookId);
        System.out.println("  Returned: " + b.getTitle());
    }

    void printCatalog() {
        System.out.println("\n=== " + name + " Catalog (" + catalog.size() + " books) ===");
        catalog.forEach(System.out::println);
    }

    void printStats() {
        long available  = catalog.stream().filter(Book::isAvailable).count();
        long checkedOut = catalog.size() - available;
        long physical   = catalog.stream().filter(b -> b instanceof PhysicalBook).count();
        long ebooks     = catalog.stream().filter(b -> b instanceof EBook).count();

        System.out.println("\n=== Library Stats ===");
        System.out.println("Total books:   " + catalog.size());
        System.out.println("Available:     " + available);
        System.out.println("Checked out:   " + checkedOut);
        System.out.println("Physical:      " + physical);
        System.out.println("eBooks:        " + ebooks);
    }
}

public class Lesson {
    public static void main(String[] args) {
        System.out.println("=== Day 14 Capstone: Library System Demo ===\n");

        Library lib = new Library("City Library");

        lib.addBook(new PhysicalBook("Clean Code",             "Robert Martin", "978-0132350884", 431));
        lib.addBook(new PhysicalBook("Effective Java",          "Joshua Bloch",  "978-0134685991", 412));
        lib.addBook(new EBook       ("Java 21 in Practice",    "Various",        "978-EBOOK-001",  "PDF"));
        lib.addBook(new PhysicalBook("The Pragmatic Programmer","Hunt & Thomas",  "978-0135957059", 352));
        lib.addBook(new EBook       ("Spring Boot in Action",  "Craig Walls",    "978-1617292545", "EPUB"));

        lib.printCatalog();

        System.out.println("\n--- Checkout ---");
        lib.checkout(1, 101);
        lib.checkout(3, 102);

        lib.printCatalog();

        System.out.println("\n--- Search 'java' ---");
        lib.search("java").forEach(System.out::println);

        System.out.println("\n--- Return ---");
        lib.returnBook(1);

        lib.printStats();

        System.out.println("\n--- Exception handling ---");
        try {
            lib.checkout(1, 101); // OK
            lib.checkout(1, 102); // already checked out!
        } catch (BookNotAvailableException e) {
            System.out.println("Caught: " + e.getMessage());
        }
        try {
            lib.findById(99);     // doesn't exist
        } catch (BookNotFoundException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        System.out.println("\n=== End of Capstone Lesson ===");
    }
}
