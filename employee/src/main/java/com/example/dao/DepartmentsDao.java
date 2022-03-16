package com.example.dao;

import com.example.imlp.DepartmentsInterface;
import com.example.models.Departments;
import com.example.until.JdbcConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentsDao implements DepartmentsInterface {


    @Override
    public List<Departments> findAll() {
        List<Departments> results = new ArrayList<>();
        try {
            String sql = "Select * from departments";
            Connection connection = JdbcConnection.getConnection();
            if (connection != null) {
                PreparedStatement pst = connection.prepareStatement(sql);
                ResultSet resultSet = pst.executeQuery();
                while (resultSet.next()) {
                    Departments departments = new Departments();
                    departments.setId(resultSet.getInt("dept_id"));
                    departments.setDeptName(resultSet.getString("dept_name"));
                    results.add(departments);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return results;
    }

    @Override
    public List<Departments> findAll(int start, int total) {
        List<Departments> results = new ArrayList<>();
        try {
            String sql = "Select * from departments order by dept_id desc limit ?, ? ";
            Connection connection = JdbcConnection.getConnection();
            if (connection != null) {
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1, (start - 1) * total);
                pst.setInt(2, total);
                ResultSet resultSet = pst.executeQuery();
                while (resultSet.next()) {
                   Departments departments = new Departments();
                   departments.setId(resultSet.getInt("dept_id"));
                   departments.setDeptName(resultSet.getString("dept_name"));
                   results.add(departments);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return results;
    }

    @Override
    public boolean create(Departments departments) {
        try {
            String sql ="INSERT INTO departments(dept_name) VALUES (?)";
            Connection connection = JdbcConnection.getConnection();
            if (connection != null){
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, departments.getDeptName());
                int result = statement.executeUpdate();
                if (result > 0) {
                    return true;
                } else {
                    return false;
                }
            }

        }catch (Exception e ) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Departments departments) {
        try {
            String sql ="UPDATE departments SET dept_name= ? WHERE dept_id= ? ";
            Connection conn = JdbcConnection.getConnection();
            if (conn != null){
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, departments.getDeptName());
                statement.setInt(2, departments.getId());
                int result = statement.executeUpdate();
                if (result > 0) {
                    return true;
                } else {
                    return false;
                }
            }

        }catch (Exception e ) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Departments findById(int id) {
        Departments departments = new Departments();
        try {
            String sql = "Select * from departments Where dept_id= ?";
            Connection connection = JdbcConnection.getConnection();
            if (connection != null) {
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1, id);
                ResultSet resultSet = pst.executeQuery();
                while (resultSet.next()) {
                    departments.setId(resultSet.getInt("dept_id"));
                    departments.setDeptName(resultSet.getString("dept_name"));
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public boolean delete(int id) {
        try {
            String sql = "delete from departments Where dept_id= ?";
            Connection connection = JdbcConnection.getConnection();
            if (connection != null) {
                PreparedStatement pst = connection.prepareStatement(sql);
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
}
