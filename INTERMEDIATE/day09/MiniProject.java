/**
 * INTERMEDIATE Day 09 Mini Project — E-Commerce Checkout System
 * Uses Strategy (payment), Observer (order events), Decorator (receipt formatting).
 *
 * Run: java MiniProject
 */
import java.util.*;
import java.util.function.*;

// ── Strategy: Payment ─────────────────────────────────────────────────────────
interface PaymentStrategy {
    boolean pay(double amount);
    String type();
}

class CreditCardPayment implements PaymentStrategy {
    private String last4; private double limit, balance;
    CreditCardPayment(String last4, double limit) { this.last4=last4; this.limit=limit; }
    @Override public boolean pay(double amount) {
        if (balance + amount > limit) { System.out.println("  Credit limit exceeded!"); return false; }
        balance += amount;
        System.out.printf("  Charged $%.2f to card ending %s%n", amount, last4);
        return true;
    }
    @Override public String type() { return "CreditCard-" + last4; }
}

class WalletPayment implements PaymentStrategy {
    private double balance;
    WalletPayment(double balance) { this.balance = balance; }
    @Override public boolean pay(double amount) {
        if (amount > balance) { System.out.println("  Insufficient wallet balance!"); return false; }
        balance -= amount;
        System.out.printf("  Paid $%.2f from wallet. Remaining: $%.2f%n", amount, balance);
        return true;
    }
    @Override public String type() { return "Wallet"; }
}

// ── Observer: Order Events ────────────────────────────────────────────────────
interface OrderObserver {
    void onOrderPlaced(String orderId, double total, String payment);
}

// ── Decorator: Receipt ────────────────────────────────────────────────────────
interface ReceiptFormatter {
    String format(String orderId, List<String> items, double total);
}
class PlainReceipt implements ReceiptFormatter {
    @Override public String format(String id, List<String> items, double total) {
        return "Order " + id + ": " + items + " = $" + String.format("%.2f", total);
    }
}
class DetailedReceiptDecorator implements ReceiptFormatter {
    private ReceiptFormatter inner;
    DetailedReceiptDecorator(ReceiptFormatter inner) { this.inner = inner; }
    @Override public String format(String id, List<String> items, double total) {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════\n");
        sb.append("         RECEIPT\n");
        sb.append("═══════════════════════════════\n");
        sb.append(inner.format(id, items, total)).append("\n");
        sb.append("───────────────────────────────\n");
        sb.append(String.format("Total: $%.2f%n", total));
        sb.append("═══════════════════════════════");
        return sb.toString();
    }
}

// ── Order / Checkout ──────────────────────────────────────────────────────────
class Checkout {
    private PaymentStrategy payment;
    private List<OrderObserver> observers = new ArrayList<>();
    private ReceiptFormatter formatter;

    Checkout(PaymentStrategy payment, ReceiptFormatter formatter) {
        this.payment   = payment;
        this.formatter = formatter;
    }

    void addObserver(OrderObserver o) { observers.add(o); }

    void placeOrder(String orderId, Map<String,Double> cart) {
        double total = cart.values().stream().mapToDouble(Double::doubleValue).sum();
        System.out.println("\n--- Processing Order " + orderId + " ---");
        System.out.printf("  Items: %s  Total: $%.2f%n", cart.keySet(), total);

        boolean success = payment.pay(total);
        if (!success) { System.out.println("  Order failed!"); return; }

        String payType = payment.type();
        observers.forEach(o -> o.onOrderPlaced(orderId, total, payType));

        List<String> items = new ArrayList<>(cart.keySet());
        System.out.println("\n" + formatter.format(orderId, items, total));
    }
}

public class MiniProject {
    public static void main(String[] args) {
        System.out.println("=== E-Commerce Checkout System ===");

        // Set up observers
        OrderObserver emailConfirm = (id, total, pay) ->
            System.out.printf("  [EMAIL] Order %s confirmed — $%.2f paid via %s%n", id, total, pay);
        OrderObserver auditLog = (id, total, pay) ->
            System.out.printf("  [AUDIT] Order %s logged%n", id);

        // Checkout with credit card + detailed receipt
        Checkout checkout = new Checkout(
            new CreditCardPayment("4242", 5000),
            new DetailedReceiptDecorator(new PlainReceipt())
        );
        checkout.addObserver(emailConfirm);
        checkout.addObserver(auditLog);

        Map<String,Double> cart1 = new LinkedHashMap<>();
        cart1.put("Laptop",    999.99);
        cart1.put("Mouse",      29.99);
        cart1.put("USB Hub",    39.99);
        checkout.placeOrder("ORD-001", cart1);

        // Switch strategy to wallet
        Checkout checkout2 = new Checkout(
            new WalletPayment(50.00),
            new DetailedReceiptDecorator(new PlainReceipt())
        );
        checkout2.addObserver(emailConfirm);

        Map<String,Double> cart2 = new LinkedHashMap<>();
        cart2.put("Phone Case", 15.99);
        cart2.put("Screen Protector", 9.99);
        checkout2.placeOrder("ORD-002", cart2);

        // Insufficient funds example
        Map<String,Double> cart3 = new LinkedHashMap<>();
        cart3.put("Tablet", 499.99);
        checkout2.placeOrder("ORD-003", cart3); // will fail
    }
}
