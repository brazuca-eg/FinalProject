package ua.kharkov.repairagency.filter;

import ua.kharkov.repairagency.db.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(filterName = "security")
public class SecurityFilter implements Filter {
    public static final String ERROR_403 = "error_403.html";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        User user = (User) req.getSession().getAttribute("current_user");
        String currentUrl = req.getRequestURL().toString();
        if(user==null){
            if(!currentUrl.contains("login") && !currentUrl.contains("main") && !currentUrl.contains("register")){
               servletRequest.getRequestDispatcher(ERROR_403).forward(servletRequest, servletResponse);
               return;
            }
        }else {
            if(user.getRole_id()==1){
                if(!currentUrl.contains("main")  && !currentUrl.contains("manager_profile") && !currentUrl.contains("find")
                        && !currentUrl.contains("seeRequests") && !currentUrl.contains("confirm_manager") && !currentUrl.contains("confirm_manager")){
                    servletRequest.getRequestDispatcher(ERROR_403).forward(servletRequest, servletResponse);
                    return;
                }
            }else  if(user.getRole_id()==2){
                if(!currentUrl.contains("main")  && !currentUrl.contains("masterRequests") && !currentUrl.contains("master_archive")){
                    servletRequest.getRequestDispatcher(ERROR_403).forward(servletRequest, servletResponse);
                    return;
                }
            }else  if(user.getRole_id()==3){
                if(!currentUrl.contains("main")  && !currentUrl.contains("masterRequests") && !currentUrl.contains("clientWelcome")
                        && !currentUrl.contains("request") && !currentUrl.contains("myRequests") && !currentUrl.contains("allUserRequests") && !currentUrl.contains("userArchive")){
                    servletRequest.getRequestDispatcher(ERROR_403).forward(servletRequest, servletResponse);
                    return;
                }
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);

    }



    @Override
    public void destroy() {

    }
}
