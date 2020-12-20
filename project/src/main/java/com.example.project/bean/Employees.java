package com.example.project.bean;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer emp_id;

    public String getPhotograph_path() {
        return photograph_path;
    }

    public void setPhotograph_path(String photograph_path) {
        this.photograph_path = photograph_path;
    }

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    @Column(name="first_name",nullable = false)
    private  String first_name;
    @Column(name="last_name")
    private String last_name;
    @Column(name="email",nullable = false,unique = true)
    private String email;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "title")
    private String title;
    @Column(name="photograph_path")
    private String photograph_path;

    @OneToMany(mappedBy = "employees")
    private List<Employee_Salary> employee_salaries;

    public Employees(){}

    public Employees(String first_name, String last_name, String email,String password, String title, String photograph_path) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.title = title;
        this.photograph_path = photograph_path;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonbTransient
    public List<Employee_Salary> getEmployee_salaries() {
        return employee_salaries;
    }

    public void setEmployee_salaries(List<Employee_Salary> employee_salaries) {
        this.employee_salaries = employee_salaries;
    }
}
