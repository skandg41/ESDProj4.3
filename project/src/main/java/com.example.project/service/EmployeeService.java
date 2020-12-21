package com.example.project.service;

import com.example.project.bean.Employees;
import com.example.project.dao.EmployeeDao;
import com.example.project.dao.EmployeeDaoImpl;

public class EmployeeService {
    EmployeeDao employeeDao=new EmployeeDaoImpl() ;
    /*public boolean verifyEmail(Employees employees){
        return employeeDao.emailVerify(employees);
    }*/
    public Employees verifyPassword(Employees employees){ return employeeDao.passwordVerify(employees);}

    public Employees retriveProfile(Integer emp_id) { return  employeeDao.retriveProfile(emp_id);    }

    public int UpdateProfile(Employees employee) { return employeeDao.updateProfile(employee);    }
}
