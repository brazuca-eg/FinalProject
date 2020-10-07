package ua.kharkov.repairagency.servlet;

import ua.kharkov.repairagency.db.DAO;
import ua.kharkov.repairagency.db.entity.*;

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
    private  List<StatusEntity> statuses;
    @Override
    public void init() throws ServletException {
        requests = DAO.getInstance().getManagerRequests();
        masters = DAO.getInstance().findClients(2);
        statuses = DAO.getInstance().getStatuses();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("masters", masters);
        req.setAttribute("statuses", statuses);



        String value_of_sort = req.getParameter("select_sort");
        String filter1 = req.getParameter("filter_status1");
        String filter2 = req.getParameter("filter_status2");
        if(value_of_sort != null){
            requests = DAO.getInstance().getManagerRequests(value_of_sort);
            req.setAttribute("list", requests);
        }else if(filter1!=null){
            int filter_status = Integer.parseInt(req.getParameter("filter_status1"));
            requests = DAO.getInstance().getManagerRequestsFilterStatus(filter_status);
            req.setAttribute("list", requests);
        }else if(filter2!=null){
            int filter_status = Integer.parseInt(req.getParameter("filter_status2"));
            requests = DAO.getInstance().getManagerRequestsFilterMaster(filter_status);
            req.setAttribute("list", requests);
        }
        else{
            requests = DAO.getInstance().getManagerRequests();
            req.setAttribute("list", requests);
        }
//        if(filter1!=null){
//            int filter_status = Integer.parseInt(req.getParameter("filter_status1"));
//            requests = DAO.getInstance().getManagerRequestsFilterStatus(filter_status);
//            req.setAttribute("list", requests);
//        }
//        if(filter2!=null){
//            int filter_status = Integer.parseInt(req.getParameter("filter_status2"));
//            requests = DAO.getInstance().getManagerRequestsFilterMaster(filter_status);
//            req.setAttribute("list", requests);
//        }



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
