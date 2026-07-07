package example.employee.restemployeeboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.employee.restemployeeboot.entity.Employee;
import example.employee.restemployeeboot.repository.EmployeeRepository;

// Marks this class as Service Layer
@Service
public class EmployeeServiceImpl implements EmployeeService {

    // Inject Repository Object
    @Autowired
    private EmployeeRepository employeeRepository;

    // ============================
    // Get All Employees
    // ============================
    @Override
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();

    }

    // ============================
    // Get Employee By Id
    // ============================
    @Override
    public Employee getEmployeeById(int id) {

        return employeeRepository.findById(id).orElse(null);

    }

    // ============================
    // Add Employee
    // ============================
    @Override
    public Employee addEmployee(Employee employee) {

        return employeeRepository.save(employee);

    }

    // =============================
    // Save All Employee at a time
    // =============================

    @Override
    public java.util.List<Employee> saveAllEmployees(java.util.List<Employee> employees) {
        java.util.List<Employee> validEmployees = new java.util.ArrayList<>();

        for (Employee emp : employees) {
            // 1. Check karo ki database me yeh email pehle se hai ya nahi
            boolean emailExistsInDb = employeeRepository.existsByEmail(emp.getEmail());

            // 2. Check karo ki current list me yeh email humne abhi toh add nahi kiya
            // (payload ke andar ka duplicate)
            boolean emailExistsInCurrentBatch = validEmployees.stream()
                    .anyMatch(e -> e.getEmail().equalsIgnoreCase(emp.getEmail()));

            if (!emailExistsInDb && !emailExistsInCurrentBatch) {
                validEmployees.add(emp);
            } else {
                System.out.println("Skipping duplicate email: " + emp.getEmail());
            }
        }

        // Spring Data JPA ka built-in batch method use karenge
        return employeeRepository.saveAll(validEmployees);
    }

    // ============================
    // Update Employee
    // ============================
    @Override
    public Employee updateEmployee(Employee employee, int id) {

        Employee oldEmployee = employeeRepository.findById(id).orElse(null);

        if (oldEmployee != null) {

            oldEmployee.setName(employee.getName());
            oldEmployee.setEmail(employee.getEmail());
            oldEmployee.setDepartment(employee.getDepartment());
            oldEmployee.setSalary(employee.getSalary());

            return employeeRepository.save(oldEmployee);

        }

        return null;

    }

    // ============================
    // Delete Employee
    // ============================
    @Override
    public void deleteEmployee(int id) {

        employeeRepository.deleteById(id);

    }

}