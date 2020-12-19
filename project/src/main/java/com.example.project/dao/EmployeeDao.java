package com.example.project.dao;

import com.example.project.bean.Employees;

public interface EmployeeDao {
    boolean emailVerify(Employees employees);
    boolean passwordVerify(Employees employees);
}
