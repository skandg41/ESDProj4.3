package com.example.project.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class PayDate {

    @Column(name = "date",nullable = false)
    @Size(min = 1,max = 30)
    private int date;

    @Column(name = "month",nullable = false)
    @Size(min=1,max = 12)
    private int month;

    @Column(name = "year",nullable = false)
    @Size(min = 1980,max = 2050)
    private int year;

    public PayDate(int date, int month, int year) {
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public PayDate() {
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
