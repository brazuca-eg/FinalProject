package ua.kharkov.repairagency.servlet;

import ua.kharkov.repairagency.db.DAO;
import ua.kharkov.repairagency.db.entity.RequestSQL;
import ua.kharkov.repairagency.db.entity.Status;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/confirm_manager")
public class ManagerConfirmServlet extends HttpServlet {
    List<RequestSQL> requests = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("path" , req.getContextPath());
        requests = DAO.getInstance().getManagerRequestsFilterStatus(Status.PAID_USER.getId());
        req.setAttribute("requests", requests);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/manager_confirm.jsp");
        requestDispatcher.forward(req, res);
        req.setAttribute("status_paid", Status.PAID);
        req.setAttribute("status_cancel", Status.CANCELLED);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if(req.getParameter("paid")!=null){
            int req_id = Integer.parseInt(req.getParameter("paid"));
            DAO.getInstance().updateStatusByMaster(Status.PAID.getId(), req_id);

        }else{
            if(req.getParameter("cancel")!=null){
                int req_id = Integer.parseInt(req.getParameter("cancel"));
                DAO.getInstance().updateStatusByMaster(Status.CANCELLED.getId(), req_id);
                DAO.getInstance().cancelRequest(req_id);
            }
        }
        doGet(req, res);
    }


}
