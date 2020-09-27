package ua.kharkov.repairagency;
import ua.kharkov.repairagency.db.DAO;
import ua.kharkov.repairagency.db.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/login")
public class Servlet1  extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String loginField = req.getParameter("login");
		String passwordField = req.getParameter("passwordField");
		User user = DAO.getInstance().findUserByLogin(loginField);
		PrintWriter out = res.getWriter();
		out.print("The role is : " + user.getRole_id() + "\n") ;
		out.print(user.toString());
	} 
}


	

	
	
