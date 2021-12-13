package choppr.chopstore.servicio.impl;

import choppr.chopstore.datos.SolicitudRegistro;
import choppr.chopstore.modelo.RolUsuario;
import choppr.chopstore.modelo.Usuario;
import choppr.chopstore.servicio.validacion.CorreoValidador;
import choppr.chopstore.servicio.validacion.TelefonoValidador;
import choppr.chopstore.excepciones.InvalidValueException;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Clase que maneja el registro del usuario como servicio
 * @author Eric Toprorek Coca
 * @author Fransisco Alejandro Arganis Ramirez
 * @author Jessica Monter Gallardo
 * @version 1.3
 */

@Service
@AllArgsConstructor
public class RegistroServicio {

    private final UsuarioServicio usuarioService;
    private final CorreoValidador emailValidator;
    private final TelefonoValidador phoneNumberValidator;
    private final CorreoServicio emailService;

    /**
     * Método que se encarga del registro de un usuario a partir de una solicitud de registro
     * @param req Una solicitud de registro
     * @return El sitio a dónde será redirigido el controlador una vez completado el registro.
     */

    public String registro(SolicitudRegistro req){
        RolUsuario r;
        if (! (emailValidator.test (req.getCorreo ()) && phoneNumberValidator.test (req.getTelefono ()))) throw new InvalidValueException ();
        if (req.isRole()) //isRole == true -> VENDEDOR
            r = RolUsuario.VENDEDOR;
        else
            r = RolUsuario.COMPRADOR;
        Usuario usr = new Usuario(req.getNombre(), req.getApellido(), req.getCorreo(), req.getTelefono(), r);
        String pwd = usr.getPassword ();
        String redirect = usuarioService.registraUsuario (usr);
        emailService.enviaContrasena (usr.getCorreo (), pwd);
        return redirect;
    }
}
