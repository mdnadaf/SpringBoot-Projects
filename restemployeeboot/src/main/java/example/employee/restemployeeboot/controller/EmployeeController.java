package example.employee.restemployeeboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import example.employee.restemployeeboot.entity.Employee;
import example.employee.restemployeeboot.service.EmployeeService;

// Marks this class as REST Controller
@RestController

// Base URL for all APIs
@RequestMapping("/employees")
public class EmployeeController {

    // Inject Service Object
    @Autowired
    private EmployeeService employeeService;

    // ==========================================
    // GET ALL EMPLOYEES
    // URL : GET /employees
    // ==========================================
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {

        List<Employee> employees = employeeService.getAllEmployees();

        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // ==========================================
    // GET EMPLOYEE BY ID
    // URL : GET /employees/1
    // ==========================================
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id) {

        Employee employee = employeeService.getEmployeeById(id);

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // ==========================================
    // ADD NEW EMPLOYEE
    // URL : POST /employees
    // ==========================================
    @PostMapping("/bulk")
    public ResponseEntity<List<Employee>> addEmployeesBulk(@RequestBody List<Employee> employees) {

        // Service layer ka naya method call karenge jo list accept karega
        List<Employee> savedEmployees = employeeService.saveAllEmployees(employees);

        return new ResponseEntity<>(savedEmployees, HttpStatus.CREATED);
    }

    // ==========================================
    // UPDATE EMPLOYEE
    // URL : PUT /employees/1
    // ==========================================
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,
            @PathVariable int id) {

        Employee updatedEmployee = employeeService.updateEmployee(employee, id);

        if (updatedEmployee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    // ==========================================
    // DELETE EMPLOYEE
    // URL : DELETE /employees/1
    // ==========================================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {

        Employee employee = employeeService.getEmployeeById(id);

        if (employee == null) {
            return new ResponseEntity<>("Employee Not Found", HttpStatus.NOT_FOUND);
        }

        employeeService.deleteEmployee(id);

        return new ResponseEntity<>("Employee Deleted Successfully", HttpStatus.OK);
    }

}