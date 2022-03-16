package com.example.controller;

import com.example.models.Departments;
import com.example.models.Employee;
import com.example.service.DepartmentsService;
import com.example.service.EmployeeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/list-employee")
public class EmployeeController extends HttpServlet {

    private EmployeeService employeeService;
    private DepartmentsService departmentsService;

    public EmployeeController(){
        employeeService = new EmployeeService();
        departmentsService = new DepartmentsService();
    }




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String id = request.getParameter("id");
        System.out.println("id:" + id);
        String page = request.getParameter("page");
        System.out.println("page" +page);

        if (id != null) {
            request.setAttribute("detailEmployee", employeeService.findById(Integer.parseInt(id)));
            System.out.println(employeeService.findById(Integer.parseInt(id)));
        }

        int currentPage = 1;
        if (page != null) {
            currentPage = Integer.parseInt(page);
            request.setAttribute("epl", employeeService.findAll(currentPage, 6));
            request.setAttribute("currentPage", currentPage);

        } else {
            request.setAttribute("epl", employeeService.findAll(1, 6));
            request.setAttribute("currentPage", currentPage);
        }
        request.setAttribute("dept", departmentsService.findAll());

        System.out.println(employeeService.findAll());
        RequestDispatcher rd = request.getRequestDispatcher("./views/employee.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        if (action != null) {
            switch (action) {
                case "create":
                    String fullName = request.getParameter("fullname");
                    String address = request.getParameter("address");
                    String phoneNumber = request.getParameter("phoneNumber");
                    String email = request.getParameter("email");
                    String departmentId = request.getParameter("dept");
                    Employee epl = new Employee(fullName, address, phoneNumber, email, Integer.parseInt(departmentId));
                    boolean result = employeeService.create(epl);
                    response.sendRedirect(request.getContextPath() + "/list-employee");
                    break;
                case "update":
                    String employeeId = request.getParameter("id");
                    if (employeeId != null) {
                        String fname = request.getParameter("fullname");
                        String adr = request.getParameter("address");
                        String phN = request.getParameter("phoneNumber");
                        String mail = request.getParameter("email");
                        String deptId = request.getParameter("dept");
                        Employee employee = new Employee(Integer.parseInt(employeeId), fname, adr, phN, mail, Integer.parseInt(deptId));
                        boolean res = employeeService.update(employee);
                        System.out.println("update");
                        response.sendRedirect(request.getContextPath() + "/list-employee");
                    }
                    break;
                case "delete":
                    String id = request.getParameter("id");
                    System.out.println("id:" + id);

                    if (id != null) {
                        request.setAttribute("detailDept", employeeService.delete(Integer.parseInt(id)));
                    }
                    response.sendRedirect(request.getContextPath() + "/list-employee");
                    break;
            }
        }
    }
}
