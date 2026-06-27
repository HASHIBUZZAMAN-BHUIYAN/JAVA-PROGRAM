/**
 * Day 10 Mini Project — Payment Processing System
 * Models different payment methods via abstract class + interfaces.
 *
 * Run: java MiniProject
 */

interface Refundable {
    boolean refund(double amount);
}

interface Validateable {
    boolean validate();
    String validationError();
}

abstract class PaymentMethod implements Validateable {
    protected String accountId;
    protected double balance;

    PaymentMethod(String accountId, double balance) {
        this.accountId = accountId;
        this.balance   = balance;
    }

    abstract boolean charge(double amount);
    abstract String  paymentType();

    @Override
    public String toString() {
        return String.format("%s[id=%s, balance=%.2f]", paymentType(), accountId, balance);
    }
}

class CreditCard extends PaymentMethod implements Refundable {
    private double creditLimit;

    CreditCard(String cardId, double balance, double creditLimit) {
        super(cardId, balance);
        this.creditLimit = creditLimit;
    }

    @Override
    public boolean charge(double amount) {
        if (balance + amount > creditLimit) { System.out.println("  Credit limit exceeded!"); return false; }
        balance += amount;
        System.out.printf("  Charged $%.2f to credit card. Owed: $%.2f%n", amount, balance);
        return true;
    }

    @Override
    public boolean refund(double amount) {
        balance -= amount;
        System.out.printf("  Refunded $%.2f. Owed: $%.2f%n", amount, balance);
        return true;
    }

    @Override public String paymentType() { return "CreditCard"; }
    @Override public boolean validate()   { return accountId.length() == 16; }
    @Override public String validationError() { return "Card number must be 16 digits"; }
}

class BankTransfer extends PaymentMethod {
    BankTransfer(String accountId, double balance) {
        super(accountId, balance);
    }

    @Override
    public boolean charge(double amount) {
        if (amount > balance) { System.out.println("  Insufficient funds!"); return false; }
        balance -= amount;
        System.out.printf("  Transferred $%.2f. Remaining: $%.2f%n", amount, balance);
        return true;
    }

    @Override public String paymentType() { return "BankTransfer"; }
    @Override public boolean validate()   { return accountId.startsWith("ACC") && accountId.length() == 9; }
    @Override public String validationError() { return "Account ID must be ACC + 6 digits"; }
}

public class MiniProject {
    public static void main(String[] args) {
        PaymentMethod[] methods = {
            new CreditCard("1234567890123456", 0, 5000),
            new BankTransfer("ACC123456", 2500),
        };

        System.out.println("=== Payment Processing Demo ===\n");
        for (PaymentMethod pm : methods) {
            System.out.println(pm);
            if (!pm.validate()) {
                System.out.println("  INVALID: " + pm.validationError());
                continue;
            }
            pm.charge(150.00);
            pm.charge(300.00);

            if (pm instanceof Refundable r) {
                r.refund(50.00);
            }
            System.out.println("  Final: " + pm);
            System.out.println();
        }
    }
}
