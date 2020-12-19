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
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginStudent(Employees employees) throws URISyntaxException {
        System.out.println("hello");
        if(employeeService.verifyEmail(employees) && employeeService.verifyPassword(employees)){
            return Response.ok().build();
        }else{
            return Response.status(203).build();
        }
    }



}
