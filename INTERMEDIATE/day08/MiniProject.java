/**
 * INTERMEDIATE Day 08 Mini Project — User Profile Builder + Registry
 * Demonstrates Singleton (registry), Factory (account type), Builder (profile).
 *
 * Run: java MiniProject
 */
import java.util.*;

// Builder: UserProfile
class UserProfile {
    private final String username;
    private final String email;
    private final String fullName;
    private final String bio;
    private final List<String> roles;
    private final boolean emailVerified;
    private final String avatarUrl;

    private UserProfile(Builder b) {
        this.username      = b.username;
        this.email         = b.email;
        this.fullName      = b.fullName;
        this.bio           = b.bio;
        this.roles         = List.copyOf(b.roles);
        this.emailVerified = b.emailVerified;
        this.avatarUrl     = b.avatarUrl;
    }
    String getUsername() { return username; }

    @Override
    public String toString() {
        return String.format("UserProfile{user='%s', email='%s', name='%s',%n" +
            "  roles=%s, verified=%s, bio='%s'}",
            username, email, fullName, roles, emailVerified, bio);
    }

    static class Builder {
        private final String username;
        private final String email;
        private String       fullName = "";
        private String       bio      = "";
        private List<String> roles    = new ArrayList<>();
        private boolean      emailVerified = false;
        private String       avatarUrl = "";

        Builder(String username, String email) {
            this.username = username;
            this.email    = email;
        }
        Builder fullName(String n)  { this.fullName = n; return this; }
        Builder bio(String b)       { this.bio = b; return this; }
        Builder role(String r)      { this.roles.add(r); return this; }
        Builder verified()          { this.emailVerified = true; return this; }
        Builder avatarUrl(String u) { this.avatarUrl = u; return this; }
        UserProfile build()         { return new UserProfile(this); }
    }
}

// Singleton: UserRegistry
class UserRegistry {
    private static UserRegistry instance;
    private Map<String, UserProfile> users = new LinkedHashMap<>();

    private UserRegistry() {}

    static synchronized UserRegistry getInstance() {
        if (instance == null) instance = new UserRegistry();
        return instance;
    }

    void register(UserProfile profile) {
        users.put(profile.getUsername(), profile);
        System.out.println("  Registered: " + profile.getUsername());
    }

    Optional<UserProfile> find(String username) {
        return Optional.ofNullable(users.get(username));
    }

    void list() {
        System.out.println("  Total users: " + users.size());
        users.values().forEach(u -> System.out.println("  - " + u.getUsername()));
    }
}

public class MiniProject {
    public static void main(String[] args) {
        System.out.println("=== User Profile System ===\n");

        UserRegistry registry = UserRegistry.getInstance();

        // Build diverse profiles
        UserProfile admin = new UserProfile.Builder("admin", "admin@company.com")
            .fullName("System Admin")
            .role("ADMIN").role("USER")
            .bio("Manages the system")
            .verified()
            .build();

        UserProfile alice = new UserProfile.Builder("alice", "alice@example.com")
            .fullName("Alice Smith")
            .role("USER")
            .bio("Java developer and coffee enthusiast")
            .verified()
            .avatarUrl("https://avatars.example.com/alice")
            .build();

        UserProfile bob = new UserProfile.Builder("bob_dev", "bob@example.com")
            .fullName("Bob Jones")
            .role("USER").role("DEVELOPER")
            .bio("Backend developer")
            .build(); // not verified yet

        registry.register(admin);
        registry.register(alice);
        registry.register(bob);

        System.out.println("\nRegistered users:");
        registry.list();

        System.out.println("\nLookup 'alice':");
        registry.find("alice").ifPresentOrElse(
            u -> System.out.println(u),
            () -> System.out.println("Not found"));

        System.out.println("\nSame registry instance? " +
            (registry == UserRegistry.getInstance()));
    }
}
