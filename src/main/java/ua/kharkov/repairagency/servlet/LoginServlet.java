package ua.kharkov.repairagency.servlet;
import ua.kharkov.repairagency.db.DAO;
import ua.kharkov.repairagency.db.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String loginField = req.getParameter("login");
		String passwordField = req.getParameter("password");

		User user = DAO.getInstance().login(loginField, passwordField);
		PrintWriter out = res.getWriter();
		if(user == null){
			res.sendRedirect("index.jsp");
			//out.println("No users with these data. Try again");
		}else{
			HttpSession session = req.getSession();
			session.setAttribute("userLogin", loginField);
			session.setAttribute("role", user.getRole_id());
			session.setAttribute("current_user", user);
			//out.print("The role is : " + user.getRole_id() + "\n") ;
			//out.print(user.toString());
			if(user.getRole_id() == 1){
				res.sendRedirect("welcomeManager.jsp");
			}
			else if(user.getRole_id() == 2){
				res.sendRedirect("welcomeMaster.jsp");
			}
			else if(user.getRole_id() == 3){
				res.sendRedirect("welcomeUser.jsp");
			}

		}

	} 
}


	

	
	
