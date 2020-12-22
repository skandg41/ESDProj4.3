package com.example.project.controller;

import com.example.project.bean.Employee_Salary;
import com.example.project.service.EmployeeSalaryService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

public class SalaryController {

    EmployeeSalaryService employeeSalaryService = new EmployeeSalaryService();

    @POST
    @Path("/view_salary")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchSalary(Employee_Salary employee_salary)throws URISyntaxException {
        Employee_Salary result=employeeSalaryService.retriveSalary(employee_salary.getEmployees().getEmp_id(), employee_salary.getPayment_date());
        if(result == null)
            return Response.noContent().build();
        System.out.println("Get Response"+result.getId());
        System.out.println(result.getPayment_date());
        System.out.println(result.getAmount());
        System.out.println(result.getDescription());
        return Response.ok().entity(result).build();
    }
}
