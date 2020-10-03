package ua.kharkov.repairagency.servlet;

import ua.kharkov.repairagency.db.DAO;
import ua.kharkov.repairagency.db.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    Map<String, String> errors = new HashMap<String, String>();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String loginField = req.getParameter("login");
        String nameField = req.getParameter("name");
        String emailField = req.getParameter("email");
        String passwordField = req.getParameter("password");
        String passwordField2  = req.getParameter("password2");
        String surnameField = req.getParameter("surname");
        int role_id = Integer.parseInt(req.getParameter("role_id"));
        if(loginField == "" || passwordField == "" || passwordField2  == "" || surnameField == "" || role_id  < 0  || role_id  > 3 || !passwordField.equals(passwordField2)){
            errors.put("validate", "Все поля должны быть заполнены");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/registration.jsp").forward(req, res);
            doGet(req, res);
        }else{
            DAO.getInstance().register(emailField, loginField, passwordField , nameField, surnameField, role_id );
            res.sendRedirect("/login");
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/registration.jsp");
        requestDispatcher.forward(req, res);
    }
}