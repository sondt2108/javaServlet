package com.example.controller;

import com.example.models.Departments;
import com.example.service.DepartmentsService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/home"}
)
public class DepartmentController extends HttpServlet {

    private DepartmentsService departmentsService;

public DepartmentController() {
    departmentsService = new DepartmentsService();
}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Departments dept = new Departments();

        String id = request.getParameter("id");
        System.out.println("id:" + id);
        String page = request.getParameter("page");
        System.out.println("page" +page);
        int currentPage = 1;
        if (page != null) {
            currentPage = Integer.parseInt(page);
            request.setAttribute("dept", departmentsService.findAll(currentPage, 6));
            request.setAttribute("currentPage", currentPage);

        } else {
            request.setAttribute("dept", departmentsService.findAll(1, 6));
            request.setAttribute("currentPage", currentPage);
        }



        if (id != null) {
            request.setAttribute("detailDept", departmentsService.findById(Integer.parseInt(id)));
        }

        RequestDispatcher rd = request.getRequestDispatcher("./views/home.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        System.out.println(action);

        if (action != null) {
            switch (action) {
                case "create":
                    String deptName = request.getParameter("deptName");
                    Departments departments = new Departments(deptName);
                    boolean result = departmentsService.create(departments);
                    response.sendRedirect(request.getContextPath() + "/home");
                    break;
                case "update":
                    String deptId = request.getParameter("id");
                    if (deptId != null) {
                        String name = request.getParameter("deptName");
                        Departments dept = new Departments(Integer.parseInt(deptId), name);
                        boolean res = departmentsService.update(dept);
                        System.out.println("update");
                        response.sendRedirect(request.getContextPath() + "/home");
                    }
                    break;
                case "delete":
                    String id = request.getParameter("id");
                    System.out.println("id:" + id);

                    if (id != null) {
                        request.setAttribute("detailDept", departmentsService.delete(Integer.parseInt(id)));
                    }
                    response.sendRedirect(request.getContextPath() + "/home");
                    break;
            }
        }
    }


}
