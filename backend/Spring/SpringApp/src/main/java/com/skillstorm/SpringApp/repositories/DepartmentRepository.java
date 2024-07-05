package com.skillstorm.SpringApp.repositories;

import com.skillstorm.SpringApp.models.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer>
{
}
