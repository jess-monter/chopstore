package choppr.chopstore.controlador;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Clase que atiende las peticiones generales al servidor
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.6
 */

@ Controller
public class ChopstoreControlador {

    /** Atiende una petición de la página de inicio cuando no se ha iniciado sesión
     * @return la página de inicio
     */

    @ RequestMapping ("/")
    public String inicio (Authentication auth) {
        if(auth != null) {
            String role = auth.getAuthorities().toString();
            if (role.contains("VENDEDOR"))
                return "redirect:/vendedor";
            else if (role.contains("COMPRADOR"))
                return "redirect:/comprador";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String viewLoginPage(){
        return "login";
    }

    @GetMapping("/register/confirm")
    public String muestraLog(){
        return "redirect:/login";
    }

}