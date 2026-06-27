/**
 * ADVANCED/TRENDING — Kafka-Style Messaging (In-Memory Simulation)
 *
 * Real Kafka is a distributed streaming platform. This demo simulates its
 * core concepts (topics, partitions, consumer groups, offsets) using
 * Java's concurrency utilities — no Kafka installation required.
 *
 * Compile: javac KafkaStyleMessagingConcept.java
 * Run:     java KafkaStyleMessagingConcept
 */
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;

// ── Message (Kafka calls this a Record) ──────────────────────────────────────
class Message {
    final String  topic;
    final String  key;
    final String  value;
    final long    offset;
    final long    timestamp;

    Message(String topic, String key, String value, long offset) {
        this.topic     = topic;
        this.key       = key;
        this.value     = value;
        this.offset    = offset;
        this.timestamp = System.currentTimeMillis();
    }

    @Override public String toString() {
        return String.format("[%s | key=%s | offset=%d] %s", topic, key, offset, value);
    }
}

// ── Topic (in Kafka, a topic has partitions; here one in-memory list per topic)
class Topic {
    final String              name;
    final List<Message>       log   = new CopyOnWriteArrayList<>();
    final AtomicLong          nextOffset = new AtomicLong(0);

    Topic(String name) { this.name = name; }

    Message append(String key, String value) {
        Message msg = new Message(name, key, value, nextOffset.getAndIncrement());
        log.add(msg);
        return msg;
    }

    List<Message> read(long fromOffset) {
        return log.stream().filter(m -> m.offset >= fromOffset).toList();
    }
}

// ── Broker (central hub; Kafka calls this a Kafka broker/cluster) ─────────────
class Broker {
    private final Map<String, Topic>                    topics    = new ConcurrentHashMap<>();
    private final Map<String, Map<String, Long>>        offsets   = new ConcurrentHashMap<>(); // group → topic → offset

    Topic getOrCreateTopic(String name) {
        return topics.computeIfAbsent(name, Topic::new);
    }

    void publish(String topicName, String key, String value) {
        Message msg = getOrCreateTopic(topicName).append(key, value);
        System.out.println("  [BROKER] Published: " + msg);
    }

    List<Message> poll(String group, String topicName, int maxMessages) {
        Topic topic  = getOrCreateTopic(topicName);
        long  offset = offsets
            .computeIfAbsent(group, g -> new ConcurrentHashMap<>())
            .getOrDefault(topicName, 0L);

        List<Message> batch = topic.read(offset).stream().limit(maxMessages).toList();
        if (!batch.isEmpty()) {
            long newOffset = batch.get(batch.size()-1).offset + 1;
            offsets.get(group).put(topicName, newOffset);
        }
        return batch;
    }
}

// ── Producer ──────────────────────────────────────────────────────────────────
class KafkaProducer {
    private final Broker broker;
    KafkaProducer(Broker broker) { this.broker = broker; }

    void send(String topic, String key, String value) { broker.publish(topic, key, value); }
}

// ── Consumer ──────────────────────────────────────────────────────────────────
class KafkaConsumer {
    private final Broker       broker;
    private final String       group;
    private final String       topic;
    private final Consumer<Message> handler;

    KafkaConsumer(Broker broker, String group, String topic, Consumer<Message> handler) {
        this.broker  = broker;
        this.group   = group;
        this.topic   = topic;
        this.handler = handler;
    }

    void poll() {
        List<Message> messages = broker.poll(group, topic, 10);
        messages.forEach(handler);
    }
}

public class KafkaStyleMessagingConcept {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Kafka-Style Messaging Concept Demo ===\n");

        Broker         broker   = new Broker();
        KafkaProducer  producer = new KafkaProducer(broker);

        // Two consumer groups — each reads ALL messages independently
        KafkaConsumer emailConsumer = new KafkaConsumer(broker, "email-group", "orders",
            msg -> System.out.println("  [EMAIL CONSUMER] " + msg.value));

        KafkaConsumer analyticsConsumer = new KafkaConsumer(broker, "analytics-group", "orders",
            msg -> System.out.println("  [ANALYTICS CONSUMER] " + msg.value));

        System.out.println("--- Publishing messages ---");
        producer.send("orders", "user-1", "Order placed: Laptop $999");
        producer.send("orders", "user-2", "Order placed: Phone $699");
        producer.send("orders", "user-1", "Order placed: Keyboard $89");
        producer.send("events", "sys",    "System heartbeat");

        System.out.println("\n--- Email consumer polls ---");
        emailConsumer.poll();

        System.out.println("\n--- Analytics consumer polls (same messages, different offset tracking) ---");
        analyticsConsumer.poll();

        System.out.println("\n--- Another message arrives ---");
        producer.send("orders", "user-3", "Order placed: Monitor $399");

        System.out.println("\n--- Email consumer polls again (only sees new message) ---");
        emailConsumer.poll();

        System.out.println("\n=== Key Kafka Concepts Demonstrated ===");
        System.out.println("  - Topic: named category of messages (like a log)");
        System.out.println("  - Offset: position of a message in the topic log");
        System.out.println("  - Consumer Group: each group independently tracks its own offset");
        System.out.println("  - Messages are NOT deleted when consumed — different groups can re-read");
        System.out.println("  - Producer → Topic ← Multiple Independent Consumer Groups");
    }
}
