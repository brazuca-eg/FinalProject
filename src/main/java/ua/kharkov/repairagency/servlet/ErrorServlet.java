package ua.kharkov.repairagency.servlet;

import ua.kharkov.repairagency.exception.DataBaseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/error")
public class ErrorServlet extends HttpServlet {
//    List<String> errors;
    DataBaseException ex = new DataBaseException();
    @Override
    public void init() throws ServletException {
//        errors = new ArrayList<>();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        errors = (List<String>) req.getAttribute("errors");
//        String path = (String) req.getAttribute("path");
//        req.setAttribute("errors", errors);
//        req.setAttribute("path", path);

        ex = (DataBaseException) req.getAttribute("error");
        req.setAttribute("error", ex);

        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/error.jsp");
        requestDispatcher.forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
