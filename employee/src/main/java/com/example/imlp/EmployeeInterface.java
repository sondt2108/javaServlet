package com.example.imlp;

import com.example.models.Employee;

import java.util.List;

public interface EmployeeInterface {

    List<Employee> findAll();
    List<Employee> findAll(int start, int total);
    boolean create(Employee employee);
    boolean update(Employee employee);
    Employee findById(int id);
    boolean delete(int id);

}
