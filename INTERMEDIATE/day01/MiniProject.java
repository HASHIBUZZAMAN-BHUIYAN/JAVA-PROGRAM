import java.util.ArrayList;
import java.util.List;

/**
 * Day 01 Mini-Project: Generic Typed Stack with History
 *
 * A fully generic stack that also tracks operation history.
 * Demonstrates: generic classes, bounded types, wildcards in practice.
 *
 * Run: java MiniProject
 */
public class MiniProject {

    // =========================================================
    // Generic Result type (like Optional but with error info)
    // =========================================================
    static class Result<T> {
        private final T value;
        private final String error;
        private final boolean success;

        private Result(T value, String error, boolean success) {
            this.value   = value;
            this.error   = error;
            this.success = success;
        }

        static <T> Result<T> ok(T value)       { return new Result<>(value, null, true); }
        static <T> Result<T> fail(String error) { return new Result<>(null, error, false); }

        public boolean isSuccess() { return success; }
        public T getValue()        { return value; }
        public String getError()   { return error; }

        @Override
        public String toString() {
            return success ? "OK(" + value + ")" : "FAIL(" + error + ")";
        }
    }

    // =========================================================
    // Generic TypedStack<T extends Comparable<T>>
    // Tracks push/pop history, supports "undo" (undo last pop)
    // =========================================================
    static class TypedStack<T extends Comparable<T>> {
        private final ArrayList<T>      data    = new ArrayList<>();
        private final ArrayList<String> history = new ArrayList<>();
        private T lastPopped = null;

        public void push(T item) {
            data.add(item);
            history.add("PUSH " + item);
        }

        public Result<T> pop() {
            if (data.isEmpty()) {
                history.add("POP  [FAILED - empty]");
                return Result.fail("Stack is empty");
            }
            lastPopped = data.remove(data.size() - 1);
            history.add("POP  " + lastPopped);
            return Result.ok(lastPopped);
        }

        public Result<T> peek() {
            if (data.isEmpty()) return Result.fail("Stack is empty");
            return Result.ok(data.get(data.size() - 1));
        }

        /** Restore last popped element */
        public boolean undoPop() {
            if (lastPopped == null) return false;
            data.add(lastPopped);
            history.add("UNDO_POP " + lastPopped);
            lastPopped = null;
            return true;
        }

        /** Find the maximum element currently in the stack */
        public Result<T> findMax() {
            if (data.isEmpty()) return Result.fail("Stack is empty");
            T max = data.get(0);
            for (T item : data) {
                if (item.compareTo(max) > 0) max = item;
            }
            return Result.ok(max);
        }

        /** Return all elements greater than the given threshold */
        public List<T> filterGreaterThan(T threshold) {
            List<T> result = new ArrayList<>();
            for (T item : data) {
                if (item.compareTo(threshold) > 0) result.add(item);
            }
            return result;
        }

        public boolean isEmpty() { return data.isEmpty(); }
        public int size()        { return data.size(); }

        public void printHistory() {
            System.out.println("  Operation History:");
            for (String entry : history) {
                System.out.println("    " + entry);
            }
        }

        @Override
        public String toString() {
            return "TypedStack" + data + " (top=" + (data.isEmpty() ? "empty" : data.get(data.size()-1)) + ")";
        }
    }

    // =========================================================
    // Utility: print stats of any TypedStack using wildcards
    // =========================================================
    static void printStats(TypedStack<?> stack) {
        System.out.println("  Stack size: " + stack.size());
        System.out.println("  Is empty:   " + stack.isEmpty());
        System.out.println("  Contents:   " + stack);
    }

    // =========================================================
    // MAIN
    // =========================================================
    public static void main(String[] args) {

        System.out.println("===== MINI-PROJECT: Generic Typed Stack with History =====\n");

        // --- Integer stack demo ---
        System.out.println("--- Integer Stack ---");
        TypedStack<Integer> intStack = new TypedStack<>();
        intStack.push(5);
        intStack.push(2);
        intStack.push(9);
        intStack.push(1);
        intStack.push(7);

        System.out.println("After pushes: " + intStack);
        System.out.println("Max: " + intStack.findMax());
        System.out.println("Elements > 4: " + intStack.filterGreaterThan(4));

        Result<Integer> r1 = intStack.pop();
        System.out.println("pop() => " + r1);

        System.out.println("Undo pop: " + intStack.undoPop());
        System.out.println("After undo: " + intStack);

        intStack.printHistory();

        // --- String stack demo ---
        System.out.println("\n--- String Stack ---");
        TypedStack<String> strStack = new TypedStack<>();
        strStack.push("delta");
        strStack.push("alpha");
        strStack.push("gamma");
        strStack.push("beta");

        System.out.println("Max: " + strStack.findMax());
        System.out.println("Elements > 'b': " + strStack.filterGreaterThan("b"));
        strStack.pop();
        strStack.pop();
        strStack.pop();
        strStack.pop();

        Result<String> failResult = strStack.pop();  // should fail
        System.out.println("Pop from empty: " + failResult);
        System.out.println("isSuccess: " + failResult.isSuccess());
        System.out.println("error: " + failResult.getError());

        strStack.printHistory();

        // --- Wildcard utility ---
        System.out.println("\n--- Generic printStats (wildcard) ---");
        TypedStack<Double> dblStack = new TypedStack<>();
        dblStack.push(1.1);
        dblStack.push(3.3);
        dblStack.push(2.2);
        printStats(dblStack);
        printStats(intStack);

        System.out.println("\n===== Mini-Project Complete! =====");
        System.out.println("Concepts demonstrated:");
        System.out.println("  - Generic class TypedStack<T extends Comparable<T>>");
        System.out.println("  - Generic Result<T> wrapper type");
        System.out.println("  - Bounded type parameter for findMax/filterGreaterThan");
        System.out.println("  - Unbounded wildcard TypedStack<?> in printStats");
    }
}
