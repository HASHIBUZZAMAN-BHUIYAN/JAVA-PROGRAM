/**
 * ADVANCED/TRENDING — GraphQL Concept Demo (In-Memory Simulation)
 *
 * GraphQL lets clients specify exactly which fields they need.
 * This demo simulates field-selection and nested queries in plain Java.
 * No GraphQL library needed — the goal is to understand the concept.
 *
 * Compile: javac GraphQlConceptDemo.java
 * Run:     java GraphQlConceptDemo
 */
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

// ── Data Model ────────────────────────────────────────────────────────────────
record Address(String street, String city, String country) {}

record User(int id, String name, String email, String phone, Address address, List<Integer> orderIds) {}

record Product(int id, String name, double price, String category) {}

record Order(int id, int userId, List<Integer> productIds, double total, String status) {}

// ── "Database" ────────────────────────────────────────────────────────────────
class DataStore {
    static final Map<Integer, User> USERS = Map.of(
        1, new User(1,"Alice","alice@example.com","555-0001",
                    new Address("123 Main St","Seoul","KR"), List.of(101,102)),
        2, new User(2,"Bob","bob@example.com","555-0002",
                    new Address("456 Oak Ave","Busan","KR"), List.of(103))
    );
    static final Map<Integer, Product> PRODUCTS = Map.of(
        1, new Product(1,"Laptop",999.99,"Electronics"),
        2, new Product(2,"Phone",699.99,"Electronics"),
        3, new Product(3,"Desk",249.99,"Furniture")
    );
    static final Map<Integer, Order> ORDERS = Map.of(
        101, new Order(101,1,List.of(1),999.99,"SHIPPED"),
        102, new Order(102,1,List.of(2,3),949.98,"DELIVERED"),
        103, new Order(103,2,List.of(1),999.99,"PROCESSING")
    );
}

// ── GraphQL-style Field Selector ──────────────────────────────────────────────
// In real GraphQL, the client sends a query document that specifies fields.
// Here we simulate this with a Set<String> of requested field names.
class UserResolver {
    // Returns only the fields the client requested — simulating field selection
    Map<String, Object> resolve(int id, Set<String> fields) {
        User user = DataStore.USERS.get(id);
        if (user == null) return Map.of("error", "User not found");

        Map<String, Object> result = new LinkedHashMap<>();
        if (fields.contains("id"))      result.put("id",    user.id());
        if (fields.contains("name"))    result.put("name",  user.name());
        if (fields.contains("email"))   result.put("email", user.email());
        if (fields.contains("phone"))   result.put("phone", user.phone());

        // Nested object resolution
        if (fields.contains("address")) {
            Address a = user.address();
            result.put("address", Map.of("city", a.city(), "country", a.country()));
        }

        // N+1 problem — fetching related orders (in real GQL, DataLoader batches this)
        if (fields.contains("orders")) {
            List<Map<String,Object>> orders = user.orderIds().stream()
                .map(oid -> {
                    Order o = DataStore.ORDERS.get(oid);
                    Map<String,Object> om = new LinkedHashMap<>();
                    om.put("id",     o.id());
                    om.put("total",  o.total());
                    om.put("status", o.status());
                    return om;
                }).collect(Collectors.toList());
            result.put("orders", orders);
        }

        return result;
    }

    List<Map<String,Object>> resolveAll(Set<String> fields) {
        return DataStore.USERS.keySet().stream()
            .map(id -> resolve(id, fields))
            .collect(Collectors.toList());
    }
}

// ── Query Executor (simulates the GraphQL engine) ────────────────────────────
class GraphQlEngine {
    private final UserResolver userResolver = new UserResolver();

    Object execute(String queryType, Object queryArg, Set<String> fields) {
        System.out.println("\n[Query] type=" + queryType + " arg=" + queryArg + " fields=" + fields);
        return switch (queryType) {
            case "user"     -> userResolver.resolve((int) queryArg, fields);
            case "users"    -> userResolver.resolveAll(fields);
            case "product"  -> resolveProduct((int) queryArg, fields);
            default         -> Map.of("error", "Unknown query type: " + queryType);
        };
    }

    private Map<String,Object> resolveProduct(int id, Set<String> fields) {
        Product p = DataStore.PRODUCTS.get(id);
        if (p == null) return Map.of("error", "Product not found");
        Map<String,Object> r = new LinkedHashMap<>();
        if (fields.contains("id"))       r.put("id",       p.id());
        if (fields.contains("name"))     r.put("name",     p.name());
        if (fields.contains("price"))    r.put("price",    p.price());
        if (fields.contains("category")) r.put("category", p.category());
        return r;
    }
}

public class GraphQlConceptDemo {
    public static void main(String[] args) {
        System.out.println("=== GraphQL Concept Demo ===");
        System.out.println("Key idea: clients specify exactly which fields they need.\n");

        GraphQlEngine engine = new GraphQlEngine();

        // REST problem: GET /users/1 always returns ALL fields
        // GraphQL solution: request only what you need

        // Query 1: just name and email (no phone, no address, no orders)
        System.out.println("=== Scenario 1: Mobile app — minimal data ===");
        System.out.println("  GraphQL query: { user(id:1) { name email } }");
        Object result1 = engine.execute("user", 1, Set.of("name","email"));
        System.out.println("  Response: " + result1);

        // Query 2: full profile with nested address
        System.out.println("\n=== Scenario 2: Profile page — with address ===");
        System.out.println("  GraphQL query: { user(id:1) { name email address { city country } } }");
        Object result2 = engine.execute("user", 1, Set.of("name","email","address"));
        System.out.println("  Response: " + result2);

        // Query 3: user dashboard with orders
        System.out.println("\n=== Scenario 3: Dashboard — user with orders ===");
        System.out.println("  GraphQL query: { user(id:2) { name orders { id total status } } }");
        Object result3 = engine.execute("user", 2, Set.of("name","orders"));
        System.out.println("  Response: " + result3);

        // Query 4: list all users — only id and name
        System.out.println("\n=== Scenario 4: User list — id and name only ===");
        System.out.println("  GraphQL query: { users { id name } }");
        Object result4 = engine.execute("users", null, Set.of("id","name"));
        System.out.println("  Response: " + result4);

        // Query 5: product with specific fields
        System.out.println("\n=== Scenario 5: Product query ===");
        System.out.println("  GraphQL query: { product(id:1) { name price } }");
        Object result5 = engine.execute("product", 1, Set.of("name","price"));
        System.out.println("  Response: " + result5);

        System.out.println("\n=== GraphQL vs REST ===");
        System.out.println("  REST: multiple endpoints, over-fetching, under-fetching");
        System.out.println("  GraphQL: single endpoint, client controls the shape of the response");
        System.out.println("  Real GraphQL: use graphql-java or Spring for GraphQL (@QueryMapping)");
    }
}
