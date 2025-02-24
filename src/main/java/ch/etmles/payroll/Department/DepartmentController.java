package ch.etmles.payroll.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    // Récupérer tous les départements
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Récupérer un département par son nom
    @GetMapping("/{name}")
    public Department getDepartmentByName(@PathVariable String name) {
        Optional<Department> department = departmentRepository.findById(name);
        if (department.isPresent()) {
            return department.get();
        } else {
            throw new DepartmentNotFoundException(name);
        }
    }

    // Créer un nouveau département
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Department createDepartment(@RequestBody Department department) {
        return departmentRepository.save(department);
    }

    // Modifier un département
    @PutMapping("/{name}")
    public Department updateDepartment(@PathVariable String name, @RequestBody Department departmentDetails) {
        Department department = departmentRepository.findById(name)
                .orElseThrow(() -> new DepartmentNotFoundException(name));
        department.setName(departmentDetails.getName());
        return departmentRepository.save(department);
    }

    // Supprimer un département
    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable String name) {
        Department department = departmentRepository.findById(name)
                .orElseThrow(() -> new DepartmentNotFoundException(name));
        departmentRepository.delete(department);
    }
}
