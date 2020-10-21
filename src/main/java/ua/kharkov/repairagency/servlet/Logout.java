package ua.kharkov.repairagency.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("logout.jsp");
        requestDispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if(req.getParameter("yes").equals("Yes")) {
            HttpSession session = req.getSession(false);
            if(session != null){
                session.removeAttribute("current_user");
                session.removeAttribute("role");
                session.invalidate();
            }
            String path = req.getContextPath() + "/login";
            res.sendRedirect(path);
        }
    }
}