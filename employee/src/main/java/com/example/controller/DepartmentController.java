package com.example.controller;

import com.example.models.Departments;
import com.example.service.DepartmentsSevice;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/home"}
)
public class DepartmentController extends HttpServlet {

    private DepartmentsSevice departmentsSevice;

public DepartmentController() {
    departmentsSevice = new DepartmentsSevice();
}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Departments dept = new Departments();

        request.setAttribute("dept", departmentsSevice.findAll());
        System.out.println(departmentsSevice.findAll());
        RequestDispatcher rd = request.getRequestDispatcher("./views/home.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
