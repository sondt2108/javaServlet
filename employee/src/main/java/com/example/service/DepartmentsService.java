package com.example.service;

import com.example.dao.DepartmentsDao;
import com.example.models.Departments;

import java.util.List;

public class DepartmentsService implements DepartmentsServiceInterface {

    private DepartmentsDao departmentsDao;

    public DepartmentsService() {
        departmentsDao = new DepartmentsDao();
    }

    @Override
    public List<Departments> findAll() {


        return departmentsDao.findAll();
    }

    @Override
    public List<Departments> findAll(int start, int total) {


        return departmentsDao.findAll( start,total);
    }

    @Override
    public boolean create(Departments departments) {

        boolean result = departmentsDao.create(departments);


        return result ;
    }

    @Override
    public boolean update(Departments departments) {
        boolean result = departmentsDao.update(departments);

        return result;
    }

    @Override
    public Departments findById(int id) {

        return departmentsDao.findById(id);
    }

    @Override
    public boolean delete(int id) {
        boolean result = departmentsDao.delete(id);

        return result;
    }
}
