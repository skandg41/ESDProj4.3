package com.example.project.controller;

import com.example.project.bean.Employee_Salary;
import com.example.project.service.EmployeeSalaryService;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

@Path("salary")
public class SalaryController {

    EmployeeSalaryService employeeSalaryService = new EmployeeSalaryService();

    @POST
    @Path("/view_salary")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchSalary(JsonObject jsonObject)throws URISyntaxException {
        System.out.println("Salary Controller reached");
        System.out.println("Id "+ jsonObject.get("employee"));
        System.out.println("date "+ jsonObject.get("payment_date"));
        System.out.println("date String "+ jsonObject.get("payment_date").toString());
        Employee_Salary result= employeeSalaryService.retriveSalary(jsonObject.get("employee").toString(),jsonObject.get("payment_date").toString());
        if(result == null) {
            System.out.println("No entry found");
            return Response.noContent().build();
        }
        System.out.println("G0t Response "+result.getId());
        System.out.println(result.getPayment_date());
        System.out.println(result.getAmount());
        System.out.println(result.getDescription());
        Response response = Response.ok().entity(result).type(MediaType.APPLICATION_JSON).build();
        System.out.println(response.getStatus());
        System.out.println(response.getEntity().getClass().toString());
        System.out.println(response.getMediaType());
         return response;
    }
}
