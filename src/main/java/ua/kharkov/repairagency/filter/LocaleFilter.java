package ua.kharkov.repairagency.filter;

import ua.kharkov.repairagency.LocaleManager;

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
    ResourceBundle currentBundle;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Locale localeEn = Locale.ENGLISH;
        Locale localeRu = new Locale("ru", "RU");
        resourceBundleEn = ResourceBundle.getBundle("page_content", localeEn);
        resourceBundleRu = ResourceBundle.getBundle("page_content", localeRu);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if(req.getSession().getAttribute("locale") == null || req.getSession().getAttribute("locale").equals("en")){
            currentBundle = resourceBundleEn;
        }else if(req.getSession().getAttribute("locale").equals("ru")){
            currentBundle = resourceBundleRu;
        }

        HttpSession session = req.getSession(false);

        req.setAttribute("h1", currentBundle.getString(LocaleManager.HEADER_MAIN_PAGE));
        req.setAttribute("h2", currentBundle.getString(LocaleManager.HEADER_LOGIN_PAGE));
        req.setAttribute("h3", currentBundle.getString(LocaleManager.HEADER_REGISTER));

        req.setAttribute("one", currentBundle.getString(LocaleManager.LOGIN_PAGE_LOGIN));
        req.setAttribute("two", currentBundle.getString(LocaleManager.LOGIN_PAGE_PASSWORD));
        req.setAttribute("thr", currentBundle.getString(LocaleManager.LOGIN_PAGE_NO_ACCOUNT));
        req.setAttribute("fou", currentBundle.getString(LocaleManager.LOGIN_PAGE_REGISTER));

        req.setAttribute("cabinet", currentBundle.getString(LocaleManager.HEADER_CABINET));
        req.setAttribute("c_two", currentBundle.getString(LocaleManager.HEADER_ORDER));
        req.setAttribute("с_th", currentBundle.getString(LocaleManager.HEADER_ALL_REQUESTS));
        req.setAttribute("с_fo", currentBundle.getString(LocaleManager.HEADER_ARCHIVE));
        req.setAttribute("с_fiv", currentBundle.getString(LocaleManager.HEADER_LOGOUT));
        req.setAttribute("с_six", currentBundle.getString(LocaleManager.HEADER_PAY));

        req.setAttribute("m_one", currentBundle.getString(LocaleManager.HEADER_MANAGER_RECS));
        req.setAttribute("m_two", currentBundle.getString(LocaleManager.HEADER_MANAGER_PAY));
        req.setAttribute("mas_one", currentBundle.getString(LocaleManager.HEADER_MASTER_CURRENT));

        req.setAttribute("confirm_manager", currentBundle.getString("header.manager_confirm"));


        req.setAttribute("order_id", currentBundle.getString(LocaleManager.REQ_ID));
        req.setAttribute("order_master_login", currentBundle.getString(LocaleManager.MASTER_LOGIN));
        req.setAttribute("order_master_name", currentBundle.getString(LocaleManager.MASTER_NAME));
        req.setAttribute("order_master_surname", currentBundle.getString(LocaleManager.MASTER_SURNAME));
        req.setAttribute("order_date", currentBundle.getString(LocaleManager.DATE));
        req.setAttribute("order_name", currentBundle.getString(LocaleManager.ORDER_NAME));
        req.setAttribute("order_desc", currentBundle.getString(LocaleManager.ORDER_DESCRIPTION));
        req.setAttribute("order_price", currentBundle.getString(LocaleManager.ORDER_PRICE));
        req.setAttribute("order_status", currentBundle.getString(LocaleManager.ORDER_STATUS));
        req.setAttribute("all_order_field", currentBundle.getString(LocaleManager.ALL_REQS_FIELD));

        req.setAttribute("feedback", currentBundle.getString(LocaleManager.REQ_FEEDBACK));
        req.setAttribute("stars", currentBundle.getString(LocaleManager.REQ_STARS));

        req.setAttribute("pay", currentBundle.getString(LocaleManager.PAY));
        req.setAttribute("wait", currentBundle.getString(LocaleManager.WAITING_PAYMENT_USER));
        req.setAttribute("make_pay", currentBundle.getString(LocaleManager.MAKE_PAYMENT));

        req.setAttribute("client_name", currentBundle.getString(LocaleManager.MASTER_REQ_CLIENT_NAME));
        req.setAttribute("client_surname", currentBundle.getString(LocaleManager.MASTER_REQ_CLIENT_SURNAME));
        req.setAttribute("client_login", currentBundle.getString(LocaleManager.MASTER_REQ_CLIENT_LOGIN));
        req.setAttribute("change_status", currentBundle.getString(LocaleManager.MASTER_REQ_CHANGE_STATUS));
        req.setAttribute("start", currentBundle.getString("master.reqs_start_work"));
        req.setAttribute("finish", currentBundle.getString("master.reqs_finish_work"));
        req.setAttribute("current", currentBundle.getString("master.reqs_current_requests"));
        req.setAttribute("archive", currentBundle.getString("master.reqs_archive"));











        session.setAttribute("bundle", currentBundle);

        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
