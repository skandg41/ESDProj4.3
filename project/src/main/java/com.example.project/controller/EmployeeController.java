package com.example.project.controller;

import com.example.project.bean.Employees;
import com.example.project.service.EmployeeService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    public Response loginStudent(Employees employees) throws URISyntaxException {
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



}
