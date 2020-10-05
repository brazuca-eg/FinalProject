package ua.kharkov.repairagency.servlet;

import ua.kharkov.repairagency.db.DAO;
import ua.kharkov.repairagency.db.entity.Request;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/myRequests")
public class UserRequestsServlet extends HttpServlet {
    List<Request> requests = new ArrayList<>();
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        requests = DAO.getInstance().getUserRequests(user);
        req.setAttribute("list", requests);



        //        Map <List<Request>, String> map = new HashMap<>();
//        map = DAO.getInstance().getSpecialisedUserRequests(user, Status.WAIT.getId());
//        req.setAttribute("wait", map);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/userRequests.jsp");
        requestDispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }


}
