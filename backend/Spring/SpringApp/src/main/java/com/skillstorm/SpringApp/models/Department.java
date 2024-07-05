package com.skillstorm.SpringApp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

/**
 *  Model - the current representation of the data in the request
 *  View - the actual data being seen, in this case, sent to the front end
 *  Controller - any functionality that adjusts or manipulates that data
 */

// this class is a model for a database record din the department table
// one instance of this class = one database record

@Entity
@Table(name = "department")
public class Department 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // Says that the DB must generate this value
	@Column(name = "department_id")   // Which column in the DB
	private int departmentId;
	
	@Column(name = "department_name")
	private String departmentName;

	@OneToMany(mappedBy = "departmentId", fetch = FetchType.EAGER)
	@JsonIgnoreProperties("department")
	private List<Employee> employees;


	public Department() {
	}

	public Department(int departmentId, String departmentName, List<Employee> employees) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.employees = employees;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
