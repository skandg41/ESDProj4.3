package com.example.project.dao;

import com.example.project.bean.Employee_Salary;
import com.example.project.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;


public class EmployeeSalaryDaoImpl implements EmployeeSalaryDao{


    @Override
    public Employee_Salary retriveSalary(Integer emp_id, String payment_date)
    {
        try (Session session = SessionUtil.getSession()) {
            System.out.println("In salaey Dao");
            Query query = session.createQuery("from Employee_Salary where employees.emp_id=:emp_id and payment_date like :month");
            System.out.println("4297315");
            query.setParameter("month",payment_date);
            System.out.println("date "+ payment_date);
            System.out.println("123456");
            query.setParameter("emp_id",emp_id);
            System.out.println("Id "+ emp_id);
            System.out.println("987654");
            //System.out.println(query.getQueryString());
            //System.out.println((new Employee_Salary(query.getFirstResult())).getAmount);
            //return query.getSingleResult().;
            System.out.println(query.list().isEmpty());
            try
            {
            for (final Object fetch : query.list()) {
                System.out.println("Here");
                Employee_Salary emp = (Employee_Salary) fetch;
                System.out.println(emp.getAmount());

                //Employees ee = (Employees) fetch;
                //System.out.println(ee.getEmail());
                return (Employee_Salary) fetch;
            }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }
        return null;
    }
}
