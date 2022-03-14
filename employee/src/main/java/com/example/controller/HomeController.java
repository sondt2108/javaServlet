package com.example.controller;

import com.example.models.Employee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/home"}
)
public class HomeController extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee epl = new Employee();
        epl.setFullName("Dao Van A");
        epl.setId(1);
        epl.setAddress("113 Hai Ba Trung");
        epl.setPhoneNumber("0987563254");
        epl.setEmail("a@gmail.com");
        request.setAttribute("model", epl);
        RequestDispatcher rd = request.getRequestDispatcher("./views/home.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
