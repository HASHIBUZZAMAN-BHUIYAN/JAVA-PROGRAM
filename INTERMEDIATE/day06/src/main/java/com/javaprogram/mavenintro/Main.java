package com.javaprogram.mavenintro;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;
import com.google.common.collect.HashMultiset;

import java.util.List;

/**
 * INTERMEDIATE Day 06 — Maven Introduction
 *
 * This class uses Google Guava (declared in pom.xml as a dependency).
 * Maven downloads Guava from Maven Central on first 'mvn compile'.
 *
 * Run: mvn exec:java
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== INTERMEDIATE Day 06: Maven Introduction ===\n");

        // ── Google Guava: Joiner ─────────────────────────────────────────────
        System.out.println("--- Guava Joiner ---");
        List<String> fruits = ImmutableList.of("apple", "banana", "cherry");

        // Without Guava: String.join(", ", fruits)
        // With Guava: Joiner gives more control (skip nulls, custom separators)
        String joined = Joiner.on(", ").join(fruits);
        System.out.println("Joined: " + joined);

        String withNulls = Joiner.on(" | ").skipNulls().join("first", null, "third");
        System.out.println("Skip nulls: " + withNulls);
        System.out.println();

        // ── Google Guava: Splitter ───────────────────────────────────────────
        System.out.println("--- Guava Splitter ---");
        String csv = "  apple , banana , , cherry , date  ";

        // Standard String.split doesn't trim or skip blanks easily
        Iterable<String> parts = Splitter.on(",")
            .trimResults()
            .omitEmptyStrings()
            .split(csv);
        System.out.print("Split: ");
        parts.forEach(p -> System.out.print("[" + p + "] "));
        System.out.println("\n");

        // ── Google Guava: Multiset (bag — counts occurrences) ─────────────────
        System.out.println("--- Guava Multiset ---");
        Multiset<String> wordBag = HashMultiset.create();
        String sentence = "the cat sat on the mat the cat";
        for (String word : sentence.split(" ")) wordBag.add(word);

        wordBag.elementSet().stream()
            .sorted()
            .forEach(w -> System.out.println("  '" + w + "' appears " + wordBag.count(w) + " time(s)"));
        System.out.println();

        System.out.println("--- Maven Key Concepts ---");
        System.out.println("pom.xml: groupId + artifactId + version uniquely identify any artifact");
        System.out.println("         dependencies are downloaded from Maven Central (search.maven.org)");
        System.out.println("Build lifecycle: validate → compile → test → package → install → deploy");
        System.out.println("'mvn package' creates a JAR in target/ folder");
        System.out.println("'mvn dependency:tree' shows all your dependencies and their deps");

        System.out.println("\n=== End of Day 06 ===");
    }
}
