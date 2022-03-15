package com.example.imlp;

import com.example.models.Departments;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface DepartmentsInterface {
    List<Departments> findAll();
}
