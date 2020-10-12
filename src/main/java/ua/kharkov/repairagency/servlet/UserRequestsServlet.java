package ua.kharkov.repairagency.servlet;

import ua.kharkov.repairagency.db.DAO;
import ua.kharkov.repairagency.db.entity.*;

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

    List<RequestUser> requests = new ArrayList<>();
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        requests = DAO.getInstance().getUserRequests(user.getId(), Status.WAIT.getId());
        req.setAttribute("waitingPaymentList", requests);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/user_pay.jsp");
        requestDispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        if(req.getParameter("pay")!=null){
            int reqId = Integer.parseInt(req.getParameter("pay"));
            Balance balance = DAO.getInstance().checkCustomerBalance(user);
            double bal = balance.getBalance();
            double pay = DAO.getInstance().getPriceOfRequest(reqId);
            if(bal>pay){
                DAO.getInstance().userPayRequest1(reqId, pay);
                DAO.getInstance().userPayRequest2(reqId, Status.PAID_USER.getId());
                DAO.getInstance().setSqlUserLowerBalance(user.getId(), pay);
            }else{
                return;
            }
        }
        doGet(req, res);

    }


}
