/**
 * INTERMEDIATE Day 09 — Design Patterns II: Observer, Strategy, Decorator
 */
import java.util.*;
import java.util.function.*;

// ══════════════════════════════════════════════════════════════════════
// PATTERN 1: OBSERVER
// Objects (Observers) subscribe to events from a Subject.
// When Subject state changes, all Observers are notified.
// ══════════════════════════════════════════════════════════════════════
interface StockObserver {
    void onPriceChange(String ticker, double oldPrice, double newPrice);
}

class StockTicker {
    private String ticker;
    private double price;
    private List<StockObserver> observers = new ArrayList<>();

    StockTicker(String ticker, double initialPrice) {
        this.ticker = ticker;
        this.price  = initialPrice;
    }

    void subscribe(StockObserver observer)   { observers.add(observer); }
    void unsubscribe(StockObserver observer) { observers.remove(observer); }

    void setPrice(double newPrice) {
        double old = this.price;
        this.price = newPrice;
        // Notify all observers
        for (StockObserver o : observers) o.onPriceChange(ticker, old, newPrice);
    }
}

class AlertObserver implements StockObserver {
    private double threshold;
    AlertObserver(double threshold) { this.threshold = threshold; }

    @Override
    public void onPriceChange(String ticker, double old, double current) {
        if (current < threshold)
            System.out.printf("  ⚠️  ALERT: %s dropped to $%.2f (below $%.2f)!%n",
                              ticker, current, threshold);
    }
}

class LogObserver implements StockObserver {
    @Override
    public void onPriceChange(String ticker, double old, double current) {
        System.out.printf("  📊 LOG: %s  $%.2f → $%.2f  (%+.1f%%)%n",
            ticker, old, current, (current - old) / old * 100);
    }
}

// ══════════════════════════════════════════════════════════════════════
// PATTERN 2: STRATEGY
// Encapsulate a family of algorithms, make them interchangeable.
// ══════════════════════════════════════════════════════════════════════
interface SortStrategy {
    <T extends Comparable<T>> List<T> sort(List<T> items);
    String name();
}

class BubbleSortStrategy implements SortStrategy {
    @Override
    public <T extends Comparable<T>> List<T> sort(List<T> items) {
        List<T> list = new ArrayList<>(items);
        for (int i = 0; i < list.size() - 1; i++)
            for (int j = 0; j < list.size() - i - 1; j++)
                if (list.get(j).compareTo(list.get(j+1)) > 0) {
                    T tmp = list.get(j); list.set(j, list.get(j+1)); list.set(j+1, tmp);
                }
        return list;
    }
    @Override public String name() { return "BubbleSort"; }
}

class JavaBuiltinSortStrategy implements SortStrategy {
    @Override
    public <T extends Comparable<T>> List<T> sort(List<T> items) {
        List<T> list = new ArrayList<>(items);
        Collections.sort(list);
        return list;
    }
    @Override public String name() { return "JavaBuiltin"; }
}

// Context uses whichever strategy is injected
class Sorter {
    private SortStrategy strategy;
    Sorter(SortStrategy strategy) { this.strategy = strategy; }
    void setStrategy(SortStrategy s) { this.strategy = s; }
    <T extends Comparable<T>> List<T> sort(List<T> items) {
        System.out.println("  Using: " + strategy.name());
        return strategy.sort(items);
    }
}

// ══════════════════════════════════════════════════════════════════════
// PATTERN 3: DECORATOR
// Wrap an object to add behavior without changing its class.
// ══════════════════════════════════════════════════════════════════════
interface TextProcessor {
    String process(String text);
}

class PlainTextProcessor implements TextProcessor {
    @Override public String process(String text) { return text; }
}

// Each decorator wraps another TextProcessor
class TrimDecorator implements TextProcessor {
    private TextProcessor inner;
    TrimDecorator(TextProcessor inner) { this.inner = inner; }
    @Override public String process(String text) { return inner.process(text).trim(); }
}

class UpperCaseDecorator implements TextProcessor {
    private TextProcessor inner;
    UpperCaseDecorator(TextProcessor inner) { this.inner = inner; }
    @Override public String process(String text) { return inner.process(text).toUpperCase(); }
}

class HtmlEscapeDecorator implements TextProcessor {
    private TextProcessor inner;
    HtmlEscapeDecorator(TextProcessor inner) { this.inner = inner; }
    @Override public String process(String text) {
        return inner.process(text)
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;");
    }
}

class TimestampDecorator implements TextProcessor {
    private TextProcessor inner;
    TimestampDecorator(TextProcessor inner) { this.inner = inner; }
    @Override public String process(String text) {
        return "[2024-01-01T12:00:00] " + inner.process(text);
    }
}

public class Lesson {
    public static void main(String[] args) {
        System.out.println("=== INTERMEDIATE Day 09: Design Patterns II ===\n");

        // ── OBSERVER ─────────────────────────────────────────────────────────
        System.out.println("--- Observer ---");
        StockTicker aapl = new StockTicker("AAPL", 175.00);
        aapl.subscribe(new LogObserver());
        aapl.subscribe(new AlertObserver(160.00));

        aapl.setPrice(178.50);
        aapl.setPrice(162.00);
        aapl.setPrice(155.00); // triggers alert
        System.out.println();

        // Lambda observer (Java 8+ — SAM interface)
        StockTicker tsla = new StockTicker("TSLA", 200.00);
        tsla.subscribe((ticker, old, cur) ->
            System.out.printf("  Custom lambda: %s $%.2f%n", ticker, cur));
        tsla.setPrice(195.00);
        System.out.println();

        // ── STRATEGY ─────────────────────────────────────────────────────────
        System.out.println("--- Strategy ---");
        List<Integer> nums = List.of(5, 2, 8, 1, 9, 3, 7, 4, 6);

        Sorter sorter = new Sorter(new BubbleSortStrategy());
        System.out.println("Sorted: " + sorter.sort(nums));

        sorter.setStrategy(new JavaBuiltinSortStrategy()); // swap strategy at runtime
        System.out.println("Sorted: " + sorter.sort(nums));
        System.out.println();

        // ── DECORATOR ────────────────────────────────────────────────────────
        System.out.println("--- Decorator ---");
        String raw = "   Hello <World> & \"friends\"!   ";

        // Plain
        TextProcessor plain = new PlainTextProcessor();
        System.out.println("Plain:   [" + plain.process(raw) + "]");

        // Trim only
        TextProcessor trimmed = new TrimDecorator(new PlainTextProcessor());
        System.out.println("Trimmed: [" + trimmed.process(raw) + "]");

        // Trim + HTML escape + uppercase — composing decorators
        TextProcessor full = new UpperCaseDecorator(
                             new HtmlEscapeDecorator(
                             new TrimDecorator(
                             new PlainTextProcessor())));
        System.out.println("Full:    [" + full.process(raw) + "]");

        // With timestamp
        TextProcessor logged = new TimestampDecorator(trimmed);
        System.out.println("Logged:  [" + logged.process(raw) + "]");

        System.out.println("\n=== End of Day 09 Lesson ===");
    }
}
