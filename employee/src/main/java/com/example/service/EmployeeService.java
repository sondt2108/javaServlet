package com.example.service;

import com.example.dao.EmployeeDao;
import com.example.models.Employee;

import java.util.List;

public class EmployeeService implements EmployeeServiceInterface{

    private EmployeeDao employeeDao;

    public EmployeeService() {
        employeeDao = new EmployeeDao();
    }

    @Override
    public List<Employee> findAll() {
        System.out.println("service");
        return employeeDao.findAll();
    }
}
