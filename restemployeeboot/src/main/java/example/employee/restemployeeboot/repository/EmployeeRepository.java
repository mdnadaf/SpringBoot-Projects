package example.employee.restemployeeboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import example.employee.restemployeeboot.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    boolean existsByEmail(String email);
}