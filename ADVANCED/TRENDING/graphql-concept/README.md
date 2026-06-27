# graphql-concept

Demonstrates the core GraphQL idea — field selection and nested queries — using plain Java, no library required.

## What It Shows
- **Field selection**: clients declare exactly which fields they want
- **Nested objects**: resolving address, orders within a user query
- **Multiple query types**: `user`, `users`, `product`
- **The N+1 problem**: how fetching related data can cause multiple lookups

## Run
```
javac GraphQlConceptDemo.java
java GraphQlConceptDemo
```

## GraphQL vs REST

| Feature | REST | GraphQL |
|---------|------|---------|
| Endpoints | Many (`/users`, `/users/1/orders`) | One (`/graphql`) |
| Shape of response | Server decides | **Client decides** |
| Over-fetching | Common | Eliminated |
| Under-fetching | Solved with more endpoints | Solved by nesting |
| Typed schema | No (OpenAPI optional) | Yes (required) |

## Using Real GraphQL in Spring Boot
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-graphql</artifactId>
</dependency>
```
Then create schema file `src/main/resources/graphql/schema.graphqls` and annotate resolvers with `@QueryMapping`.
