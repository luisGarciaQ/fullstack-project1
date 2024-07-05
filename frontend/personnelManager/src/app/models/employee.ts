import { Department } from "./department";

export class Employee 
{
    employeeId: number;
    employeeFirstName: string;
    employeeLastName: string;
    departmentId: Department;

    constructor(employeeId: number, employeeFirstName: string,employeeLastName: string,department: Department)
    {
        this.employeeId = employeeId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.departmentId = department;
    }
}
