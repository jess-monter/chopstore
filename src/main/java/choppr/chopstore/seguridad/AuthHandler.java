package choppr.chopstore.seguridad;

import choppr.chopstore.modelo.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String rol = auth.getAuthorities().toString();
        Usuario usr = (Usuario)auth.getPrincipal();
        httpServletRequest.getSession().setAttribute("idUsuario",Integer.toString(usr.getIdusuario()));
        if (rol.contains("VENDEDOR"))
            httpServletResponse.sendRedirect("/vendedor");
        else if (rol.contains("COMPRADOR"))
            httpServletResponse.sendRedirect("/comprador");
    }
}
