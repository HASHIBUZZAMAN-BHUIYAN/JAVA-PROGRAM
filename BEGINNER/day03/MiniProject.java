/**
 * Day 03 — Mini Project: Grade & Season Classifier
 *
 * Applies: if/else-if, switch expressions, ternary operator.
 *
 * Classifies student grades, seasons, day types, and traffic-light meanings.
 *
 * Run: java MiniProject
 */
public class MiniProject {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║     GRADE & SEASON CLASSIFIER        ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        // ── Part 1: Student Grade Report ───────────────────────────────────
        int[] scores = {95, 83, 71, 65, 42};
        String[] names = {"Alice", "Bob", "Carol", "Dave", "Eve"};

        System.out.println("=== Student Grade Report ===");
        System.out.printf("%-10s %-6s %-7s %-12s%n", "Name", "Score", "Grade", "Status");
        System.out.println("─".repeat(38));

        for (int i = 0; i < scores.length; i++) {
            int score = scores[i];

            String grade = switch (score / 10) {
                case 10, 9 -> "A";
                case 8     -> "B";
                case 7     -> "C";
                case 6     -> "D";
                default    -> "F";
            };

            String status = (score >= 60) ? "PASS" : "FAIL";

            String remark;
            if (score >= 90) {
                remark = "Excellent";
            } else if (score >= 80) {
                remark = "Good";
            } else if (score >= 70) {
                remark = "Satisfactory";
            } else if (score >= 60) {
                remark = "Needs effort";
            } else {
                remark = "Retake needed";
            }

            System.out.printf("%-10s %-6d %-7s %-12s (%s)%n",
                    names[i], score, grade, status, remark);
        }
        System.out.println();

        // ── Part 2: Season & Month Info ────────────────────────────────────
        System.out.println("=== Monthly Info ===");
        String[] months = {"January", "April", "July", "October", "February"};

        for (String month : months) {
            String season = switch (month) {
                case "December", "January", "February" -> "Winter ❄";
                case "March",    "April",   "May"      -> "Spring 🌸";
                case "June",     "July",    "August"   -> "Summer ☀";
                case "September","October", "November" -> "Autumn 🍂";
                default -> "Unknown";
            };

            int days = switch (month) {
                case "January","March","May","July","August","October","December" -> 31;
                case "April","June","September","November"                        -> 30;
                case "February"                                                   -> 28;
                default -> 0;
            };

            System.out.printf("%-12s → %-15s  %d days%n", month, season, days);
        }
        System.out.println();

        // ── Part 3: Traffic Light Interpreter ──────────────────────────────
        System.out.println("=== Traffic Light Interpreter ===");
        String[] lights = {"red", "yellow", "green", "purple"};

        for (String light : lights) {
            String action = switch (light) {
                case "red"    -> "STOP — wait for green.";
                case "yellow" -> "CAUTION — prepare to stop.";
                case "green"  -> "GO — proceed safely.";
                default       -> "UNKNOWN signal — stop and assess.";
            };
            System.out.println(light.toUpperCase() + ": " + action);
        }
        System.out.println();

        // ── Part 4: Day Type Classifier ────────────────────────────────────
        System.out.println("=== Day Type Classifier ===");
        int[] dayNums = {1, 3, 6, 7, 5};
        for (int d : dayNums) {
            String dayName = switch (d) {
                case 1 -> "Monday";
                case 2 -> "Tuesday";
                case 3 -> "Wednesday";
                case 4 -> "Thursday";
                case 5 -> "Friday";
                case 6 -> "Saturday";
                case 7 -> "Sunday";
                default -> "Invalid";
            };
            boolean isWeekend = (d == 6 || d == 7);
            String type = isWeekend ? "Weekend" : "Weekday";
            System.out.println("Day " + d + " (" + dayName + ") → " + type);
        }
        System.out.println();

        System.out.println("=== Classifier complete ===");
    }
}
