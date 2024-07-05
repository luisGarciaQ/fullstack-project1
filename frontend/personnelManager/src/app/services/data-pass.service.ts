import { Injectable } from '@angular/core';
import { Employee } from '../models/employee';
import { Department } from '../models/department';
import { BehaviorSubject } from 'rxjs';
import { HttpService } from './http.service';

@Injectable({
  providedIn: 'root'
})
export class DataPassService 
{

  constructor(private httpService: HttpService) 
  { 
    this.getAllDepartments()
  }

  favEmployee : Employee = new Employee(0,"","", new Department(0, ""))
  departmentList: Department[] = [];
  favEmployeeSubject = new BehaviorSubject<Employee>(this.favEmployee);
  departmentListSubject = new BehaviorSubject<Department[]>(this.departmentList)

  updateFavoriteEmployee(employee: Employee)
  {
    this.favEmployee = employee;
    this.favEmployeeSubject.next(this.favEmployee);
  }

  getAllDepartments()
   {
    this.httpService.getAllDepartments().subscribe(response =>
      {
        let body: any = response.body;
        let id: number;
        let name; String;

        for(let item of body)
          {
            id = item.departmentId;
            name = item.departmentName;

            this.departmentList.push(new Department(id, name));
          }
      })

    }
}
