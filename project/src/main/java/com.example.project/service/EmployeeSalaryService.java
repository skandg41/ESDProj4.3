package com.example.project.service;

import com.example.project.bean.Employee_Salary;
import com.example.project.dao.EmployeeSalaryDao;
import com.example.project.dao.EmployeeSalaryDaoImpl;


public class EmployeeSalaryService {

    EmployeeSalaryDao employeeSalaryDao = new EmployeeSalaryDaoImpl();

    public Employee_Salary retriveSalary(String emp_id, String payment_date) {
        System.out.println("In Salary Service");
        System.out.println("Id" + emp_id);
        System.out.println("date" + payment_date);
        String payDate = payment_date.substring(1, payment_date.length() - 1);
        Employee_Salary result;
        try {
            result = employeeSalaryDao.retriveSalary(Integer.parseInt(emp_id), payDate);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return result;
    }
}