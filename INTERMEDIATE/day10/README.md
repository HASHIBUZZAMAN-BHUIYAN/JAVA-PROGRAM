# INTERMEDIATE Day 10 — Capstone Maven Project

## What You Build
A Maven project demonstrating proper layering + multiple design patterns:
- **Builder** pattern (`Product` class)
- **Factory** pattern (`ProductFactory`)
- **Observer** pattern (price change notifications)
- **Strategy** pattern (`ProductService` sorting strategies)
- **JUnit 5** test suite

## How to Run
```
# From the day10/ directory:
mvn compile                                                           # compile
mvn test                                                              # run JUnit tests
mvn exec:java -Dexec.mainClass="com.javaprogram.capstone.Main"       # run app
```

## Project Structure
```
day10/
├── pom.xml
└── src/
    ├── main/java/com/javaprogram/capstone/
    │   ├── Main.java
    │   ├── model/Product.java           (Builder pattern)
    │   ├── factory/ProductFactory.java  (Factory pattern)
    │   ├── observer/PriceObserver.java  (Observer interface)
    │   ├── observer/PriceSubject.java   (Observable)
    │   └── service/ProductService.java  (Strategy for sorting/filtering)
    └── test/java/com/javaprogram/capstone/
        └── ProductServiceTest.java      (JUnit 5 tests)
```

## Time Estimate
90 minutes
