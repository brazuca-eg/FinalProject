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
	private User user = null;
	Map<String, String> errors = new HashMap<String, String>();

	@Override
	public void init() throws ServletException {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String path = req.getContextPath();
		HttpSession session = req.getSession();
		session.setAttribute("path", path);


		String loginField = req.getParameter("login");
		String passwordField = req.getParameter("password");
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
				errors.put("cant_find", "Нету такого пользователя");
				req.setAttribute("errors", errors);
				req.getRequestDispatcher("/login.jsp").forward(req, res);
			}
		} else {
			errors.put("validate", "Заполните все поля");
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("/login.jsp").forward(req, res);
		}
	}




	@Override
	protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}


}


	
	
