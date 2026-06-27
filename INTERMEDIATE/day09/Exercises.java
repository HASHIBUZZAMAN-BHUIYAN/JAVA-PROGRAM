/**
 * INTERMEDIATE Day 09 — Design Patterns II — Exercises
 */
import java.util.*;

// Exercise 1 (Observer): Create a simple EventBus where any object can
// subscribe to named events and publish messages. When published, all
// subscribers to that event name receive the message.
// subscribe(String eventName, Consumer<String> handler)
// publish(String eventName, String message)

// Exercise 2 (Strategy): Create a TextSearch strategy with two implementations:
// - CaseSensitiveSearch: finds exact substring
// - CaseInsensitiveSearch: finds substring ignoring case
// A Searcher class holds a TextSearch strategy and has:
//   List<Integer> findAll(String text, String query) — returns all start indexes

// Exercise 3 (Decorator): Create a Logger interface with log(String message).
// ConsoleLogger writes to System.out.
// PrefixDecorator adds a prefix string before each message.
// TimestampLogDecorator adds "[HH:mm:ss] " before each message.
// Demonstrate composing them.

public class Exercises {
    public static void main(String[] args) {
        System.out.println("Implement the classes above and test them here.");
        System.out.println("See Solutions.java for reference implementations.");
    }
}
