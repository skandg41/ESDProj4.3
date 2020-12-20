package com.example.project.dao;

import com.example.project.bean.Employees;

public interface EmployeeDao {
    //boolean emailVerify(Employees employees);
    Employees passwordVerify(Employees employees);
    void registerEmployee(Employees employees);
}
