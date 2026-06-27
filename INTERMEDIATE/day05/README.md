# INTERMEDIATE Day 05 — File I/O with java.nio.file

## Objectives
- Use `Path` and `Files` from `java.nio.file` (modern Java file API)
- Read and write text files
- Walk directory trees
- Work with CSV-style data using file I/O

## Key Types
- `Path path = Path.of("data.txt")` — file/directory path (doesn't need to exist)
- `Files.readAllLines(path)` — read all lines as `List<String>`
- `Files.writeString(path, content)` — write a string to a file
- `Files.write(path, lines)` — write a list of strings
- `Files.walk(path)` — stream all entries in a directory tree
- `Files.exists(path)`, `Files.createDirectories(path)`

## How to Compile and Run
```
javac *.java
java Lesson
```

## Time Estimate
60 minutes
