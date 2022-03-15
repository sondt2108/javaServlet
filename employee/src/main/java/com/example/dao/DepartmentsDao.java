package com.example.dao;

import com.example.imlp.DepartmentsInterface;
import com.example.models.Departments;
import com.example.until.JdbcConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentsDao implements DepartmentsInterface {

    static Connection connection = JdbcConnection.getConnection();

    @Override
    public List<Departments> findAll() {
        List<Departments> results = new ArrayList<>();
        String sql = "Select * from departments";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        if (connection != null) {
            try {
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Departments departments = new Departments();
                    departments.setId(resultSet.getInt("dept_id"));
                    departments.setDeptName(resultSet.getString("dept_name"));
                    results.add(departments);
                }

                return results;
            }catch (SQLException e) {
                return null;
            }finally {
                try{
                    if (connection != null) {
                        connection.close();
                    }

                    if (statement != null) {
                        connection.close();
                    }

                    if (resultSet != null) {
                        resultSet.close();
                    }
                }catch (SQLException e) {
                    return null;
                }
            }
        }
        return  results;
    }
}
