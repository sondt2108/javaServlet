package com.example.service;

import com.example.models.Departments;

import java.util.List;

public interface DepartmentsServiceInterface {
    List<Departments> findAll();
    List<Departments> findAll(int start, int total);
    boolean create(Departments departments);
    boolean update(Departments departments);
    Departments findById(int id);
    boolean delete(int id);
}
