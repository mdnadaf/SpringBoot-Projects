package example.employee.restemployeeboot.service;

import java.util.List;

import example.employee.restemployeeboot.entity.Employee;

// Service Interface
// Declares all business methods
public interface EmployeeService {

    // Get All Employees
    List<Employee> getAllEmployees();

    // Get Employee By ID
    Employee getEmployeeById(int id);

    // Add New Employee
    Employee addEmployee(Employee employee);

    // Update Existing Employee
    Employee updateEmployee(Employee employee, int id);

    // Delete Employee
    void deleteEmployee(int id);

    List<Employee> saveAllEmployees(List<Employee> employees);

}