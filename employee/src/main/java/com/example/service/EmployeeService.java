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

    @Override
    public List<Employee> findAll(int start, int total) {

        return employeeDao.findAll(start, total);
    }

    @Override
    public boolean create(Employee employee) {

        boolean result = employeeDao.create(employee);

        return result;
    }

    @Override
    public boolean update(Employee employee) {

        boolean result = employeeDao.update(employee);

        return result;
    }

    @Override
    public Employee findById(int id) {


        return employeeDao.findById(id);
    }

    @Override
    public boolean delete(int id) {

        return employeeDao.delete(id);
    }

    @Override
    public int searchEmployee(String txtSearch) {

        return employeeDao.countEmployee(txtSearch);
    }

    @Override
    public List<Employee> search(String txtSearch, int start, int total) {

        return employeeDao.search(txtSearch, start, total);
    }
}
