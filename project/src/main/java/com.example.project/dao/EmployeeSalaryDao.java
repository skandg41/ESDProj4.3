package com.example.project.dao;

import com.example.project.bean.Employee_Salary;

public interface EmployeeSalaryDao {
    Employee_Salary retriveSalary(Integer emp_id, String payment_date);
}
