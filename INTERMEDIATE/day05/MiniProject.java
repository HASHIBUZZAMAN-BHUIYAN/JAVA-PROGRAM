/**
 * INTERMEDIATE Day 05 Mini Project — CSV Contact Manager
 * Persists contacts to a CSV file using java.nio.file.
 *
 * Run: java MiniProject
 */
import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;

record ContactRecord(String name, String phone, String email, String group) {
    String toCsv() { return name + "," + phone + "," + email + "," + group; }
    static ContactRecord fromCsv(String line) {
        String[] p = line.split(",", 4);
        return new ContactRecord(p[0], p[1], p[2], p.length > 3 ? p[3] : "");
    }
    @Override public String toString() {
        return String.format("%-20s %-15s %-25s %s", name, phone, email, group);
    }
}

public class MiniProject {
    static final Path CSV_FILE = Path.of("contacts.csv");
    static List<ContactRecord> contacts = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        loadCsv();

        int choice;
        do {
            System.out.println("\n=== CSV Contact Manager ===");
            System.out.println("1. List contacts");
            System.out.println("2. Add contact");
            System.out.println("3. Search");
            System.out.println("4. List by group");
            System.out.println("5. Save & Quit");
            System.out.print("Choice: ");
            choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1 -> listAll();
                case 2 -> addContact();
                case 3 -> search();
                case 4 -> byGroup();
                case 5 -> { saveCsv(); System.out.println("Saved. Goodbye!"); }
            }
        } while (choice != 5);
    }

    static void loadCsv() throws IOException {
        if (!Files.exists(CSV_FILE)) {
            // Create sample data on first run
            contacts.add(new ContactRecord("Alice Smith", "555-0001", "alice@example.com", "Friends"));
            contacts.add(new ContactRecord("Bob Jones",   "555-0002", "bob@work.com",      "Work"));
            contacts.add(new ContactRecord("Carol White", "555-0003", "carol@example.com", "Friends"));
            System.out.println("No existing file. Loaded sample contacts.");
            return;
        }
        contacts = Files.readAllLines(CSV_FILE).stream()
            .filter(l -> !l.isBlank())
            .map(ContactRecord::fromCsv)
            .collect(Collectors.toList());
        System.out.println("Loaded " + contacts.size() + " contacts from " + CSV_FILE);
    }

    static void saveCsv() throws IOException {
        List<String> lines = contacts.stream().map(ContactRecord::toCsv).collect(Collectors.toList());
        Files.write(CSV_FILE, lines);
        System.out.println("Saved " + contacts.size() + " contacts to " + CSV_FILE);
    }

    static void listAll() {
        if (contacts.isEmpty()) { System.out.println("No contacts."); return; }
        System.out.printf("%-20s %-15s %-25s %s%n","Name","Phone","Email","Group");
        System.out.println("-".repeat(70));
        contacts.stream().sorted(Comparator.comparing(ContactRecord::name)).forEach(System.out::println);
    }

    static void addContact() {
        System.out.print("Name: ");  String name  = sc.nextLine();
        System.out.print("Phone: "); String phone = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Group: "); String group = sc.nextLine();
        contacts.add(new ContactRecord(name, phone, email, group));
        System.out.println("Contact added.");
    }

    static void search() {
        System.out.print("Search: "); String q = sc.nextLine().toLowerCase();
        contacts.stream()
            .filter(c -> c.name().toLowerCase().contains(q) ||
                         c.email().toLowerCase().contains(q))
            .forEach(System.out::println);
    }

    static void byGroup() {
        Map<String, List<ContactRecord>> grouped = contacts.stream()
            .collect(Collectors.groupingBy(ContactRecord::group));
        grouped.forEach((g, list) -> {
            System.out.println("\n[" + g + "]");
            list.forEach(System.out::println);
        });
    }
}
