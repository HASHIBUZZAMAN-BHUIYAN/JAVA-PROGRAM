/**
 * INTERMEDIATE Day 09 — Design Patterns II — Solutions
 */
import java.util.*;
import java.util.function.*;
import java.time.*;
import java.time.format.*;

// Observer: EventBus
class EventBus {
    private Map<String, List<Consumer<String>>> subscribers = new HashMap<>();

    void subscribe(String event, Consumer<String> handler) {
        subscribers.computeIfAbsent(event, k -> new ArrayList<>()).add(handler);
    }

    void publish(String event, String message) {
        List<Consumer<String>> handlers = subscribers.getOrDefault(event, List.of());
        System.out.println("[EventBus] Publishing '" + event + "': " + message);
        handlers.forEach(h -> h.accept(message));
    }
}

// Strategy: TextSearch
interface TextSearch { List<Integer> findAll(String text, String query); }
class CaseSensitiveSearch implements TextSearch {
    @Override public List<Integer> findAll(String text, String query) {
        List<Integer> indexes = new ArrayList<>();
        int i = 0;
        while ((i = text.indexOf(query, i)) != -1) { indexes.add(i); i++; }
        return indexes;
    }
}
class CaseInsensitiveSearch implements TextSearch {
    @Override public List<Integer> findAll(String text, String query) {
        return new CaseSensitiveSearch().findAll(text.toLowerCase(), query.toLowerCase());
    }
}
class Searcher {
    private TextSearch strategy;
    Searcher(TextSearch s) { this.strategy = s; }
    void setStrategy(TextSearch s) { this.strategy = s; }
    List<Integer> findAll(String text, String query) { return strategy.findAll(text, query); }
}

// Decorator: Logger
interface Logger2 { void log(String message); }
class ConsoleLogger2 implements Logger2 {
    @Override public void log(String msg) { System.out.println(msg); }
}
class PrefixDecorator2 implements Logger2 {
    private Logger2 inner; private String prefix;
    PrefixDecorator2(Logger2 inner, String prefix) { this.inner=inner; this.prefix=prefix; }
    @Override public void log(String msg) { inner.log(prefix + msg); }
}
class TimestampLogDecorator2 implements Logger2 {
    private Logger2 inner;
    TimestampLogDecorator2(Logger2 inner) { this.inner = inner; }
    @Override public void log(String msg) {
        inner.log("[" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "] " + msg);
    }
}

public class Solutions {
    public static void main(String[] args) {
        System.out.println("--- EventBus ---");
        EventBus bus = new EventBus();
        bus.subscribe("user.login",  msg -> System.out.println("  Auth service: " + msg));
        bus.subscribe("user.login",  msg -> System.out.println("  Audit log: " + msg));
        bus.subscribe("order.placed",msg -> System.out.println("  Warehouse: " + msg));
        bus.publish("user.login",   "alice logged in");
        bus.publish("order.placed", "Order #123 placed");
        bus.publish("user.logout",  "nobody subscribed");

        System.out.println("\n--- TextSearch ---");
        Searcher searcher = new Searcher(new CaseSensitiveSearch());
        System.out.println("Case-sensitive 'the': " + searcher.findAll("the cat and the dog", "the"));
        searcher.setStrategy(new CaseInsensitiveSearch());
        System.out.println("Case-insensitive 'THE': " + searcher.findAll("The Cat and THE Dog", "THE"));

        System.out.println("\n--- Logger Decorator ---");
        Logger2 logger = new TimestampLogDecorator2(
                         new PrefixDecorator2(
                         new ConsoleLogger2(), "[APP] "));
        logger.log("Application started");
        logger.log("User connected");
    }
}
