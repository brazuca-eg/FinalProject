package ua.kharkov.repairagency.servlet;
import ua.kharkov.repairagency.db.DAO;
import ua.kharkov.repairagency.db.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private User user = null;
	Map<String, String> errors = new HashMap<String, String>();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String loginField = req.getParameter("login");
		String passwordField = req.getParameter("password");
		if (loginField != "" && passwordField != "") {
			user = DAO.getInstance().login(loginField, passwordField);
			if (user != null) {
				HttpSession session = req.getSession();
				session.setAttribute("role", user.getRole_id());
				session.setAttribute("current_user", user);
				if(user.getRole_id() == 1){
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcomeManager.jsp");
					dispatcher.forward(req, res);
				}else if(user.getRole_id() == 3){
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/clientWelcome");
					dispatcher.forward(req, res);
				}

				//res.sendRedirect("/clientWelcome");
			} else if(user==null){
				errors.put("cant_find", "Нету такого пользователя");
				req.setAttribute("errors", errors);
				req.getRequestDispatcher("/login.jsp").forward(req, res);
			}
		} else {
			errors.put("validate", "Заполните все поля");
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("/login.jsp").forward(req, res);
		}
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.jsp");
		requestDispatcher.forward(req, res);


//			if(user.getRole_id() == 1){
//				res.sendRedirect("welcomeManager.jsp");
//			}
//			else if(user.getRole_id() == 2){
//				res.sendRedirect("welcomeMaster.jsp");
//			}
//			else if(user.getRole_id() == 3){
//				res.sendRedirect("welcomeUser.jsp");
//			}

	//}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("registration.jsp");
	}
}


	

	
	
