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
import java.util.ArrayList;
import java.util.List;


@WebServlet("/master_archive")
public class MasterArchiveServlet extends HttpServlet {
    List<RequestMaster> archive = null;
    List<RequestMaster> currentPage = new ArrayList<>();
    private final static String index = "/master_archive.jsp";
    int divide = 2;
    List<Feedback> currentFeedback = new ArrayList<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        currentPage =  new ArrayList<>();
        currentFeedback = new ArrayList<>();
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("current_user");
        archive = DAO.getInstance().getMasterRequestsArchive(user.getId());
        int amount = archive.size();
        int pages = 1;
        if(amount%divide==0){
            pages = amount/divide;
        }else {
            pages = (amount/divide) + 1;
        }
        req.setAttribute("pages", pages);
        req.setAttribute("amount", amount);
        if(req.getParameter("numPage")==null){
            for (int i = 0; i < divide; i++) {
                if(i<archive.size()){
                    currentPage.add(archive.get(i));
                    int reqId = archive.get(i).getId();
                    Feedback feedback = DAO.getInstance().getMasterRequestFeedback(user.getId(), reqId);
                    currentFeedback.add(feedback);
                }
            }
        }else{
            int n = Integer.parseInt(req.getParameter("numPage"));
            n = n-1;
            for (int i = n*divide; i < n*divide+2; i++) {
                if(i<archive.size()){
                    currentPage.add(archive.get(i));
                    int reqId = archive.get(i).getId();
                    Feedback feedback = DAO.getInstance().getMasterRequestFeedback(user.getId(), reqId);
                    currentFeedback.add(feedback);
                }

            }
        }
        req.setAttribute("list", currentPage);
        req.setAttribute("feedbacks", currentFeedback);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(index);
        requestDispatcher.forward(req, res);
    }

}
