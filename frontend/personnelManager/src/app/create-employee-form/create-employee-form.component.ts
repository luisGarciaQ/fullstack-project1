import { Component, EventEmitter, Output } from '@angular/core';
import { Department } from '../models/department';
import { Employee } from '../models/employee';
import { FormsModule } from '@angular/forms';
import { HttpService } from '../services/http.service';
import { NgFor } from '@angular/common';
import { DataPassService } from '../services/data-pass.service';

@Component({
  selector: 'app-create-employee-form',
  standalone: true,
  imports: [FormsModule, NgFor],
  templateUrl: './create-employee-form.component.html',
  styleUrl: './create-employee-form.component.css'
})
export class CreateEmployeeFormComponent 
{
  constructor(private httpService: HttpService, private dataPassService: DataPassService)
  {
    this.dataPassService.departmentListSubject.subscribe(data =>
      {
        this.departments = data;
      })
  }

  @Output() createEmployeeEvent = new EventEmitter<Employee>();

   employeeId: number = 0;
   employeeFirstName: string= '';
   employeeLastName: string = '';
   departments: Department[] = [];
   index: number = 0;
   
   createThisEmployee()
   {
    let employee = new Employee(this.employeeId, this.employeeFirstName, this.employeeLastName, this.departments[this.index])
     console.log(this.index)
     this.createEmployeeEvent.emit(employee)
   }

   



}
