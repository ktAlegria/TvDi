/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import control.AuthBean;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gavellan
 */
public class AuthAdminFilter implements Filter {

    private FilterConfig configuration;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.configuration = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (((HttpServletRequest) request).getSession().getAttribute(
                AuthBean.USER_KEY) == null) {
            ((HttpServletResponse) response).sendRedirect("/CarruselNoticias/Restringido.jspx");
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        configuration = null;
    }
}
