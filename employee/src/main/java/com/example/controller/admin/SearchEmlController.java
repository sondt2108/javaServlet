package com.example.controller.admin;

import com.example.service.DepartmentsService;
import com.example.service.EmployeeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/search-employee")
public class SearchEmlController extends HttpServlet {

    private EmployeeService employeeService;
    private DepartmentsService departmentsService;

    public SearchEmlController(){
        employeeService = new EmployeeService();
        departmentsService = new DepartmentsService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = request.getParameter("page");
        System.out.println("page" +page);

        String txtSearch = request.getParameter("txtSearch");
        System.out.println("------------------------------------------txtSearch"+txtSearch);

        System.out.println("------------------------------------------Page"+page);

        int res = employeeService.searchEmployee(txtSearch);
        int pageSize = 2;
        int endPage = 0;
        endPage = res/pageSize;
        if (res % pageSize != 0){
            endPage++;
        }

        request.setAttribute("endPage", endPage);

        int currentPage = 1;
        if (page != null) {
            currentPage = Integer.parseInt(page);
            System.out.println("-----------------------------currentPage"+ currentPage);
            request.setAttribute("epl", employeeService.search(txtSearch,currentPage, 2));
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("txtSearch", txtSearch);

        } else {
            request.setAttribute("epl", employeeService.search(txtSearch,1, 2));
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("txtSearch", txtSearch);
        }

        RequestDispatcher rd = request.getRequestDispatcher("./views/searchepl.jsp");
        rd.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String txtSearch = request.getParameter("txtSearch");


        int res = employeeService.searchEmployee(txtSearch);
        System.out.println("-----------------------res"+res);
        int pageSize = 2;
        int endPage = 0;
        endPage = res/pageSize;
        if (res % pageSize != 0){
            endPage++;
        }

        String page = request.getParameter("page");
        System.out.println("page" +page);

        int currentPage = 1;
        if (page != null) {
            currentPage = Integer.parseInt(page);
            request.setAttribute("epl", employeeService.search(txtSearch,currentPage, 2));
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("txtSearch", txtSearch);

        } else {
            request.setAttribute("epl", employeeService.search(txtSearch,1, 2));
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("txtSearch", txtSearch);
        }


        request.setAttribute("endPage", endPage);
        RequestDispatcher rd = request.getRequestDispatcher("./views/searchepl.jsp");
        rd.forward(request,response);
    }
}
