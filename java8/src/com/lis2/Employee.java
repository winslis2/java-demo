package com.lis2;

import javax.swing.*;
import java.util.Objects;

public class Employee {
    private String name;
    private Integer id;
    private Double salary;


    public Employee(String name, Integer id, Double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getSalary() {
        return salary;
    }


    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Employee employee = (Employee) o;
        return Objects.equals (name, employee.name) &&
                Objects.equals (id, employee.id) &&
                Objects.equals (salary, employee.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash (name, id, salary);
    }
}
