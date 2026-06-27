import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ANDROID ROOM DATABASE - CONCEPT DEMO (Plain Java, no Android SDK)
 *
 * WHAT THIS MAPS TO IN REAL ANDROID ROOM:
 * ┌──────────────────────────────────────────────────────────────────┐
 * │  This Demo               │  Real Android Room                    │
 * │─────────────────────────│───────────────────────────────────────│
 * │  @Entity annotation      │  @Entity (marks SQLite table)         │
 * │  @PrimaryKey annotation  │  @PrimaryKey(autoGenerate = true)     │
 * │  UserDao interface       │  @Dao interface                       │
 * │  UserDaoImpl (HashMap)   │  Room generates SQLite implementation │
 * │  UserRepository          │  Repository pattern (best practice)   │
 * │  RoomDatabase class      │  @Database(entities={User.class}, ..) │
 * └──────────────────────────────────────────────────────────────────┘
 *
 * WHY ROOM?
 * - Type-safe SQL queries (compile-time checking)
 * - No boilerplate SQLite cursor code
 * - Works with LiveData/Flow — UI auto-updates when DB changes
 * - Supports migration scripts for schema updates
 *
 * REAL ROOM USAGE:
 *   @Entity(tableName = "users")
 *   public class User {
 *       @PrimaryKey(autoGenerate = true) public int id;
 *       public String name;
 *   }
 *
 *   @Dao
 *   public interface UserDao {
 *       @Query("SELECT * FROM users WHERE id = :id")
 *       LiveData<User> findById(int id);
 *
 *       @Insert
 *       void insert(User user);
 *
 *       @Delete
 *       void delete(User user);
 *   }
 */
public class RoomDatabaseConceptDemo {

    // =========================================================================
    // CUSTOM ANNOTATIONS — Mirror Room's annotation API
    // =========================================================================

    /** Marks a class as a database table. Real Room: @Entity(tableName = "...") */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface Entity {
        String tableName() default "";
    }

    /** Marks the primary key field. Real Room: @PrimaryKey(autoGenerate = true) */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface PrimaryKey {
        boolean autoGenerate() default false;
    }

    /** Marks a DAO interface. Real Room: @Dao */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface Dao {}

    // =========================================================================
    // ENTITY — Maps to a database table
    // =========================================================================

    @Entity(tableName = "users")
    static class User {
        // Real Room: @PrimaryKey(autoGenerate = true) — Room handles ID generation
        @PrimaryKey(autoGenerate = true)
        private int id;
        private String name;
        private String email;

        // Real Room entities need a no-arg constructor (for Room to reconstruct objects)
        User() {}

        User(String name, String email) {
            this.name = name;
            this.email = email;
        }

        // Package-private setter for ID — only the DAO implementation sets this
        void setId(int id) { this.id = id; }

        int getId() { return id; }
        String getName() { return name; }
        String getEmail() { return email; }
        void setName(String name) { this.name = name; }
        void setEmail(String email) { this.email = email; }

        @Override
        public String toString() {
            return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
        }
    }

    // =========================================================================
    // DAO INTERFACE — Defines database operations
    // =========================================================================
    // Real Room: annotate methods with @Query, @Insert, @Update, @Delete
    // Room auto-generates the implementation at compile time using annotation processing

    @Dao
    interface UserDao {
        // Real Room: @Query("SELECT * FROM users WHERE id = :id")
        Optional<User> findById(int id);

        // Real Room: @Query("SELECT * FROM users")
        List<User> findAll();

        // Real Room: @Query("SELECT * FROM users WHERE name LIKE :name")
        List<User> findByName(String name);

        // Real Room: @Insert(onConflict = OnConflictStrategy.REPLACE)
        // Returns the auto-generated row ID
        int insert(User user);

        // Real Room: @Update
        boolean update(User user);

        // Real Room: @Delete
        boolean delete(User user);

        // Real Room: @Delete (or @Query("DELETE FROM users WHERE id = :id"))
        boolean deleteById(int id);

        // Real Room: @Query("SELECT COUNT(*) FROM users")
        int count();

        // Real Room: @Query("DELETE FROM users")
        void deleteAll();
    }

    // =========================================================================
    // DAO IMPLEMENTATION — In-memory HashMap simulating what Room does with SQLite
    // =========================================================================
    // In real Android, you NEVER write this class — Room generates it automatically
    // from your @Dao interface using annotation processing at build time.

    static class UserDaoImpl implements UserDao {
        // This HashMap simulates what SQLite does — Room generates all of this for you
        private final Map<Integer, User> database = new HashMap<>();
        private final AtomicInteger idSequence = new AtomicInteger(1);

        @Override
        public Optional<User> findById(int id) {
            return Optional.ofNullable(database.get(id));
        }

        @Override
        public List<User> findAll() {
            return new ArrayList<>(database.values());
        }

        @Override
        public List<User> findByName(String name) {
            // Real Room: Room generates a SQL LIKE query from @Query annotation
            List<User> results = new ArrayList<>();
            for (User user : database.values()) {
                if (user.getName().toLowerCase().contains(name.toLowerCase())) {
                    results.add(user);
                }
            }
            return results;
        }

        @Override
        public int insert(User user) {
            // Real Room: SQLite AUTO_INCREMENT handles this
            int newId = idSequence.getAndIncrement();
            user.setId(newId);
            database.put(newId, user);
            return newId;
        }

        @Override
        public boolean update(User user) {
            if (!database.containsKey(user.getId())) return false;
            database.put(user.getId(), user);
            return true;
        }

        @Override
        public boolean delete(User user) {
            return database.remove(user.getId()) != null;
        }

        @Override
        public boolean deleteById(int id) {
            return database.remove(id) != null;
        }

        @Override
        public int count() {
            return database.size();
        }

        @Override
        public void deleteAll() {
            database.clear();
            idSequence.set(1);
        }
    }

    // =========================================================================
    // REPOSITORY — Wraps DAO, provides clean API to ViewModels
    // =========================================================================
    // This is a recommended pattern on top of Room, not part of Room itself.
    // Hides whether data comes from local DB, network, or cache.

    static class UserRepository {
        private final UserDao userDao;

        UserRepository(UserDao userDao) {
            this.userDao = userDao;
        }

        public int createUser(String name, String email) {
            // Validation logic lives here, not in the DAO
            if (name == null || name.isBlank()) throw new IllegalArgumentException("Name is required");
            if (email == null || !email.contains("@")) throw new IllegalArgumentException("Invalid email");
            User user = new User(name, email);
            return userDao.insert(user);
        }

        public Optional<User> getUserById(int id) {
            return userDao.findById(id);
        }

        public List<User> getAllUsers() {
            return userDao.findAll();
        }

        public List<User> searchByName(String name) {
            return userDao.findByName(name);
        }

        public boolean updateEmail(int userId, String newEmail) {
            Optional<User> userOpt = userDao.findById(userId);
            if (userOpt.isEmpty()) return false;
            User user = userOpt.get();
            user.setEmail(newEmail);
            return userDao.update(user);
        }

        public boolean deleteUser(int userId) {
            return userDao.deleteById(userId);
        }

        public int getUserCount() {
            return userDao.count();
        }
    }

    // =========================================================================
    // SIMULATED DATABASE — In real Android: @Database(entities = {User.class}, version = 1)
    // =========================================================================

    static class AppDatabase {
        private static AppDatabase instance;
        private final UserDao userDao;

        private AppDatabase() {
            // Real Room: Room.databaseBuilder(context, AppDatabase.class, "app-database").build();
            this.userDao = new UserDaoImpl();
        }

        // Real Room: this singleton pattern is exactly how Room databases are used
        static AppDatabase getInstance() {
            if (instance == null) {
                instance = new AppDatabase();
            }
            return instance;
        }

        UserDao userDao() { return userDao; }
    }

    // =========================================================================
    // MAIN — Full demonstration
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("  ANDROID ROOM DATABASE CONCEPT DEMO");
        System.out.println("=".repeat(60));

        // Real Android: inject via Hilt/Dagger or get from Application context
        AppDatabase db = AppDatabase.getInstance();
        UserRepository repository = new UserRepository(db.userDao());

        // --- INSERT ---
        System.out.println("\n--- INSERTING USERS ---");
        int aliceId = repository.createUser("Alice Johnson", "alice@example.com");
        int bobId   = repository.createUser("Bob Smith", "bob@example.com");
        int carolId = repository.createUser("Carol Williams", "carol@example.com");
        int daveId  = repository.createUser("Dave Johnson", "dave@example.com");
        System.out.println("Inserted 4 users. IDs: " + aliceId + ", " + bobId + ", " + carolId + ", " + daveId);
        System.out.println("Total users in DB: " + repository.getUserCount());

        // --- FIND BY ID ---
        System.out.println("\n--- FIND BY ID ---");
        repository.getUserById(aliceId).ifPresent(u -> System.out.println("Found: " + u));
        repository.getUserById(99).ifPresentOrElse(
            u -> System.out.println("Found: " + u),
            () -> System.out.println("User with id=99 not found (expected)")
        );

        // --- FIND ALL ---
        System.out.println("\n--- ALL USERS ---");
        repository.getAllUsers().forEach(u -> System.out.println("  " + u));

        // --- SEARCH BY NAME ---
        System.out.println("\n--- SEARCH: 'Johnson' ---");
        repository.searchByName("Johnson").forEach(u -> System.out.println("  " + u));

        // --- UPDATE ---
        System.out.println("\n--- UPDATE ---");
        boolean updated = repository.updateEmail(aliceId, "alice.johnson@company.com");
        System.out.println("Update Alice's email: " + (updated ? "SUCCESS" : "FAILED"));
        repository.getUserById(aliceId).ifPresent(u -> System.out.println("After update: " + u));

        // --- DELETE ---
        System.out.println("\n--- DELETE ---");
        boolean deleted = repository.deleteUser(carolId);
        System.out.println("Delete Carol (id=" + carolId + "): " + (deleted ? "SUCCESS" : "FAILED"));
        System.out.println("Total users after delete: " + repository.getUserCount());

        // --- VALIDATION ---
        System.out.println("\n--- VALIDATION (Repository layer) ---");
        try {
            repository.createUser("", "bad");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
        try {
            repository.createUser("Valid Name", "not-an-email");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("REAL ANDROID ROOM SETUP:");
        System.out.println("  build.gradle: implementation 'androidx.room:room-runtime:2.6.1'");
        System.out.println("  annotationProcessor 'androidx.room:room-compiler:2.6.1'");
        System.out.println("  Room generates UserDaoImpl automatically at compile time.");
        System.out.println("  See ANDROID_REAL_SETUP.md for full instructions.");
        System.out.println("=".repeat(60));
    }
}
