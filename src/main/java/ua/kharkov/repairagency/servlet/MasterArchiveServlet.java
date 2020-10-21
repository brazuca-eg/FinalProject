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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/master_archive")
public class MasterArchiveServlet extends HttpServlet {
    List<RequestMaster> archive = null;
    List<RequestMaster> currentPage = new ArrayList<>();
    private final static String index = "/master_archive.jsp";
    int divide = 2;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        currentPage =  new ArrayList<>();
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("current_user");
        archive = DAO.getInstance().getMasterRequestsArchive(user.getId());
        int amount = archive.size();
        int pages = 1;
        if(amount%divide==0){
            pages = amount/divide;
        }else {
            pages = amount/divide + 1;
        }
        req.setAttribute("pages", pages);
        if(req.getParameter("numPage")==null){
            for (int i = 0; i < divide; i++) {
                currentPage.add(archive.get(i));
            }
        }else{
            int n = Integer.parseInt(req.getParameter("numPage"));
            System.out.println("the n is " + n);
            for (int i = n*divide-divide; i < n*divide; i++) {
                currentPage.add(archive.get(i));
            }
        }
        req.setAttribute("list", currentPage);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher(index);
        requestDispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {

    }
}
