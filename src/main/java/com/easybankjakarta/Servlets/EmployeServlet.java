package com.easybankjakarta.Servlets;

import com.easybankjakarta.Dao.EmployeDao;
import com.easybankjakarta.Dao.impl.EmployeDaoImpl;
import com.easybankjakarta.Models.Employe;
import com.easybankjakarta.Services.EmployeService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "EmployeServlet", value = "/employe")
public class EmployeServlet extends HttpServlet {
    EmployeDao employeDao = new EmployeDaoImpl();
    EmployeService employeService = new EmployeService(employeDao);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "edit":
                showUpdateForm(request, response);
                break;
            case "get":
                getEmploye(request, response);
                break;
            case "insert":
                insertEmploye(request, response);
                break;
            case "delete":
                deleteEmploye(request, response);
                break;
            case "update":
                updateEmploye(request, response);
                break;
            default:
                getEmployes(request, response);
                break;
        }
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricule = request.getParameter("matricule");
        Employe existingEmploye;
        try {
            Optional<Employe> employe = employeService.getEmploye(matricule);
            if (employe.isPresent()) {
                existingEmploye = employe.get();
                RequestDispatcher dispatcher = request.getRequestDispatcher("Views/updateEmploye.jsp");
                request.setAttribute("employe", existingEmploye);
                dispatcher.forward(request, response);
            } else {
                String errorMessage = "There is no employe with this matricule.";
                request.setAttribute("errorMessage", errorMessage);
                RequestDispatcher dispatcher = request.getRequestDispatcher("Views/updateEmploye.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertEmploye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricule = request.getParameter("matricule");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
        String mobile = request.getParameter("mobile");
        LocalDate recruitmentDate = LocalDate.parse(request.getParameter("recruitmentDate"));
        String email = request.getParameter("email");
        Employe employe = new Employe(firstName, lastName, birthDate, mobile, matricule, recruitmentDate, email);
        Optional<Employe> optionalEmploye = employeService.addEmploye(employe);
        String message = null;
        if (optionalEmploye.isPresent()) {
            message = "Employe Inserted Successfuly";
        } else {
            message = "Employe Not Inserted";
        }
        request.setAttribute("message", message);
        getEmployes(request, response);
    }

    private void getEmployes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Employe> employes = employeService.getEmployees();
            request.setAttribute("employes", employes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Views/employe.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getEmploye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricule = request.getParameter("matricule");
        Optional<Employe> optionalEmploye = employeService.getEmploye(matricule);
        if (optionalEmploye.isPresent()) {
            Employe employe = optionalEmploye.get();
            request.setAttribute("employe", employe);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Views/employe.jsp");
            dispatcher.forward(request, response);
        } else {
            String message = "There is no Employe with this matricule";
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Views/employe.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void deleteEmploye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricule = request.getParameter("matricule");
        Integer res = employeService.deleteEmploye(matricule);
        String message = null;
        if (res != 0){
            message = "Employe Deleted Successfuly";
        }else {
            message = "Employe Not Deleted";
        }
        request.setAttribute("message", message);
        getEmployes(request, response);
    }

    private void updateEmploye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricule = request.getParameter("matricule");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
        String mobile = request.getParameter("mobile");
        LocalDate recruitmentDate = LocalDate.parse(request.getParameter("recruitmentDate"));
        String email = request.getParameter("email");
        Employe employe = new Employe(firstName, lastName, birthDate, mobile, matricule, recruitmentDate, email);
        Optional<Employe> optionalEmploye = employeService.updateEmploye(employe);
        String message = null;
        if (optionalEmploye.isPresent()) {
            message = "Employe Updated Successfuly";
        } else {
            message = "Employe Not Updated";
        }
        request.setAttribute("message", message);
        getEmployes(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}