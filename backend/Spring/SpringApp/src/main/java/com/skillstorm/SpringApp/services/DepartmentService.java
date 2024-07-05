package com.skillstorm.SpringApp.services;

import com.skillstorm.SpringApp.models.Department;
import com.skillstorm.SpringApp.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService
{
    @Autowired
    private DepartmentRepository repo;

    // GET all Departments
    public Iterable<Department> getAllDepartments()
    {
        return repo.findAll();
    }

    // CREATE a Department
    public ResponseEntity<Department> createDepartment(Department department)
    {
        if(repo.existsById(department.getDepartmentId()))
        {
            System.out.println("Error: Department Already Exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Error: Department Already Exists").body(department);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Success: Department has been created")
                .body(repo.save(department));

    }

    //UPDATE Department
    public ResponseEntity<Department> updateDepartment(int id, String departmentName)
    {
        if(!repo.existsById(id))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Error: Department does not exist in database").body(new Department(id, departmentName, null));
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).header("Success: Department has been updated").
                body(repo.save(new Department(id, departmentName, repo.findById(id).get().getEmployees())));
    }


    // Delete Department
    public void deleteDepartment(int id)
    {
        repo.deleteById(id);
    }
}
