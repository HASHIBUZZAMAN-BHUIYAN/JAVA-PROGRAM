/**
 * Day 11 Mini Project — Robust Input Validator
 * A form validator demonstrating exception handling patterns.
 *
 * Run: java MiniProject
 */

class ValidationException extends Exception {
    private String field;
    ValidationException(String field, String message) {
        super(message);
        this.field = field;
    }
    String getField() { return field; }
}

class UserForm {
    private String username;
    private String email;
    private int    age;

    void setUsername(String username) throws ValidationException {
        if (username == null || username.trim().isEmpty())
            throw new ValidationException("username", "Username cannot be empty");
        if (username.length() < 3)
            throw new ValidationException("username", "Username must be at least 3 characters");
        if (!username.matches("[a-zA-Z0-9_]+"))
            throw new ValidationException("username", "Username can only contain letters, numbers, underscore");
        this.username = username;
    }

    void setEmail(String email) throws ValidationException {
        if (email == null || !email.contains("@") || !email.contains("."))
            throw new ValidationException("email", "Invalid email format");
        this.email = email;
    }

    void setAge(String ageStr) throws ValidationException {
        try {
            int age = Integer.parseInt(ageStr);
            if (age < 13 || age > 120)
                throw new ValidationException("age", "Age must be between 13 and 120");
            this.age = age;
        } catch (NumberFormatException e) {
            throw new ValidationException("age", "Age must be a number");
        }
    }

    @Override
    public String toString() {
        return String.format("User[%s, %s, age=%d]", username, email, age);
    }
}

public class MiniProject {
    public static void main(String[] args) {
        System.out.println("=== User Form Validator ===\n");

        String[][] testInputs = {
            {"alice_99", "alice@example.com", "25"},    // valid
            {"", "bob@example.com", "30"},              // bad username (empty)
            {"carol", "not-an-email", "22"},            // bad email
            {"dave", "dave@test.com", "abc"},           // bad age (not a number)
            {"e", "eve@test.com", "20"},                // username too short
            {"frank!", "frank@test.com", "35"},         // bad username chars
            {"grace", "grace@test.com", "10"},          // age too low
        };

        for (String[] input : testInputs) {
            System.out.printf("Input: [%s, %s, %s]%n", input[0], input[1], input[2]);
            UserForm form = new UserForm();
            try {
                form.setUsername(input[0]);
                form.setEmail(input[1]);
                form.setAge(input[2]);
                System.out.println("  VALID: " + form);
            } catch (ValidationException e) {
                System.out.println("  ERROR [" + e.getField() + "]: " + e.getMessage());
            }
        }
    }
}
