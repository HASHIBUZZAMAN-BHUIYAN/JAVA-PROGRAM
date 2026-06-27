/**
 * INTERMEDIATE Day 08 — Design Patterns I: Singleton, Factory, Builder
 */
import java.util.*;

// ══════════════════════════════════════════════════════════════════════
// PATTERN 1: SINGLETON
// Ensures only ONE instance of a class is ever created.
// ══════════════════════════════════════════════════════════════════════
class AppConfig {
    private static AppConfig instance; // the single instance

    private Map<String, String> settings = new HashMap<>();

    // Private constructor — prevents new AppConfig() from outside
    private AppConfig() {
        settings.put("env",     "production");
        settings.put("version", "1.0.0");
        settings.put("timeout", "30");
    }

    // Thread-safe lazy initialization with synchronized
    public static synchronized AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
            System.out.println("  [AppConfig] Created single instance");
        }
        return instance;
    }

    public String get(String key) { return settings.getOrDefault(key, ""); }
    public void   set(String key, String value) { settings.put(key, value); }
}

// ══════════════════════════════════════════════════════════════════════
// PATTERN 2: FACTORY
// Creates objects without specifying the exact class to instantiate.
// ══════════════════════════════════════════════════════════════════════
interface Notification {
    void send(String message);
    String type();
}

class EmailNotification implements Notification {
    private String recipient;
    EmailNotification(String recipient) { this.recipient = recipient; }
    @Override public void send(String msg) {
        System.out.println("  📧 Email to " + recipient + ": " + msg);
    }
    @Override public String type() { return "EMAIL"; }
}

class SmsNotification implements Notification {
    private String phoneNumber;
    SmsNotification(String phoneNumber) { this.phoneNumber = phoneNumber; }
    @Override public void send(String msg) {
        System.out.println("  📱 SMS to " + phoneNumber + ": " + msg);
    }
    @Override public String type() { return "SMS"; }
}

class PushNotification implements Notification {
    private String deviceToken;
    PushNotification(String deviceToken) { this.deviceToken = deviceToken; }
    @Override public void send(String msg) {
        System.out.println("  🔔 Push to " + deviceToken + ": " + msg);
    }
    @Override public String type() { return "PUSH"; }
}

// The Factory — caller asks for a notification without knowing the class
class NotificationFactory {
    public static Notification create(String type, String target) {
        return switch (type.toUpperCase()) {
            case "EMAIL" -> new EmailNotification(target);
            case "SMS"   -> new SmsNotification(target);
            case "PUSH"  -> new PushNotification(target);
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
}

// ══════════════════════════════════════════════════════════════════════
// PATTERN 3: BUILDER
// Construct objects with many optional fields in a readable way.
// ══════════════════════════════════════════════════════════════════════
class HttpRequest {
    // Required fields
    private final String url;
    private final String method;
    // Optional fields
    private final Map<String, String> headers;
    private final String              body;
    private final int                 timeoutSeconds;
    private final boolean             followRedirects;

    // Private constructor — only the Builder can call it
    private HttpRequest(Builder b) {
        this.url             = b.url;
        this.method          = b.method;
        this.headers         = Collections.unmodifiableMap(b.headers);
        this.body            = b.body;
        this.timeoutSeconds  = b.timeoutSeconds;
        this.followRedirects = b.followRedirects;
    }

    @Override
    public String toString() {
        return String.format("""
            HttpRequest{
              method=%s  url=%s
              headers=%s
              body=%s  timeout=%ds  followRedirects=%s
            }""", method, url, headers, body, timeoutSeconds, followRedirects);
    }

    // ── Static nested Builder class ──────────────────────────────────────────
    static class Builder {
        private final String url;
        private final String method;
        private Map<String, String> headers = new HashMap<>();
        private String body            = null;
        private int    timeoutSeconds  = 30;
        private boolean followRedirects = true;

        Builder(String method, String url) { // required params in constructor
            this.method = method;
            this.url    = url;
        }

        Builder header(String name, String value) { headers.put(name, value); return this; }
        Builder body(String body)                 { this.body = body; return this; }
        Builder timeout(int seconds)              { this.timeoutSeconds = seconds; return this; }
        Builder noRedirects()                     { this.followRedirects = false; return this; }

        HttpRequest build() { return new HttpRequest(this); } // creates the object
    }
}

public class Lesson {
    public static void main(String[] args) {
        System.out.println("=== INTERMEDIATE Day 08: Design Patterns I ===\n");

        // ── SINGLETON ────────────────────────────────────────────────────────
        System.out.println("--- Singleton ---");
        AppConfig config1 = AppConfig.getInstance();
        AppConfig config2 = AppConfig.getInstance();
        System.out.println("  Same instance? " + (config1 == config2));  // true
        System.out.println("  env: " + config1.get("env"));
        config1.set("env", "staging");
        System.out.println("  env after change via config1: " + config2.get("env")); // "staging"
        System.out.println();

        // ── FACTORY ──────────────────────────────────────────────────────────
        System.out.println("--- Factory ---");
        Notification[] notifications = {
            NotificationFactory.create("EMAIL", "alice@example.com"),
            NotificationFactory.create("SMS",   "+1-555-1234"),
            NotificationFactory.create("PUSH",  "device-token-abc"),
        };
        for (Notification n : notifications) {
            n.send("Your order has shipped!");
        }
        System.out.println();

        // ── BUILDER ──────────────────────────────────────────────────────────
        System.out.println("--- Builder ---");
        // Simple GET request
        HttpRequest get = new HttpRequest.Builder("GET", "https://api.example.com/users")
            .header("Authorization", "Bearer token123")
            .header("Accept", "application/json")
            .timeout(10)
            .build();
        System.out.println("GET request:\n" + get);

        // POST request with body
        HttpRequest post = new HttpRequest.Builder("POST", "https://api.example.com/users")
            .header("Content-Type", "application/json")
            .body("{\"name\":\"Alice\",\"email\":\"alice@example.com\"}")
            .timeout(30)
            .noRedirects()
            .build();
        System.out.println("\nPOST request:\n" + post);

        System.out.println("\n=== End of Day 08 Lesson ===");
    }
}
