package ua.kharkov.repairagency.servlet;

import ua.kharkov.repairagency.db.DAO;
import ua.kharkov.repairagency.db.entity.Balance;
import ua.kharkov.repairagency.db.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@WebServlet("/find")
public class PaymentManagerServlet extends HttpServlet {
    private User user = null;
    Balance balance = null;
    private final static String index = "/managerPay.jsp";
    //private List<User> users = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setAttribute("found", user);
        req.setAttribute("balance", balance);
       // this.users = DAO.getInstance().findClients(3);
        //req.setAttribute("customers", users);
        req.getRequestDispatcher(index).forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
       this.user = DAO.getInstance().findUserId(Integer.parseInt(req.getParameter("ident")));
       if(DAO.getInstance().checkCustomerBalance(this.user)!=null){
           this.balance = DAO.getInstance().checkCustomerBalance(this.user);
       }
       //this.users = DAO.getInstance().findClients(3);
       doGet(req,res);
    }
}
