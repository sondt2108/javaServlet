package com.example.service;

import com.example.dao.DepartmentsDao;
import com.example.models.Departments;

import java.util.List;

public class DepartmentsSevice implements DepartmentsServiceInterface {

    private DepartmentsDao departmentsDao;

    public DepartmentsSevice() {
        departmentsDao = new DepartmentsDao();
    }

    @Override
    public List<Departments> findAll() {


        return departmentsDao.findAll();
    }
}
