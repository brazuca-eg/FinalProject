package ua.kharkov.repairagency.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebFilter(filterName = "localization")

public class LocaleFilter implements Filter {
    ResourceBundle resourceBundleRu;
    ResourceBundle resourceBundleEn;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//        HttpSession session = req.getSession(false);
//        Locale localeEn = Locale.ENGLISH;
//        Locale localeRu = new Locale("ru", "RU");
//        resourceBundleEn = ResourceBundle.getBundle("page_content", localeEn);
//        resourceBundleRu = ResourceBundle.getBundle("page_content", localeRu);
//        request.setAttribute("bundle_en", resourceBundleEn);
//        request.setAttribute("bundle_ru", resourceBundleRu);
        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
