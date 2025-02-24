package ch.etmles.payroll;

import ch.etmles.payroll.Department.Department;
import ch.etmles.payroll.Employee.Employee;
import ch.etmles.payroll.Department.DepartmentRepository;
import ch.etmles.payroll.Employee.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        return args -> {
            // Création et enregistrement des départements
            Department itDepartment = new Department("IT");
            Department financeDepartment = new Department("Finance");
            departmentRepository.save(itDepartment);
            departmentRepository.save(financeDepartment);

            log.info("Preloading " + departmentRepository.save(itDepartment));
            log.info("Preloading " + departmentRepository.save(financeDepartment));

            // Création et enregistrement des employés avec leurs départements respectifs
            Employee bilbo = new Employee("Bilbo", "Baggins", "Burglar", itDepartment);
            Employee frodo = new Employee("Frodo", "Baggins", "Thief", financeDepartment);

            log.info("Preloading " + employeeRepository.save(bilbo));
            log.info("Preloading " + employeeRepository.save(frodo));
        };
    }
}
