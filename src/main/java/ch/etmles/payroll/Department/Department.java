package ch.etmles.payroll.Department;

import ch.etmles.payroll.Employee.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Department {

    @Id
    private String name;

    @OneToMany(mappedBy = "department")
    @JsonIgnore  // Cela va ignorer la sérialisation de la liste des employés dans Department
    private List<Employee> employees;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department department)) return false;
        return Objects.equals(this.name, department.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    @Override
    public String toString() {
        return "Department{" + "name='" + this.name + '\'' + '}';
    }
}
