package ua.kharkov.repairagency.servlet;

import ua.kharkov.repairagency.db.DAO;
import ua.kharkov.repairagency.db.entity.RequestUser;
import ua.kharkov.repairagency.db.entity.Status;
import ua.kharkov.repairagency.db.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/allUserRequests")
public class UserAllRequestsServlet extends HttpServlet {
    List<RequestUser> requests = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        requests = DAO.getInstance().getUserAllRequests(user);
        req.setAttribute("requestList", requests);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/user_all_request.jsp");
        requestDispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void init() throws ServletException {

    }
}
