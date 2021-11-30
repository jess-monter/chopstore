package choppr.chopstore.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthHandler implements AuthenticationSuccessHandler{
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse, Authentication authentication)
            throws IOException, ServletException {
        //System.out.println(httpServletRequest.getHeader("referer"));
        if(httpServletRequest.getHeader("referer").equals("http://localhost:8080/register/confirm") || httpServletRequest.getHeader("referer").equals("http://localhost:8080/login?logout"))
            httpServletResponse.sendRedirect("/");
        else
            httpServletResponse.sendRedirect(httpServletRequest.getHeader("referer"));

    }
}
