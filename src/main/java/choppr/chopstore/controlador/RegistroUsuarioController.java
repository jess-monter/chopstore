package choppr.chopstore.controlador;

import choppr.chopstore.datos.SolicitudRegistro;
import choppr.chopstore.servicio.impl.RegistroServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Clase que representa al controlador para el registro de un usuario
 * @author Eric Toporek Coca
 * @author Fransisco Alejandro Arganis Ramirez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */
@Controller
@RequestMapping(path = "/register")
@AllArgsConstructor
public class RegistroUsuarioController {

    private final RegistroServicio registroService;


    /**
     * Atiende una solicitud de tipo GET sobre el tipo de registro
     * @param model EL modelo actual del sitio
     * @return El sitio con el modelo agregado a un parámetro.
     */
    @GetMapping
    public  String muestraRegistro(Model model){
        model.addAttribute("req", new SolicitudRegistro());
        return "register";
    };

    /**
     * Atiende una solicitud de tipo POST en el sitio de registro
     * @param req Una solicitud de registro creada por el modelo
     * @return Un sitio de redirección, en este caso sería el sitio para inicio de sesión.
     */
    @PostMapping
    public String registro(SolicitudRegistro req){
        return registroService.registro(req);
    }
}
