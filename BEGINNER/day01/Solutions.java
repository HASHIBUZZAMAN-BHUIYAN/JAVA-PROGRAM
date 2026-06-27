/**
 * Day 01 — Solutions
 * Completed versions of all five exercises.
 * Run with: java Solutions
 */
public class Solutions {

    public static void main(String[] args) {
        System.out.println("=== Day 01 Solutions ===\n");

        exercise1();
        exercise2();
        exercise3();
        exercise4();
        exercise5();

        System.out.println("\n=== All solutions complete. ===");
    }

    // Exercise 1 — Introduce Yourself
    static void exercise1() {
        System.out.println("-- Exercise 1: Introduce Yourself --");
        String name   = "Alice";
        int    age    = 20;
        double height = 1.75;
        System.out.println("My name is " + name + ", I am " + age
                + " years old and " + height + " m tall.");
        System.out.println();
    }

    // Exercise 2 — Boolean Flags
    static void exercise2() {
        System.out.println("-- Exercise 2: Boolean Flags --");
        boolean isStudent  = true;
        boolean hasJob     = false;
        boolean likesJava  = true;
        System.out.println("isStudent : " + isStudent);
        System.out.println("hasJob    : " + hasJob);
        System.out.println("likesJava : " + likesJava);
        System.out.println();
    }

    // Exercise 3 — Character Initials
    static void exercise3() {
        System.out.println("-- Exercise 3: Initials --");
        char firstInitial  = 'J';
        char middleInitial = 'K';
        char lastInitial   = 'R';
        System.out.println("Initials: " + firstInitial + ". "
                + middleInitial + ". " + lastInitial + ".");
        System.out.println();
    }

    // Exercise 4 — Temperature Record
    static void exercise4() {
        System.out.println("-- Exercise 4: Temperature --");
        double high    = 30.5;
        double low     = 18.0;
        double average = (high + low) / 2;
        System.out.println("High: " + high + "  Low: " + low
                + "  Average: " + average);
        System.out.println();
    }

    // Exercise 5 — Product Label
    static void exercise5() {
        System.out.println("-- Exercise 5: Product Label --");
        String  productName = "Widget Pro";
        double  price       = 9.99;
        int     quantity    = 50;
        boolean inStock     = true;
        System.out.println("Product : " + productName);
        System.out.println("Price   : $" + price);
        System.out.println("Qty     : " + quantity);
        System.out.println("In Stock: " + inStock);
        System.out.println();
    }
}
