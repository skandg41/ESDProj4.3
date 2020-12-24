package com.example.project.utils;

import com.example.project.bean.Employees;
import com.example.project.dao.EmployeeDao;
import com.example.project.dao.EmployeeDaoImpl;

public class AddingData {

    public static void main(String[] args) {
        Employees employees = new Employees("Clark", "Kent", "ck@gmail.com", "12345", "Professor", null);
        EmployeeDao employeeDao = new EmployeeDaoImpl();

        employeeDao.registerEmployee(employees);

    }
}
