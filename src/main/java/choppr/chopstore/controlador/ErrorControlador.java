package choppr.chopstore.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorControlador {
    @RequestMapping("/error")
    public String manejoDeErrores(HttpServletRequest request){
        // TODO: Manejo de errores apropiado.
        return "error";
    }
}
