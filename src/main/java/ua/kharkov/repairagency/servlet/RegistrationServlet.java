package ua.kharkov.repairagency.servlet;

import ua.kharkov.repairagency.db.DAO;
import ua.kharkov.repairagency.db.entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String loginField = req.getParameter("login");
        String passwordField = req.getParameter("password");
        String passwordField2  = req.getParameter("password2");
        String surnameField = req.getParameter("surname");
        int role_id = Integer.parseInt(req.getParameter("role_id"));
        if(loginField == "" || passwordField == "" || passwordField2  == "" || surnameField == "" || role_id  < 0  || role_id  > 3 || !passwordField.equals(passwordField2)){
            PrintWriter out = res.getWriter();
            out.println("err");
            //res.sendRedirect("registration.jsp");
        }else{
            DAO.getInstance().register(loginField, passwordField , surnameField, role_id );
            PrintWriter out = res.getWriter();
            out.println("succ");
            //res.sendRedirect("login.jsp");
        }

    }
}