package main.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kuznetsov on 20/04/2017.
 */

public class AdminList implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AdminList.class);

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        String email = (String) ((HttpServletRequest) servletRequest).getSession().getAttribute("email");
        Integer group = (Integer) ((HttpServletRequest) servletRequest).getSession().getAttribute("group");


        if (email != null && group == 1) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + "/auth/");
        }
    }

    public void destroy() {

    }
}