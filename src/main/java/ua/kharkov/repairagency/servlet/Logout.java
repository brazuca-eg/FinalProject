package ua.kharkov.repairagency.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//        HttpSession session = req.getSession();
//        session.removeAttribute("current_user");
//        session.removeAttribute("role");
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("JSESSIONID")){
                    System.out.println("JSESSIONID="+cookie.getValue());
                    break;
                }
            }
        }
        //invalidate the session if exists
        HttpSession session = req.getSession(false);
        if(session != null){
            session.invalidate();
        }
        res.sendRedirect("login.jsp");
    }

}