/**
 * Day 08 — OOP I: Classes, Objects, Constructors, Encapsulation
 *
 * Java requires ONE public class per file matching the filename.
 * Helper classes in the same file can be non-public (package-private).
 */

// ── Helper class (non-public) — can live in Lesson.java ─────────────────────
class BankAccount {
    // private fields — hidden from outside code (encapsulation)
    private String owner;
    private double balance;
    private int    transactionCount;

    // Constructor — called when you write: new BankAccount("Alice", 1000)
    BankAccount(String owner, double initialBalance) {
        this.owner   = owner;          // 'this.owner' is the field; 'owner' is the parameter
        this.balance = initialBalance;
        this.transactionCount = 0;
    }

    // Default constructor — no initial balance
    BankAccount(String owner) {
        this(owner, 0.0);  // calls the 2-arg constructor above
    }

    // Getters — read-only access to private fields
    public String getOwner()   { return owner; }
    public double getBalance() { return balance; }
    public int    getTransactionCount() { return transactionCount; }

    // Business methods
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
        transactionCount++;
        System.out.printf("  Deposited $%.2f — new balance: $%.2f%n", amount, balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal must be positive");
        if (amount > balance) throw new IllegalStateException("Insufficient funds");
        balance -= amount;
        transactionCount++;
        System.out.printf("  Withdrew $%.2f — new balance: $%.2f%n", amount, balance);
    }

    // toString — lets System.out.println(account) show something useful
    @Override
    public String toString() {
        return String.format("BankAccount[owner=%s, balance=%.2f, txns=%d]",
                             owner, balance, transactionCount);
    }
}

// ── Public class — must match the filename (Lesson.java) ─────────────────────
public class Lesson {

    public static void main(String[] args) {
        System.out.println("=== Day 08: OOP I — Classes and Objects ===\n");

        // ── Create objects with 'new' ────────────────────────────────────────
        BankAccount alice = new BankAccount("Alice", 1000.00);
        BankAccount bob   = new BankAccount("Bob");   // starts at 0

        System.out.println("Created: " + alice);
        System.out.println("Created: " + bob);
        System.out.println();

        // ── Call methods ────────────────────────────────────────────────────
        System.out.println("Alice's transactions:");
        alice.deposit(500.00);
        alice.withdraw(200.00);
        alice.deposit(100.00);

        System.out.println("\nBob's transactions:");
        bob.deposit(750.00);
        bob.withdraw(300.00);

        System.out.println();
        System.out.println("Final state:");
        System.out.println("  " + alice);
        System.out.println("  " + bob);
        System.out.println();

        // ── Getters ─────────────────────────────────────────────────────────
        System.out.printf("Alice's balance: $%.2f%n", alice.getBalance());
        System.out.println("Bob's transactions: " + bob.getTransactionCount());
        System.out.println();

        // ── Each object is INDEPENDENT ───────────────────────────────────────
        // alice and bob are separate objects; changing one doesn't affect the other
        System.out.println("Objects are independent:");
        alice.deposit(50);
        System.out.println("  Alice: $" + alice.getBalance());
        System.out.println("  Bob:   $" + bob.getBalance()); // unchanged

        System.out.println("\n=== End of Day 08 Lesson ===");
    }
}
