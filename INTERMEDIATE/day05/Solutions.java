/**
 * INTERMEDIATE Day 05 — File I/O — Solutions
 */
import java.nio.file.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.*;

public class Solutions {

    static long countLines(Path file) throws IOException {
        return Files.lines(file).count();
    }

    static List<Integer> findWord(Path file, String word) throws IOException {
        List<String> lines = Files.readAllLines(file);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains(word)) result.add(i + 1);
        }
        return result;
    }

    static List<Map<String,String>> readCsv(Path file) throws IOException {
        List<String> lines = Files.readAllLines(file);
        if (lines.isEmpty()) return List.of();
        String[] headers = lines.get(0).split(",");
        return lines.stream().skip(1).map(line -> {
            String[] vals = line.split(",");
            Map<String,String> row = new LinkedHashMap<>();
            for (int i = 0; i < headers.length; i++)
                row.put(headers[i].trim(), i < vals.length ? vals[i].trim() : "");
            return row;
        }).collect(Collectors.toList());
    }

    static void copyTxtFiles(Path source, Path dest) throws IOException {
        Files.createDirectories(dest);
        Files.walk(source)
            .filter(p -> p.toString().endsWith(".txt"))
            .forEach(p -> {
                try { Files.copy(p, dest.resolve(p.getFileName()), StandardCopyOption.REPLACE_EXISTING); }
                catch (IOException e) { e.printStackTrace(); }
            });
    }

    public static void main(String[] args) throws IOException {
        Path test = Path.of("test_solutions.txt");
        Files.write(test, List.of("Hello world", "Java is fun", "Hello again", "Bye"));
        System.out.println("Line count: " + countLines(test));
        System.out.println("'Hello' at lines: " + findWord(test, "Hello"));
        Files.delete(test);
    }
}
