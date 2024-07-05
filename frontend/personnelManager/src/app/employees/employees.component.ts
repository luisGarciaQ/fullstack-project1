import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { HttpService } from '../services/http.service';
import { Employee } from '../models/employee';
import { Department } from '../models/department';
import { DataPassService } from '../services/data-pass.service';
import { FormsModule } from '@angular/forms';
import { CreateEmployeeFormComponent } from '../create-employee-form/create-employee-form.component';
import { Router } from '@angular/router';


@Component({
  selector: 'app-employees',
  standalone: true,
  imports: [CommonModule, FormsModule, CreateEmployeeFormComponent],
  templateUrl: './employees.component.html',
  styleUrl: './employees.component.css'
})
export class EmployeesComponent 
{
  employees: Employee[] = [];
  formEmployeeId: number = 0;
  departments: any;
  
  

  constructor(private httpService: HttpService, private dataPass: DataPassService, private router: Router)
  {
    this.getAllEmployees();
  }

  getAllEmployees()
  {
    this.httpService.getAllEmployees().subscribe(response =>
      {
            this.employees = [];
            let body: any = response.body;
            let depId: number;
            let depName: string;
            let dep : Department;
            let emp : Employee;
            

            for(let item of body)
              {
               depId = item.departmentId.departmentId;
               depName = item.departmentId.departmentName;

               dep = new Department(depId, depName);
               emp = new Employee(item.employeeId, item.employeeFirstName, item.employeeLastName, dep);
              this.employees.push(emp);
              
              }
      })
  }

  getEmployeeById()
  {
    this.httpService.getEmployeeById(this.formEmployeeId)
    .subscribe(data =>
      {
        let body: any = data.body;
        let depId: number;
        let depName: string;
        let dep : Department;
        let emp : Employee;
        this.employees = [];

        depId = body.departmentId.departmentId;
        depName = body.departmentId.departmentName;
        dep = new Department(depId, depName)
        emp = new Employee(body.employeeId, body.employeeFirstName,body.employeeLastName,dep);
        this.employees.push(emp);
      });
  }

  formEmployee: Employee = new Employee(0, "", "", new Department(0, ""));
  createEmployee()
  {
    this.httpService.createEmployee(this.formEmployee).subscribe(data =>
    {
      this.getAllEmployees()
    });
  }
  updateEmployee(index: number)
  {
        this.dataPass.updateFavoriteEmployee(this.employees[index])
        this.router.navigate([""])
        this.getAllEmployees();
       
  }
  deleteEmployee(id: number)
  {
    this.httpService.deleteEmployee(id).subscribe(data =>
      {this.getAllEmployees();}
    );
  }

  handleCreateEmployee(employee: Employee)
  {
    this.formEmployee = employee;
    this.createEmployee();
  }

  openEditEmployeeForm(employee: Employee)
  {
    
  }

}
