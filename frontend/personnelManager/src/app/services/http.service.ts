import { HttpClient, HttpErrorResponse, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Employee } from '../models/employee';
import { Department } from '../models/department';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient) 
  {}

  url: String = "http://localhost:8080";

  //GET request to get all employees
  getAllEmployees(): Observable<HttpResponse<any>>
  {
    return this.http.get( this.url + "/employee" , {observe: "response"})
    .pipe(catchError(this.handleError));
  }

  //GET request to get an employees by ID
  getEmployeeById(id: number)
  {
    return this.http.get(this.url + "/employee/" + id, {observe: "response"});
  }

  //POST request to create an employee
  createEmployee(employee: Employee)
  {
    return this.http.post(this.url + "/employee",
      employee,{observe : "response"})
  }

  //PUT requeset to update an employee
  updateEmployee(id: number, firstName: string, lastName: string, dep : Department)
  {
    let params = new HttpParams().set('id',id).set('firstName',firstName).set('lastName',lastName);

    return this.http.put(this.url + "/employee", 
        { "departmentId": dep.departmentId,
          "departmentName": dep.departmentName
        }, {observe: 'response',
            params: params
        })
  }

  //DELETE request to delete an employee
  deleteEmployee(id: number)
  {
    return this.http.delete(this.url + "/employee/" + id, {observe : "response"})
  }

  getAllDepartments()
  {
    return this.http.get(this.url+"/department" , {observe: "response"})
  }

  private handleError(error: HttpErrorResponse): Observable<never>
  {
    let errorMessage = "This department has reached maximum capacity of 3 employees";

    return throwError(errorMessage)
  }
}
