package com.example.project.dao;

import com.example.project.bean.Employees;
import com.example.project.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class EmployeeDaoImpl implements EmployeeDao{
     @Override
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
    }

    @Override
    public boolean passwordVerify(Employees employees) {
        Session session = SessionUtil.getSession();
        try {
//
            Query query = session.createQuery("from Employees where email=:email and password=:password");
            System.out.println(employees.getEmail());
            System.out.println("----------------------");
            query.setParameter("email", employees.getEmail());
            query.setParameter("password",employees.getPassword());
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

    }
}