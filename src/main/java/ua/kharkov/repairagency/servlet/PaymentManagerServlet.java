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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/find")
public class PaymentManagerServlet extends HttpServlet {
    private User user = null;
    Balance balance = null;
    private final static String index = "/managerPay.jsp";
    private List<User> users = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        this.users = DAO.getInstance().findClients(3);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setAttribute("customers", users);
        if(req.getParameter("ident")!=null){
            User user = DAO.getInstance().findUserId(Integer.parseInt(req.getParameter("ident")));
            if(user.getRole_id()==3){
                this.user = DAO.getInstance().findUserId(Integer.parseInt(req.getParameter("ident")));
                req.setAttribute("found", user);
                if(DAO.getInstance().checkCustomerBalance(this.user)!=null){
                    this.balance = DAO.getInstance().checkCustomerBalance(this.user);
                    req.setAttribute("balance", balance);
                }
            }
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(index);
        requestDispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        if(user!=null &&  Integer.parseInt(req.getParameter("summa")) > 0){
            DAO.getInstance().updateBalance(user, Integer.parseInt(req.getParameter("summa")));
        }


       doGet(req,res);
    }
}
