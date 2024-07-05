package com.skillstorm.SpringApp.services;
import com.skillstorm.SpringApp.models.Department;
import com.skillstorm.SpringApp.models.Employee;
import com.skillstorm.SpringApp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService
{
    @Autowired
    private EmployeeRepository repo;

    //get all employees (READ)
    public Iterable<Employee> getAllEmployees()
    {
        return repo.findAll();
    }

    //Get emplopyee by ID
    public ResponseEntity<Employee> getEmployeeById(int id)
    {
        if(!repo.existsById(id))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Error: This employee id does not match a record in the database")
                    .body(null);
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .header("Success: An employee has been found in the database")
                .body(repo.findById(id).get());
    }

    //create one employee (CREATE) DOUBLE CHECK CONFLICT
    public ResponseEntity<Employee> createEmployee(Employee employee)
    {

        if(repo.existsById(employee.getEmployeeId()))
        {


            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    header("Error: An employee with this ID already exists in database")
                    .body(employee);
        }

        if(!(repo.countInDepartment(employee.getDepartmentId().getDepartmentId()) < 3))
        {

             return ResponseEntity.status(HttpStatus.CONFLICT).
                header("Error: Max capacity of 25 has been reached")
                .body(employee);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Success: An employee has been added to the Database")
                .body(repo.save(employee));
    }

    //Update an Employee (UPDATE)
    public ResponseEntity<Employee> updateEmployee(int id, String firstName, String lastName, Department department)
    {
        //REMOVE COMMENT
        if(!repo.existsById(id))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Error: This employee doesn't exist in the Database").body(new Employee(id, firstName, lastName,department));

        }

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .header("Success: Employee has been updated")
                .body(repo.save(new Employee(id, firstName, lastName, department)));
    }

    // Delete Employee (DELETE)
    public void deleteEmployee(int id)
    {
        repo.deleteById(id);
    }


}
