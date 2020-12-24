package com.example.project.service;


import com.example.project.bean.Employees;
import com.example.project.dao.EmployeeDao;
import com.example.project.dao.EmployeeDaoImpl;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;

public class EmployeeService {
    EmployeeDao employeeDao= new EmployeeDaoImpl() ;
    /*public boolean verifyEmail(Employees employees){
        return employeeDao.emailVerify(employees);
    }*/
    public Employees verifyPassword(Employees employees){ return employeeDao.passwordVerify(employees);}

    public Employees retriveProfile(Integer emp_id) { return  employeeDao.retriveProfile(emp_id);    }

    public int UpdateProfile(Employees employee) { return employeeDao.updateProfile(employee);    }

    public int uploadProfilePic(InputStream fileInputStream, FormDataContentDisposition fileMetaData, Integer emp_id) {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("The current working directory is " + currentDirectory);

        int upload_status = employeeDao.uploadProfilePic(fileInputStream,fileMetaData);

        if(upload_status==1) {
            int upload = employeeDao.updateProfilePicPath(fileMetaData.getFileName(),emp_id);
            if(upload != 1) return 1;
        }
        return 0;
    }

    public Employees getPhotoPath(Employees employees) { return employeeDao.getPhotoPath(employees);    }

}
