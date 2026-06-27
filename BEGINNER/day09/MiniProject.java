/**
 * Day 09 Mini Project — Employee Payroll System
 * Demonstrates inheritance with different employee types.
 *
 * Run: java MiniProject
 */

class Employee {
    protected String name;
    protected String id;

    Employee(String name, String id) {
        this.name = name;
        this.id   = id;
    }

    double calculatePay() { return 0; }

    void printPayslip() {
        System.out.printf("%-20s (ID: %s)  Pay: $%.2f%n",
                          name, id, calculatePay());
    }
}

class SalariedEmployee extends Employee {
    private double annualSalary;

    SalariedEmployee(String name, String id, double annualSalary) {
        super(name, id);
        this.annualSalary = annualSalary;
    }

    @Override
    double calculatePay() { return annualSalary / 12; } // monthly
}

class HourlyEmployee extends Employee {
    private double hourlyRate;
    private int    hoursWorked;

    HourlyEmployee(String name, String id, double hourlyRate, int hoursWorked) {
        super(name, id);
        this.hourlyRate   = hourlyRate;
        this.hoursWorked  = hoursWorked;
    }

    @Override
    double calculatePay() {
        // Overtime (>40 hrs) paid at 1.5x
        if (hoursWorked <= 40)
            return hoursWorked * hourlyRate;
        return 40 * hourlyRate + (hoursWorked - 40) * hourlyRate * 1.5;
    }
}

class CommissionEmployee extends Employee {
    private double baseSalary;
    private double salesAmount;
    private double commissionRate;

    CommissionEmployee(String name, String id,
                       double baseSalary, double salesAmount, double commissionRate) {
        super(name, id);
        this.baseSalary     = baseSalary;
        this.salesAmount    = salesAmount;
        this.commissionRate = commissionRate;
    }

    @Override
    double calculatePay() {
        return baseSalary + salesAmount * commissionRate;
    }
}

public class MiniProject {
    public static void main(String[] args) {
        Employee[] staff = {
            new SalariedEmployee("Alice Smith",  "E001", 72000),
            new HourlyEmployee("Bob Jones",      "E002", 25.50, 45),
            new HourlyEmployee("Carol Davis",    "E003", 18.00, 40),
            new CommissionEmployee("Dave Wilson","E004", 2000, 15000, 0.08),
        };

        System.out.println("=== Monthly Payroll ===");
        System.out.println("-".repeat(55));

        double total = 0;
        for (Employee e : staff) {
            e.printPayslip();
            total += e.calculatePay();
        }

        System.out.println("-".repeat(55));
        System.out.printf("Total payroll this month: $%.2f%n", total);
    }
}
