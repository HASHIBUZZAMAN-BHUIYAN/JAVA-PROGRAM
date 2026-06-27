/**
 * Day 08 Mini Project — Contact Book
 * Demonstrates classes, objects, arrays of objects, encapsulation.
 *
 * Run: java MiniProject
 */
import java.util.Scanner;

class Contact {
    private String name;
    private String phone;
    private String email;

    Contact(String name, String phone, String email) {
        this.name  = name;
        this.phone = phone;
        this.email = email;
    }

    String getName()  { return name; }
    String getPhone() { return phone; }
    String getEmail() { return email; }

    void setPhone(String phone) { this.phone = phone; }
    void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return String.format("%-20s  %-15s  %s", name, phone, email);
    }
}

public class MiniProject {
    static final int MAX = 50;
    static Contact[] contacts = new Contact[MAX];
    static int count = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Pre-load sample contacts
        contacts[count++] = new Contact("Alice Smith",  "555-1001", "alice@example.com");
        contacts[count++] = new Contact("Bob Jones",    "555-1002", "bob@example.com");
        contacts[count++] = new Contact("Carol White",  "555-1003", "carol@example.com");

        int choice;
        do {
            System.out.println("\n=== Contact Book ===");
            System.out.println("1. List all contacts");
            System.out.println("2. Add contact");
            System.out.println("3. Search by name");
            System.out.println("4. Quit");
            System.out.print("Choice: ");
            choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1 -> listAll();
                case 2 -> addContact();
                case 3 -> search();
                case 4 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    static void listAll() {
        if (count == 0) { System.out.println("No contacts."); return; }
        System.out.printf("%n%-20s  %-15s  %s%n", "Name", "Phone", "Email");
        System.out.println("-".repeat(55));
        for (int i = 0; i < count; i++) System.out.println(contacts[i]);
    }

    static void addContact() {
        if (count >= MAX) { System.out.println("Contact book full!"); return; }
        System.out.print("Name:  "); String name  = sc.nextLine().trim();
        System.out.print("Phone: "); String phone = sc.nextLine().trim();
        System.out.print("Email: "); String email = sc.nextLine().trim();
        contacts[count++] = new Contact(name, phone, email);
        System.out.println("Contact added.");
    }

    static void search() {
        System.out.print("Search name: ");
        String query = sc.nextLine().trim().toLowerCase();
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (contacts[i].getName().toLowerCase().contains(query)) {
                System.out.println(contacts[i]);
                found = true;
            }
        }
        if (!found) System.out.println("No contacts found for \"" + query + "\".");
    }
}
