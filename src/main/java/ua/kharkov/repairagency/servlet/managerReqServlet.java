package ua.kharkov.repairagency.servlet;

import ua.kharkov.repairagency.db.DAO;
import ua.kharkov.repairagency.db.entity.Request;
import ua.kharkov.repairagency.db.entity.RequestSQL;
import ua.kharkov.repairagency.db.entity.Status;
import ua.kharkov.repairagency.db.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.Predicate;

@WebServlet("/seeRequests")
public class managerReqServlet extends HttpServlet {
    private final static String index = "/managerRequests.jsp";
    private List<RequestSQL> requests;
    private  List<User> masters;

    @Override
    public void init() throws ServletException {
        requests = DAO.getInstance().getManagerRequests();
        masters = DAO.getInstance().findClients(2);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("masters", masters);

        String value_of_sort = req.getParameter("select_sort");
        if(value_of_sort != null){
            requests = DAO.getInstance().getManagerRequests(value_of_sort);
            req.setAttribute("list", requests);
        }else{
            requests = DAO.getInstance().getManagerRequests();
            req.setAttribute("list", requests);
        }


        RequestDispatcher requestDispatcher = req.getRequestDispatcher(index);
        requestDispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int request_id = (int) req.getAttribute("ident");
        double price = (double) req.getAttribute("price");
        int master_id = (int) req.getAttribute("select_master");
        int status_id = DAO.getInstance().statusOfRequest(request_id);
        if(status_id == Status.WAIT.getId()){
            DAO.getInstance().updateUnpaidRequest(request_id, price, master_id);
        }

    }

}
