package ua.kharkov.repairagency.servlet;


import ua.kharkov.repairagency.db.DAO;
import ua.kharkov.repairagency.db.entity.Feedback;
import ua.kharkov.repairagency.db.entity.RequestMaster;
import ua.kharkov.repairagency.db.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/userArchive")
public class UserArchiveServlet extends HttpServlet {
    private final static String index = "/user_archive.jsp";
    Map<RequestMaster, Feedback> archive = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("current_user");
        archive = DAO.getInstance().getUserRequestsArchiveFeedback(user.getId());
        req.setAttribute("archiveList", archive);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(index);
        requestDispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        User user = (User)session.getAttribute("current_user");
        if(req.getParameter("ident")!=null && req.getParameter("textb")!=null && req.getParameter("answer")!=null ){
            try{
                Feedback feedback = new Feedback();
                feedback.setText(req.getParameter("textb"));
                feedback.setStars(Integer.parseInt(req.getParameter("answer")));
                DAO.getInstance().userArchiveFeedback(feedback, Integer.parseInt(req.getParameter("ident")));
            }catch (Exception ex){
                System.out.println("0000000000");
            }
        }

    }

    @Override
    public void init() throws ServletException {

    }
}
