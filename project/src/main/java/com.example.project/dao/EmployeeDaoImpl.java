package com.example.project.dao;

import com.example.project.bean.Employees;
import com.example.project.utils.SessionUtil;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.*;

public class EmployeeDaoImpl implements EmployeeDao{
    /* @Override
    public boolean emailVerify(Employees employees) {
        Session session = SessionUtil.getSession();
        try {
//
            Query query = session.createQuery("from Employees where email=:email");
            System.out.println(employees.getEmail());
            System.out.println("----------------------");
            query.setParameter("email", employees.getEmail());
            if (query.getResultList().size() == 1) {
                return true;
            }
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return false;
        } finally {
            session.close();
        }
        return false;
    }*/

    @Override
    public Employees passwordVerify(Employees employees) {

        try (Session session = SessionUtil.getSession()) {

            Query query = session.createQuery("from Employees where email=:email and password=:password");
            System.out.println("Login Req from "+ employees.getEmail());
            query.setParameter("email", employees.getEmail());
            query.setParameter("password",employees.getPassword());
            for (final Object fetch : query.list()) {
                return (Employees) fetch;
            }
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }
        return null;
    }
    @Override
    public int updateProfile(Employees employees){
        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "update Employees set first_name=:first_name, last_name=:last_name, email=:email, password=:password, title=:title where emp_id=:emp_id";
            Query query = session.createQuery(hql);
            query.setParameter("emp_id",employees.getEmp_id());
            query.setParameter("first_name", employees.getFirst_name());
            query.setParameter("last_name",employees.getLast_name());
            query.setParameter("email",employees.getEmail());
            query.setParameter("password",employees.getPassword());
            query.setParameter("title",employees.getTitle());
            int result = query.executeUpdate();
            transaction.commit();
            return result;

        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return 0;
        }
    }

    @Override
    public int updateProfilePicPath(String name){
        return 1;
    }

    @Override
    public int uploadProfilePic(InputStream fileInputStream, FormDataContentDisposition fileMetaData){
        String UPLOAD_PATH = "/media/skand/New Volume/IIITB/Term 1 20-21/SS/Part 2/Project/ESDProj4.3/project/src/main/webapp/assets/userImg/";
        try
        {
            String currentDirectory = System.getProperty("user.dir");
            System.out.println("The current working directory is " + currentDirectory);

            int read = 0;
            byte[] bytes = new byte[4096];
            System.out.println("File Upload at location "+ UPLOAD_PATH + fileMetaData.getFileName());
            OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + fileMetaData.getFileName()));
            while ((read = fileInputStream.read(bytes)) != -1)
            {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e)
        {
            System.out.println("Exception at Dao");
            System.out.println(e);
            throw new WebApplicationException("Error while uploading file. Please try again !!");
        }
        return 1;
    }
    @Override
    public Employees retriveProfile(Integer emp_id){
        try (Session session = SessionUtil.getSession()) {

            Query query = session.createQuery("from Employees where emp_id=:emp_id");
            query.setParameter("emp_id",emp_id);
            for (final Object fetch : query.list()) {
                return (Employees) fetch;
            }
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }
        return null;
    }

    @Override
    public void registerEmployee(Employees employees) {

        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employees);
            transaction.commit();

        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
        }
    }
}