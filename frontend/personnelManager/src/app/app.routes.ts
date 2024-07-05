import { Component } from '@angular/core';
import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { EmployeesComponent } from './employees/employees.component';

export const routes: Routes = 
[
    {   
        path:'',
        component: HomeComponent
    },

    {
        path: 'employees',
        component: EmployeesComponent
    }

];
