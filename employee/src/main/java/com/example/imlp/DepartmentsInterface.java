package com.example.imlp;

import com.example.models.Departments;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface DepartmentsInterface {
    List<Departments> findAll();
    List<Departments> findAll(int start, int total);
    boolean create(Departments departments);
    boolean update(Departments departments);
    Departments findById(int id);
    boolean delete(int id);
}
