package com.example.service;

import com.example.models.Employee;

import java.util.List;

public interface EmployeeServiceInterface {

    List<Employee> findAll();
    List<Employee> findAll(int start, int total);
    boolean create(Employee employee);
    boolean update(Employee employee);
    boolean delete(int id);
    Employee findById(int id);
    int searchEmployee(String txtSearch);
    List<Employee> search(String txtSearch, int start, int total);
 }
