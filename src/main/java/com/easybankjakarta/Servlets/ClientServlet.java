package com.easybankjakarta.Servlets;

import com.easybankjakarta.Dao.ClientDao;
import com.easybankjakarta.Dao.impl.ClientDaoImpl;
import com.easybankjakarta.Models.Client;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "ClientServlet", value = "/ClientServlet")
public class ClientServlet extends HttpServlet {
    ClientDao clientDao;

    public void init(ServletConfig servletconfig)
            throws ServletException {
        clientDao = new ClientDaoImpl();
    }

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
                getClient(request, response);
                break;
            case "insert":
                insertClient(request, response);
                break;
            case "delete":
                deleteClient(request, response);
                break;
            case "update":
                updateClient(request, response);
                break;
            default:
                getClients(request, response);
                break;
        }
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        Client existingClient;
        try {
            Optional<Client> client = clientDao.getClient(code);
            if (client.isPresent()) {
                existingClient = client.get();
                RequestDispatcher dispatcher = request.getRequestDispatcher("Views/updateClient.jsp");
                request.setAttribute("client", existingClient);
                dispatcher.forward(request, response);
            } else {
                String errorMessage = "There is no client with this code.";
                request.setAttribute("errorMessage", errorMessage);
                RequestDispatcher dispatcher = request.getRequestDispatcher("Views/updateClient.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
        String mobile = request.getParameter("mobile");
        String address = request.getParameter("address");
        Client client = new Client(firstName, lastName, birthDate, mobile, code, address);
        Optional<Client> optionalClient =  clientDao.addClient(client);
        String message = null;
        if (optionalClient.isPresent()){
            message = "Client Inserted Successfuly";
        }else {
            message = "Client Not Inserted";
        }
        request.setAttribute("message", message);
        getClients(request, response);
    }

    private void getClients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Client> clients = clientDao.getClients();
            request.setAttribute("clients", clients);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Views/client.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        Optional<Client> optionalClient = clientDao.getClient(code);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            request.setAttribute("client", client);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Views/client.jsp");
            dispatcher.forward(request, response);
        } else {
            String message = "There is no client with this code";
            request.setAttribute("message", message);
            getClients(request, response);
        }

    }

    private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        Integer res = clientDao.deleteClient(code);
        String message = null;
        if (res != 0){
            message = "Client Deleted Successfuly";
        }else {
            message = "Client Not Deleted";
        }
        request.setAttribute("message", message);
        getClients(request, response);
    }

    private void updateClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String firstName = request.getParameter("firstName");
        String lastname = request.getParameter("lastName");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
        String mobiel = request.getParameter("mobile");
        String adresse = request.getParameter("address");
        Client client = new Client(firstName, lastname, birthDate, mobiel, code, adresse);
        Optional<Client> optionalClient = clientDao.updateClient(client);
        String message = null;
        if (optionalClient.isPresent()){
            message = "Client Updated Successfuly";
        }else {
            message = "Client Not Updated ";
        }
        request.setAttribute("message", message);
        getClients(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}