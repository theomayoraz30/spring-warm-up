package ch.etmles.payroll.Controllers;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(String name) {
        super("Could not find department with name: " + name);
    }
}
