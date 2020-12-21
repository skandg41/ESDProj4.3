package com.example.project.controller;

import com.example.project.bean.Employees;
import com.example.project.service.EmployeeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
