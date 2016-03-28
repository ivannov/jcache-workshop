package bg.jug.guestbook.security;

import bg.jug.guestbook.users.UserContext;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ivan St. Ivanov
 */
@WebFilter(urlPatterns = "/app/comment")
public class LoggedInUserFilter implements Filter {

    @Inject
    private UserContext userContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        if (userContext.getCurrentUser() != null) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse)response).sendRedirect("login");
        }
    }

    @Override public void destroy() {
    }
}
