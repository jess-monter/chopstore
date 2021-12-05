package choppr.chopstore.controlador;

import choppr.chopstore.datos.SolicitudRegistro;
import choppr.chopstore.servicio.impl.RegistroServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/register")
@AllArgsConstructor
public class RegistroUsuarioController {

    private final RegistroServicio registroService;

    @GetMapping
    public  String muestraRegistro(Model model){
        model.addAttribute("req", new SolicitudRegistro());
        return "register";
    };

    @PostMapping
    public String registro(SolicitudRegistro req){
        return registroService.registro(req);
    }
}
