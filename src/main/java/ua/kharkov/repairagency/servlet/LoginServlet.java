package ua.kharkov.repairagency.servlet;
import ua.kharkov.repairagency.LocaleManager;
import ua.kharkov.repairagency.db.DAO;
import ua.kharkov.repairagency.db.entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		User user = null;
		String path = req.getContextPath();
		HttpSession session = req.getSession();
		session.setAttribute("path", path);
		String loginField = req.getParameter("login");
		String passwordField = req.getParameter("password");
		if (loginField != null && passwordField != null){
			if (loginField != "" && passwordField != "") {
				user = DAO.getInstance().login(loginField, passwordField);
				if (user != null) {
					session.setAttribute("role", user.getRole_id());
					session.setAttribute("current_user", user);
					if (user.getRole_id() == 1) {
						String path1 = req.getContextPath() + "/manager_profile";
						res.sendRedirect(path1);
					} else if (user.getRole_id() == 3) {
						String path2 = req.getContextPath() + "/clientWelcome";
						res.sendRedirect(path2);
					} else if (user.getRole_id() == 2) {
						String path3 = req.getContextPath() + "/masterRequests";
						res.sendRedirect(path3);
					}
				} else if (user == null) {
					req.setAttribute("error", "Такого пользователя нету в базе");
					req.getRequestDispatcher("/error.jsp").forward(req, res);
				}
			}
			else {
				req.setAttribute("error", "Заполните все поля");
				req.getRequestDispatcher("/error.jsp").forward(req, res);
			}
		}else {
			req.getRequestDispatcher("/login.jsp").forward(req, res);
		}




	}


	@Override
	protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(req.getParameter("localeForm").equals("en")){
			session.removeAttribute("locale");
			session.setAttribute("locale", "en");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}else if(req.getParameter("localeForm").equals("ru")){
			session.removeAttribute("locale");
			session.setAttribute("locale", "ru");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}


}


	
	
