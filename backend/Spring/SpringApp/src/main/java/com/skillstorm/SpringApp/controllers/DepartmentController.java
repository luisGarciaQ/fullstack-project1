package com.skillstorm.SpringApp.controllers;

import com.skillstorm.SpringApp.models.Department;
import com.skillstorm.SpringApp.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;

@RestController
@RequestMapping("/department")
@CrossOrigin("*")
public class DepartmentController
{
    @Autowired
    DepartmentService service;

    @GetMapping
    public Iterable<Department> getAllDepartments()
    {
        return service.getAllDepartments();
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department)
    {
        return service.createDepartment(department);
    }

    @PutMapping
    public ResponseEntity<Department> updateDepartment(@RequestParam int id, @RequestParam String departmentName)
    {
        return service.updateDepartment(id, departmentName);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable int id)
    {
        service.deleteDepartment(id);
    }
}
