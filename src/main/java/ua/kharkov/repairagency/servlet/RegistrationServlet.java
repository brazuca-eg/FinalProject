package ua.kharkov.repairagency.servlet;

import ua.kharkov.repairagency.db.DAO;
import ua.kharkov.repairagency.db.entity.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setAttribute("path" , req.getContextPath() );
        String loginField = req.getParameter("login");
        String nameField = req.getParameter("name");
        String emailField = req.getParameter("email");
        String passwordField = req.getParameter("password");
        String passwordField2  = req.getParameter("password2");
        String surnameField = req.getParameter("surname");
        int role_id = Integer.parseInt(req.getParameter("role_id"));
        if(loginField == "" || passwordField == "" || passwordField2  == "" || surnameField == "" || emailField==""  || role_id  < 0  || role_id  > 3 ){
            req.setAttribute("error", "Все поля должны быть заполнены");
            req.getRequestDispatcher("/error.jsp").forward(req, res);
        }else if(!passwordField.equals(passwordField2)){
            req.setAttribute("error", "Пароли должны быть одинаковыми");
            req.getRequestDispatcher("/error.jsp").forward(req, res);
        }
        else{
            int userId = DAO.getInstance().checkExstingUser(loginField, emailField);
            if(userId == 0){
                int counter = 0;
                Matcher matcher = pattern.matcher(emailField);
                while (matcher.find()){
                    counter++;
                }
                if(counter==0){
                    req.setAttribute("error", "Неправильный формат email");
                    req.getRequestDispatcher("/error.jsp").forward(req, res);
                }else{
                    DAO.getInstance().register(emailField, loginField, passwordField , nameField, surnameField, role_id );
                    User user = DAO.getInstance().login(loginField, passwordField);
                    if(role_id == 3){
                        DAO.getInstance().userDefaultDetails(user.getId());
                    }
                    res.sendRedirect( req.getContextPath() +"/login");
                }

            }else{
                req.setAttribute("error", "Такой пользователь уже зарегестрирован");
                req.getRequestDispatcher("/error.jsp").forward(req, res);
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/registration.jsp");
        requestDispatcher.forward(req, res);
    }
}