package com.example.controller;

import com.example.models.Employee;
import com.example.service.EmployeeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/list-employee")
public class EmployeeController extends HttpServlet {

    private EmployeeService employeeService;

    public EmployeeController(){
        employeeService = new EmployeeService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee epl = new Employee();
        request.setAttribute("epl", employeeService.findAll());
        System.out.println(employeeService.findAll());
        RequestDispatcher rd = request.getRequestDispatcher("./views/employee.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
