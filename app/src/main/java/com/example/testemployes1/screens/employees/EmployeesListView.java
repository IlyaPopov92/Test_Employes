package com.example.testemployes1.screens.employees;

import com.example.testemployes1.pojo.Employee;

import java.util.List;


public interface EmployeesListView {
    void showData (List<Employee> employees);
    void showError();

    }

