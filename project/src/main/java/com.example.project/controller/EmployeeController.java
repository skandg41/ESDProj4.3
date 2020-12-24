package com.example.project.controller;

import com.example.project.bean.Employees;
import com.example.project.service.EmployeeService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.InputStream;
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
                                    @FormDataParam("file") FormDataContentDisposition fileMetaData, @FormDataParam("emp_id") Integer emp_id) throws Exception {
        System.out.println("File Upload Initiated at Controller for emp "+ emp_id);

        String currentDirectory = System.getProperty("user.dir");
        System.out.println("The current working directory is " + currentDirectory);

        int result = employeeService.uploadProfilePic(fileInputStream, fileMetaData, emp_id);
        if (result == 0) {
            System.out.println("File Upload Failed");
            return Response.noContent().build();
        }
        System.out.println("File Upload Response OK ");
        return Response.ok().build();
    }
  /*  @GET
    @Path("/retriveImage")
    public Response getImage() {
        File f = new File("/home/Document/MT2020029.jpeg");

        if (!f.exists()) {
            throw new WebApplicationException(404);
        }

        String mt = new MimetypesFileTypeMap().getContentType(f);
        return Response.ok(f, mt).build();
    }
    @POST
    @Path("/retriveImage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("image/jpeg")
    //@Produces({MediaType.MULTIPART_FORM_DATA})
    public Response retriveImage(Employees employees){
        System.out.println("Image fetch req received");
        String path = employeeService.getPhotoPath(employees).getPhotograph_path();
        System.out.println("Photograph at "+path);
        File file = new File(path);
        String filename = file.getName();
        System.out.println("FileName is "+ filename);
        return Response.ok().entity(filename).build();
    }*/

    /*@GET
    @Path("/dowImage")
    @Produces("image/*")
    public Response dowImage(){
        File file = new File("/home/skand/Documents/MT2020029.jpeg");
        String filename = file.getName();
        System.out.println("FileName is "+filename);
        return Response.ok((File) file).header("Content-Disposition", "attachment; filename=/"+filename).build();
    }*/

    @GET
    @Path("/images/{filename}")
    @Produces("image/*")
    public Response getImage(@PathParam("filename") String filename) {
        System.out.println("Fetch image Request Received");
        String path = "/home/skand/Documents/A-ERPUsers/" + filename;
        File f = new File(path);
        if (!f.exists()) {
            throw new WebApplicationException(404);
        }
        String mt = new MimetypesFileTypeMap().getContentType(f);
        return Response.ok(f, mt).build();
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
        System.out.println(result.getPhotograph_path());
        if( result.getPhotograph_path() != null)
        {
            File file = new File(result.getPhotograph_path());
            System.out.println("File Name " + file.getName());
            result.setPhotograph_path(file.getName());
        }
        else result.setPhotograph_path("null");
        return Response.ok().entity(result).build();
    }

}
