# kafka-style-messaging

Simulates core Apache Kafka concepts in plain Java (no Kafka installation needed).

## What It Demonstrates
- **Topic**: named log of ordered messages
- **Offset**: each message's position in the log (consumers track their own offset)
- **Consumer Groups**: multiple independent groups each read the full topic from their own offset
- **Producer → Topic ← Multiple Consumer Groups** pattern

## Run
```
javac KafkaStyleMessagingConcept.java
java KafkaStyleMessagingConcept
```

## Real Kafka vs This Demo
| Feature | This Demo | Real Kafka |
|---------|-----------|-----------|
| Storage | CopyOnWriteArrayList | Distributed log on disk |
| Distribution | Single JVM | Multiple broker nodes |
| Partitioning | One partition per topic | N partitions per topic |
| Retention | JVM memory only | Configurable (hours/days/size) |
| Consumer groups | ✅ (same concept) | ✅ (full implementation) |

## Using Real Kafka
Add to pom.xml: `spring-kafka` dependency + Spring Boot.  
Config: `spring.kafka.bootstrap-servers=localhost:9092`  
Use `@KafkaListener(topics="orders", groupId="email-group")`.
