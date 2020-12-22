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

            Query query = session.createQuery("from Employee_Salary where employees.emp_id=:emp_id and payment_date like :month");
            query.setParameter("month",payment_date);
            query.setParameter("emp_id",emp_id);
            for (final Object fetch : query.list()) {
                return (Employee_Salary) fetch;
            }
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }
        return null;
    }
}
