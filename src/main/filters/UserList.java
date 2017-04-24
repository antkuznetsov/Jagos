package main.filters;

import main.controllers.AuthController;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kuznetsov on 20/04/2017.
 */

public class UserList implements Filter {

    private static final Logger LOGGER = Logger.getLogger(UserList.class);

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        String email = (String) ((HttpServletRequest) servletRequest).getSession().getAttribute("email");

        if (email != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + "/auth/");
        }
    }

    public void destroy() {

    }
}