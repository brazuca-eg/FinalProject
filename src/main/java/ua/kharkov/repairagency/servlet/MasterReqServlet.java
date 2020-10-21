package ua.kharkov.repairagency.servlet;

import ua.kharkov.repairagency.db.DAO;
import ua.kharkov.repairagency.db.entity.RequestMaster;
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

import java.util.List;

@WebServlet("/masterRequests")
public class MasterReqServlet extends HttpServlet {
    List<RequestMaster> requestMasters = null;
    private final static String index = "/master_requests.jsp";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("status_paid", Status.PAID.getName());
        req.setAttribute("status_work", Status.WORK.getName());
        req.setAttribute("status_finish", Status.END.getName());

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        if(user!=null){
            requestMasters = DAO.getInstance().getMasterRequests(user.getId());
        }
        req.setAttribute("masterList", requestMasters);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(index);
        requestDispatcher.forward(req, res);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if(req.getParameter("start")!=null){
            int req_id = Integer.parseInt(req.getParameter("start"));
            DAO.getInstance().updateStatusByMaster(Status.WORK.getId(), req_id);
        }else{
            if(req.getParameter("finish")!=null){
                int req_id = Integer.parseInt(req.getParameter("finish"));
                DAO.getInstance().updateStatusByMaster(Status.END.getId(), req_id);
            }
        }
        doGet(req, res);

    }

}
