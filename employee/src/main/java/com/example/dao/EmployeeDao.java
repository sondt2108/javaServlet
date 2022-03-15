package com.example.dao;

import com.example.imlp.EmployeeInterface;
import com.example.models.Departments;
import com.example.models.Employee;
import com.example.until.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeDao implements EmployeeInterface {

    static Connection connection = JdbcConnection.getConnection();

    @Override
    public List<Employee> findAll() {

        List<Employee> results = new ArrayList<>();
        String sql = "Select * from employee";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        System.out.println("dao");
        if (connection != null) {
            try {
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Employee employee = new Employee();
                    Departments departments = new Departments();
                    employee.setId(resultSet.getInt("id"));
                    employee.setFullName(resultSet.getString("fullname"));
                    employee.setAddress(resultSet.getString("address"));
                    employee.setPhoneNumber(resultSet.getString("phoneNumber"));
                    employee.setEmail(resultSet.getString("email"));
                    employee.setDepartments(resultSet.getObject("dept_id"));
                    results.add(employee);


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
