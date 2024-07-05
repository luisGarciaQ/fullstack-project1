package com.skillstorm.SpringApp.repositories;

import com.skillstorm.SpringApp.models.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>
{
    @Query(value = "SELECT COUNT(*) FROM company.employee WHERE department_id = :departmentId", nativeQuery = true)
    int countInDepartment(@Param("departmentId") int departmentId);
}
