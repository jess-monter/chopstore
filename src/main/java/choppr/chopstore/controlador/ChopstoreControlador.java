package choppr.chopstore.controlador;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

/**
 * Clase que atiende peticiones generales al servidor
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.7
 */

@ Controller
public class ChopstoreControlador {

    /**
     * Atiende una petición de la página de inicio
     * @param autentificacion es el token de autentificación del usuario
     * @return la página comprador, vendedor o de inicio de sesión, cuando el usuario es un comprador, vendedor o no ha iniciado sesión, respectivamente
     */

    @ RequestMapping ("/")
    public String inicio (Authentication autentificacion) {
        if (autentificacion != null) {
            String role = autentificacion.getAuthorities().toString();
            if (role.contains("VENDEDOR"))
                return "redirect:/vendedor";
            else if (role.contains("COMPRADOR"))
                return "redirect:/comprador";
        }
        return "redirect:/login";
    }

    /**
     * Atiende una solicitud de la ruta al inicio de sesión
     * @return El template del sitio de inicio de sesión
     */
    @GetMapping("/login")
    public String viewLoginPage(){
        return "login";
    }

    /**
     * Atiende una solicitud una vez que se realiza un registro exitoso
     * @param modelo es el contenedor con la información que se envía a la página
     * @return el sitio de inicio de sesión con un mensaje de registro exitoso
     */

    @ GetMapping ("/register/confirm")
    public String muestraLog (Model modelo) {
        modelo.addAttribute ("regExito", true);
        return "login";
    }

}