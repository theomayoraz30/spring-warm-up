package ch.etmles.payroll.Controllers;

import ch.etmles.payroll.Entities.Employee;
import ch.etmles.payroll.Repositories.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository){
        this.repository = repository;
    }

    @GetMapping("/employees")
    List<Employee> all(){
        return repository.findAll();
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee){
        // Ensure that both firstName and lastName are provided
        if (newEmployee.getFirstName() == null || newEmployee.getLastName() == null) {
            throw new IllegalArgumentException("Both first and last names are required");
        }
        return repository.save(newEmployee);
    }

    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setFirstName(newEmployee.getFirstName());
                    employee.setLastName(newEmployee.getLastName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(500).body("Could not delete employee (not found) " + id);
        }
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
