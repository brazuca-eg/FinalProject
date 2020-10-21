package ua.kharkov.repairagency.servlet;

import ua.kharkov.repairagency.db.DAO;
import ua.kharkov.repairagency.db.entity.Balance;
import ua.kharkov.repairagency.db.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet("/clientWelcome")
public class WelcomeUserServlet extends HttpServlet {
    ResourceBundle currentBundle;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("current_user");
        req.setAttribute("found", user);
        Balance balance = DAO.getInstance().checkCustomerBalance(user);
        req.setAttribute("balance",balance);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("welcomeUser.jsp");
        requestDispatcher.forward(req, res);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("role");
        session.removeAttribute("current_user");
        session.invalidate();
        try {
            res.sendRedirect("/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
