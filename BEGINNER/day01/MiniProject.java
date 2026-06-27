/**
 * Day 01 — Mini Project: Personal Info Card
 *
 * Applies: variables, primitive types, String, System.out.println
 *
 * This program builds and displays a nicely formatted personal information
 * card using only the concepts from Day 01.
 *
 * Run with: java MiniProject
 */
public class MiniProject {

    public static void main(String[] args) {

        // ── Person data ────────────────────────────────────────────────────
        String  firstName   = "Jordan";
        String  lastName    = "Rivera";
        int     age         = 22;
        char    gender      = 'M';
        double  heightCm    = 178.5;
        double  weightKg    = 73.2;
        String  city        = "Austin";
        String  country     = "USA";
        String  occupation  = "Student";
        boolean isEmployed  = false;
        double  gpa         = 3.7;
        char    gradeLevel  = 'S';   // S = Sophomore
        int     creditHours = 45;
        boolean hasCar      = true;
        String  email       = "jordan.rivera@example.com";

        // ── Computed values ────────────────────────────────────────────────
        double heightM = heightCm / 100.0;
        // BMI = weight(kg) / height(m)^2
        double bmi     = weightKg / (heightM * heightM);
        // Round BMI to 1 decimal place using simple math
        double bmiRounded = Math.round(bmi * 10.0) / 10.0;

        String fullName = firstName + " " + lastName;

        // ── Print the card ─────────────────────────────────────────────────
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║         PERSONAL INFO CARD           ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ Name       : " + padRight(fullName, 24) + "║");
        System.out.println("║ Age        : " + padRight(age + " years", 24) + "║");
        System.out.println("║ Gender     : " + padRight(String.valueOf(gender), 24) + "║");
        System.out.println("║ Location   : " + padRight(city + ", " + country, 24) + "║");
        System.out.println("║ Email      : " + padRight(email, 24) + "║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║         PHYSICAL STATS               ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ Height     : " + padRight(heightCm + " cm", 24) + "║");
        System.out.println("║ Weight     : " + padRight(weightKg + " kg", 24) + "║");
        System.out.println("║ BMI        : " + padRight(bmiRounded + "", 24) + "║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║         ACADEMIC INFO                ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ Occupation : " + padRight(occupation, 24) + "║");
        System.out.println("║ Grade      : " + padRight(String.valueOf(gradeLevel) + " (Sophomore)", 24) + "║");
        System.out.println("║ GPA        : " + padRight(gpa + " / 4.0", 24) + "║");
        System.out.println("║ Credits    : " + padRight(creditHours + " hours completed", 24) + "║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║         STATUS FLAGS                 ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ Employed   : " + padRight(String.valueOf(isEmployed), 24) + "║");
        System.out.println("║ Has Car    : " + padRight(String.valueOf(hasCar), 24) + "║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    /**
     * Helper: pad a string with spaces on the right to reach the target width.
     * This is defined here as a static method — we'll learn methods properly on Day 05.
     */
    static String padRight(String text, int width) {
        if (text.length() >= width) return text.substring(0, width);
        StringBuilder sb = new StringBuilder(text);
        while (sb.length() < width) sb.append(' ');
        return sb.toString();
    }
}
