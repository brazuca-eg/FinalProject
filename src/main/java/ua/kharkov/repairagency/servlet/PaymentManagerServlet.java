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
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/find")
public class PaymentManagerServlet extends HttpServlet {
    private User user = null;
    private Balance balance = null;
    private final static String index = "/managerPay.jsp";
    private Map<Balance,User> list = new HashMap<>();
    private List<String> statuses;
    private List<User> clients;

    @Override
    public void init() throws ServletException {
        this.list = DAO.getInstance().findClientsAndBalance(3);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        statuses = new ArrayList<String>();
        list = DAO.getInstance().findClientsAndBalance(3);
        req.setAttribute("path" , req.getContextPath());
        list = DAO.getInstance(). findClientsAndBalance(3);


        clients = DAO.getInstance().findClients(3);
        for (int i = 0; i <  clients.size(); i++) {
            String usStatus = DAO.getInstance().getUserStatus(clients.get(i).getId());
            statuses.add(usStatus);
        }



        req.setAttribute("usSt", statuses);

        req.setAttribute("clients", list);
        if(req.getParameter("ident")!=null){
            User user = DAO.getInstance().findUserId(Integer.parseInt(req.getParameter("ident")));
            if(user == null){
                req.setAttribute("error", "Такой идентификатора пользователя не найдено");
                req.getRequestDispatcher("/error.jsp").forward(req, res);
            }else{
                if(user.getRole_id()==3){
                    this.user = DAO.getInstance().findUserId(Integer.parseInt(req.getParameter("ident")));
                    req.setAttribute("found", user);
                    if(DAO.getInstance().checkCustomerBalance(this.user)!=null){
                        this.balance = DAO.getInstance().checkCustomerBalance(this.user);
                        req.setAttribute("balance", balance);
                    }
                }
            }

        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(index);
        requestDispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        if(Integer.parseInt(req.getParameter("ban")) > 0) {
            DAO.getInstance().changeUserStatus(Integer.parseInt(req.getParameter("ban")), "banned");
            req.setAttribute("bannedS", "the user with id " + Integer.parseInt(req.getParameter("ban")) + "banned");
//        }else if(Integer.parseInt(req.getParameter("unban")) > 0){
//            DAO.getInstance().changeUserStatus(Integer.parseInt(req.getParameter("unban")), "unbunned");
//            req.setAttribute("bannedS", "the user with id " + Integer.parseInt(req.getParameter("unban")) + "unbanned");
//        }
        }else if(user!=null && Integer.parseInt(req.getParameter("summa")) > 0){
            DAO.getInstance().updateBalance(user, Integer.parseInt(req.getParameter("summa")));
            req.setAttribute("paidSucc", "Балнас пользователя с id: " + user.getId() + " успешно пополнен на " + Integer.parseInt(req.getParameter("summa")));
        }else if(user==null || Integer.parseInt(req.getParameter("summa")) <= 0){
            req.setAttribute("error", "Сумма должна быть больше 0");
            req.getRequestDispatcher("/error.jsp").forward(req, res);
        }




       doGet(req,res);
    }
}
