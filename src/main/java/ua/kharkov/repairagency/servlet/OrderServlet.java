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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@WebServlet("/request")
public class OrderServlet extends HttpServlet {
    private final static String index = "/order.jsp";
    Map<String, String> errors = new HashMap<String, String>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(index);
        requestDispatcher.forward(req, res);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        Date now = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String mysqlDateString = formatter.format(now);
        DAO.getInstance().makeRequest(user.getId(), name, desc , mysqlDateString);
        doGet(req, res);
    }

}