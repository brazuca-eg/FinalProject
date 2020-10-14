package ua.kharkov.repairagency.servlet;


import ua.kharkov.repairagency.db.DAO;
import ua.kharkov.repairagency.db.entity.Feedback;
import ua.kharkov.repairagency.db.entity.RequestMaster;
import ua.kharkov.repairagency.db.entity.User;
import ua.kharkov.repairagency.exception.DataBaseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        User user = (User)session.getAttribute("current_user");
        if(Integer.parseInt(req.getParameter("ident"))>0 && req.getParameter("textb")!="" && Integer.parseInt(req.getParameter("answer"))>0 ){
            for (Map.Entry<RequestMaster, Feedback> entry : archive.entrySet()) {
                int reqId = entry.getKey().getId();
                if(reqId == Integer.parseInt(req.getParameter("ident"))){
                    try{
                        Feedback feedback = new Feedback();
                        feedback.setText(req.getParameter("textb"));
                        feedback.setStars(Integer.parseInt(req.getParameter("answer")));
                        DAO.getInstance().userArchiveFeedback(feedback, Integer.parseInt(req.getParameter("ident")));
                        doGet(req,res);
                    }catch (DataBaseException ex){
                        ex.setMessage("Проблемы с БД");
                        req.setAttribute("error", ex);
                        req.getRequestDispatcher("/error.jsp").forward(req, res);
                    }
                }else{
                    DataBaseException ex = new DataBaseException("Такого идентификатора нету в вашем аррхиве ");
                    req.setAttribute("error", ex);
                    req.getRequestDispatcher("/error.jsp").forward(req, res);
                }
            }

        }else{
            DataBaseException ex = new DataBaseException("Заполните все поля ");
            req.setAttribute("error", ex);
            req.getRequestDispatcher("/error.jsp").forward(req, res);
        }

    }

    @Override
    public void init() throws ServletException {

    }
}
