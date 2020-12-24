package com.example.project.dao;

import com.example.project.bean.Employees;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;

public interface EmployeeDao {
    
    //boolean emailVerify(Employees employees);
    Employees passwordVerify(Employees employees);
    void registerEmployee(Employees employees);

    Employees retriveProfile(Integer emp_id);

    int updateProfile(Employees employee);

    int uploadProfilePic(InputStream fileInputStream, FormDataContentDisposition fileMetaData);

    int updateProfilePicPath(String name,Integer emp_id);

    Employees getPhotoPath(Employees employees);
}
