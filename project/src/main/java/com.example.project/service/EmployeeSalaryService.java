package com.example.project.service;

import com.example.project.bean.Employee_Salary;
import com.example.project.dao.EmployeeSalaryDao;
import com.example.project.dao.EmployeeSalaryDaoImpl;


public class EmployeeSalaryService {

    EmployeeSalaryDao employeeSalaryDao = new EmployeeSalaryDaoImpl();

    public Employee_Salary retriveSalary(Integer emp_id, String payment_date) { return employeeSalaryDao.retriveSalary(emp_id,payment_date); }
}
