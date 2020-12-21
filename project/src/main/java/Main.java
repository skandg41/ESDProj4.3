import com.example.project.bean.Employee_Salary;
import com.example.project.bean.Employees;
import com.example.project.bean.PayDate;
import com.example.project.utils.SessionUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Employees employees = new Employees();
        //employees.setEmployee_id("MT2020079");
        employees.setFirst_name("Neeraj");
        employees.setLast_name("Jetha");
        employees.setEmail("neeraj.jetha@iiitb.org");
        employees.setPassword("123456789");
        employees.setTitle("SDE");

        Employee_Salary employee_salary=new Employee_Salary();
        PayDate payment_date = new PayDate(1,1,2020);
        //employee_salary.setEmployee_id("MT2020079");
        employee_salary.setAmount("2.14L");
        //employee_salary.setPayment_date(payment_date);
        employee_salary.setDescription("Basic-2L , Allowances= 18000, Deductions-4000");
        employee_salary.setEmployees(employees);

        Employee_Salary employee_salary1=new Employee_Salary();
        PayDate payment_date1 = new PayDate(1,1,2020);
        //employee_salary1.setEmployee_id("MT2020079");
        employee_salary1.setAmount("2.22L");
        //employee_salary1.setPayment_date(payment_date);
        employee_salary1.setDescription("Basic-3L , Allowances= 17000, Deductions-4000");
        employee_salary1.setEmployees(employees);

//        Configuration configuration = new Configuration();
//        configuration.configure();
//        SessionFactory ourSessionFactory = configuration.buildSessionFactory();
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.save(employee_salary);
        session.save(employees);
        tx.commit();

    }
}