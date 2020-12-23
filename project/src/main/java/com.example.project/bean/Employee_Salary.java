package com.example.project.bean;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

@Entity
@Table(name="Employee_Salary")
public class Employee_Salary {
    @Id
    @Column(name="id")
    private String id;
    @Column(name = "payment_date", nullable = false)
    private String payment_date;
    @Column(name = "amount", nullable = false)
    private String amount;
    private String description;

    @ManyToOne
    @JoinColumn(name = "employee", nullable = false)
    private Employees employees;

    public Employee_Salary() {
    }

    public Employee_Salary(String id, String payment_date, String amount, String description) {
        this.id = id;
        this.payment_date = payment_date;
        this.amount = amount;
        this.description = description;
    }

    public Employee_Salary(String id, String payment_date, String amount, String description, Employees employees) {
        this.id = id;
        this.payment_date = payment_date;
        this.amount = amount;
        this.description = description;
        this.employees = employees;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonbTransient
    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }
}