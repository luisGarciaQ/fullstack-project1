package com.skillstorm.SpringApp.controllers;

import com.skillstorm.SpringApp.models.Department;
import com.skillstorm.SpringApp.models.Employee;
import com.skillstorm.SpringApp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*")
public class EmployeeController
{
    @Autowired
    private EmployeeService service;

    //Controller to get all employees
    @GetMapping
    public Iterable<Employee> getAllEmployees()
    {
        return service.getAllEmployees();

    }

    //Controller to find an employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id)
    {
        return service.getEmployeeById(id);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee)
    {
        return service.createEmployee(employee);
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestParam int id, @RequestParam String firstName,@RequestParam String lastName, @RequestBody Department department)
    {
        return service.updateEmployee(id, firstName, lastName, department);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id)
    {
        service.deleteEmployee(id);
    }
}
