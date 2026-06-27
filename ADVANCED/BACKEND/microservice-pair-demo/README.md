# microservice-pair-demo

Two Spring Boot microservices that communicate over HTTP, demonstrating the core microservice pattern.

## Services

| Service | Port | Responsibility |
|---------|------|---------------|
| `products-service` | 8081 | Product catalog (names, prices, stock) |
| `orders-service`   | 8082 | Place orders; calls products-service to get price and check stock |

## How to Run

Open **two separate terminals**:

**Terminal 1 — Products Service:**
```
cd products-service
mvn spring-boot:run
```

**Terminal 2 — Orders Service:**
```
cd orders-service
mvn spring-boot:run
```

## Test the Interaction

```bash
# 1. List products (from products-service)
curl http://localhost:8081/api/products

# 2. Place an order (orders-service calls products-service internally)
curl -X POST http://localhost:8082/api/orders \
  -H "Content-Type: application/json" \
  -d '{"productId":"P001","quantity":2}'

# 3. List all orders
curl http://localhost:8082/api/orders
```

## Key Concept: Inter-Service HTTP Communication
`OrderService` uses Spring's `RestTemplate` to call the products-service:
```java
Map product = restTemplate.getForObject("http://localhost:8081/api/products/P001", Map.class);
```
In production, replace the hardcoded URL with service discovery (Eureka/Consul) and a load-balancing client (Feign or WebClient).
