package com.example.project.controller;

import com.example.project.bean.Employees;
import com.example.project.service.EmployeeService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.hibernate.annotations.SQLUpdate;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.URISyntaxException;

@Path("employee")
public class EmployeeController {
    EmployeeService employeeService=new EmployeeService();
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginEmployee(Employees employees) throws URISyntaxException {
        System.out.println("Login Request Received");
        System.out.println(employees.getEmail());
        System.out.println(employees.getPassword());
        Employees result = employeeService.verifyPassword(employees);
        if(result == null){
            return Response.noContent().build();
        }
        System.out.println("Login Response "+ result.getEmp_id());
        System.out.println(result.getEmail());
        System.out.println(result.getPassword());
        return Response.ok().entity(result).build();
    }

    @POST
    @Path("/Profile")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProfile(Employees employee) throws URISyntaxException{
        System.out.println("Update Profile Req Received");
        int result = employeeService.UpdateProfile(employee);
        if(result == 0){
            return Response.noContent().build();
        }
        System.out.println("Update Response "+ employee.getFirst_name()+" Updated Profile");
        return Response.ok().entity(result).build();
    }

    @POST
    @Path("/uploadImage")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public Response uploadImage(  @FormDataParam("file") InputStream fileInputStream,
                                    @FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception {
        System.out.println("File Upload Initiated at Controller");

        String currentDirectory = System.getProperty("user.dir");
        System.out.println("The current working directory is " + currentDirectory);

        int result = employeeService.uploadProfilePic(fileInputStream, fileMetaData);
        if (result == 0) {
            System.out.println("File Upload Failed");
            return Response.noContent().build();
        }
        System.out.println("File Upload Response OK ");
        return Response.ok().build();
    }

    @POST
    @Path("/retriveImage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.MULTIPART_FORM_DATA})
    public Response retriveImage(){
        System.out.println("Image fetch req received");

        File file = new File("/home/skand/Documents/MT2020029.jpeg");

        return Response.ok((Object) file).header("Content-Disposition", "attachment; filename=\"MT2020029.jpeg\"").build();

    }


    @POST
    @Path("/get_details")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchEmployee(Employees employees) throws URISyntaxException{
        System.out.println("Fetch Request Received from Employee "+ employees.getEmp_id());
        Employees result = employeeService.retriveProfile(employees.getEmp_id());
        if(result == null){
            return Response.noContent().build();
        }
        System.out.println("Get Response "+ result.getEmp_id());
        System.out.println(result.getEmail());
        System.out.println(result.getPassword());
        return Response.ok().entity(result).build();
    }

}
