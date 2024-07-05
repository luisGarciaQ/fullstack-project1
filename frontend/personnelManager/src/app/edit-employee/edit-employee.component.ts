import { Component } from '@angular/core';
import { DataPassService } from '../services/data-pass.service';
import { Employee } from '../models/employee';
import { Department } from '../models/department';
import { FormsModule } from '@angular/forms';
import { NgFor } from '@angular/common';
import { NgForm } from '@angular/forms';
import { HttpService } from '../services/http.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-employee',
  standalone: true,
  imports: [FormsModule, NgFor],
  templateUrl: './edit-employee.component.html',
  styleUrl: './edit-employee.component.css'
})
export class EditEmployeeComponent 
{
  currEmployee : Employee = new Employee(0,"" ,"" , new Department(0,''));
  currDepartments: Department[] = [];
  index: number = 0;
  employeeId: number = 0;
  employeeFirstName: string= '';
  employeeLastName: string = '';
  dep: Department = new Department(0, '');
  departments: Department[] = [];

  constructor(private dataPassService: DataPassService, private httpServer: HttpService, private router: Router)
  {
    this.dataPassService.favEmployeeSubject.subscribe(data =>
      {
        this.currEmployee = data;
      })

     this.dataPassService.departmentListSubject.subscribe(data =>
      {
        this.currDepartments = data;
      }) 
  }

  updateEmployee(form: NgForm)
  {
    if(form.valid)
      {
        console.log(form.value)
        this.httpServer.updateEmployee(form.value.employeeId, form.value.employeeFirstName, form.value.employeeLastName, this.currDepartments[form.value.index]).subscribe(data =>
          {
            this.reRoute();
          })
      }
  }

  reRoute()
  {
    this.router.navigate(["employees"])

  }
}
