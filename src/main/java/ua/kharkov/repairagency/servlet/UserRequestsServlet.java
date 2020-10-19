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
import java.util.List;

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
        Balance balance = DAO.getInstance().checkCustomerBalance(user);
        req.setAttribute("myBalance", balance.getBalance());
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
            int statusId = DAO.getInstance().getStatusByRequestId(reqId);
            if(statusId==Status.WAIT.getId()){
                Balance balance = DAO.getInstance().checkCustomerBalance(user);
                double bal = balance.getBalance();
                double pay = DAO.getInstance().getPriceOfRequest(reqId);
                if(bal>pay){
                    DAO.getInstance().userPayRequest(pay, Status.PAID_USER.getId(), reqId);
                    DAO.getInstance().setSqlUserLowerBalance(user.getId(), pay);
                    req.setAttribute("paid", "Заказа номер "+ reqId +" успешно оплачен");
                    doGet(req, res);
                }else{
                    req.setAttribute("error", "Недостаточно средств на балансе для оплаты");
                    req.getRequestDispatcher("/error.jsp").forward(req, res);
                }
            }
        }
    }

}
