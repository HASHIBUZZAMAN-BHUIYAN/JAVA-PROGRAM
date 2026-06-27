/**
 * Day 14 — Capstone Solutions (hints/approach for exercises)
 */
public class Solutions {
    public static void main(String[] args) {
        System.out.println("""
            Exercise 1 — Most checked out book:
              Add 'private int checkoutCount = 0;' to Book.
              Increment in Library.checkout(). Track max with a stream.

            Exercise 2 — Filter by author:
              return catalog.stream()
                .filter(b -> b.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());

            Exercise 3 — Waitlist:
              In Library: Map<Integer, Queue<Integer>> waitlists = new HashMap<>();
              In checkout(): if unavailable, waitlists
                .computeIfAbsent(bookId, k -> new LinkedList<>()).offer(memberId);
              In returnBook(): poll() next member from queue if present.

            Exercise 4 — Sorted by title:
              catalog.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(System.out::println);

            Exercise 5 — Total download size:
              Add 'private double fileSizeMB;' to EBook.
              totalDownloadSizeMB: catalog.stream()
                .filter(b -> b instanceof EBook && b.isAvailable())
                .mapToDouble(b -> ((EBook)b).getFileSizeMB())
                .sum();
            """);
    }
}
