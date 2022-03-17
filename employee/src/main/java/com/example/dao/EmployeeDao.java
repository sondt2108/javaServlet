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

public class EmployeeDao implements EmployeeInterface {



    @Override
    public List<Employee> findAll() {

        List<Employee> results = new ArrayList<>();
        String sql = "Select * from employee";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = JdbcConnection.getConnection();
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
                    employee.setDept_id(resultSet.getInt("dept_id"));
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

    @Override
    public List<Employee> findAll(int start, int total) {

        List<Employee> results = new ArrayList<>();
        try {
            String sql = "Select * from employee order by id desc limit ?, ? ";
            Connection connection = JdbcConnection.getConnection();
            if (connection != null) {
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1, (start - 1) * total);
                pst.setInt(2, total);
                ResultSet resultSet = pst.executeQuery();
                while (resultSet.next()) {
                    Employee employee = new Employee();
                    Departments departments = new Departments();
                    employee.setId(resultSet.getInt("id"));
                    employee.setFullName(resultSet.getString("fullname"));
                    employee.setAddress(resultSet.getString("address"));
                    employee.setPhoneNumber(resultSet.getString("phoneNumber"));
                    employee.setEmail(resultSet.getString("email"));
                    employee.setDept_id(resultSet.getInt("dept_id"));
                    results.add(employee);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return results;
    }

    @Override
    public List<Employee> search(String txtSearch, int start, int total) {
        List<Employee> results = new ArrayList<>();
        try {
            String sql = "SELECT * FROM employee WHERE fullname LIKE ? ORDER BY id DESC LIMIT ?,?";
            Connection connection = JdbcConnection.getConnection();
            if (connection != null) {
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, "%" + txtSearch +"%");
                pst.setInt(2, (start - 1) * total);
                pst.setInt(3, total);
                ResultSet resultSet = pst.executeQuery();
                while (resultSet.next()) {
                    Employee employee = new Employee();
                    Departments departments = new Departments();
                    employee.setId(resultSet.getInt("id"));
                    employee.setFullName(resultSet.getString("fullname"));
                    employee.setAddress(resultSet.getString("address"));
                    employee.setPhoneNumber(resultSet.getString("phoneNumber"));
                    employee.setEmail(resultSet.getString("email"));
                    employee.setDept_id(resultSet.getInt("dept_id"));
                    results.add(employee);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return results;
    }

    @Override
    public boolean create(Employee employee) {
            try {
                String sql = "INSERT INTO employee(fullname, address, phoneNumber, email, dept_id) VALUES (?, ?, ?, ?, ?)";
                Connection connection = JdbcConnection.getConnection();
                if (connection != null) {
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1, employee.getFullName());
                    pst.setString(2, employee.getAddress());
                    pst.setString(3, employee.getPhoneNumber());
                    pst.setString(4, employee.getEmail());
                    pst.setInt(5, employee.getDept_id());

                    int result = pst.executeUpdate();
                    if (result > 0) {
                        return true;
                    } else {
                        return false;
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        return false;
    }

    @Override
    public boolean update(Employee employee) {
        try {
            String sql = "UPDATE employee SET fullname= ?,address= ?,phoneNumber= ?,email= ?,dept_id= ? WHERE id = ?";
            Connection connection = JdbcConnection.getConnection();
            if (connection != null) {
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, employee.getFullName());
                pst.setString(2, employee.getAddress());
                pst.setString(3, employee.getPhoneNumber());
                pst.setString(4, employee.getEmail());
                pst.setInt(5, employee.getDept_id());
                pst.setInt(6, employee.getId());

                int result = pst.executeUpdate();
                if (result > 0) {
                    return true;
                } else {
                    return false;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Employee findById(int id) {
        Employee employee = new Employee();
        try {
            String sql = "SELECT * from employee e INNER JOIN departments de on e.dept_id = de.dept_id WHERE e.id = ?";
            Connection connection = JdbcConnection.getConnection();
            if (connection != null){
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1, id);
                ResultSet resultSet = pst.executeQuery();
                while (resultSet.next()){
                    employee.setId(resultSet.getInt("e.id"));
                    employee.setFullName(resultSet.getString("e.fullname"));
                    employee.setAddress(resultSet.getString("e.address"));
                    employee.setPhoneNumber(resultSet.getString("e.phoneNumber"));
                    employee.setEmail(resultSet.getString("e.email"));
                    employee.setDept_id(resultSet.getInt("de.dept_id"));
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public boolean delete(int id) {
        try {
            String sql = "delete from employee Where id = ?";
            Connection conn = JdbcConnection.getConnection();
            if (conn != null) {
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, id);
                int result = pst.executeUpdate();
                if (result > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public int countEmployee(String txtSearch) {
        try {
            String sql = "SELECT COUNT(*) FROM employee WHERE fullname LIKE ?";
            Connection conn = JdbcConnection.getConnection();
            if (conn != null){
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, "%" + txtSearch +"%");
                ResultSet resultSet = pst.executeQuery();
                while (resultSet.next()){

                    return resultSet.getInt(1);
                }
            }
        }catch (Exception e) {

        }
        return 0;
    }
}
