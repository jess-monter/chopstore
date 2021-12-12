package choppr.chopstore.servicio.impl;

import choppr.chopstore.datos.SolicitudRegistro;
import choppr.chopstore.modelo.RolUsuario;
import choppr.chopstore.modelo.Usuario;
import choppr.chopstore.servicio.validacion.CorreoValidador;
import choppr.chopstore.servicio.validacion.TelefonoValidador;
import choppr.chopstore.excepciones.InvalidValueException;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistroServicio {

    private final UsuarioServicio usuarioService;
    private final CorreoValidador emailValidator;
    private final TelefonoValidador phoneNumberValidator;
    private final CorreoServicio emailService;

    public String registro(SolicitudRegistro req){
        RolUsuario r;
        if (! (emailValidator.test (req.getCorreo ()) && phoneNumberValidator.test (req.getTelefono ()))) throw new InvalidValueException ();
        if (req.isRole()) //isRole == true -> VENDEDOR
            r = RolUsuario.VENDEDOR;
        else
            r = RolUsuario.COMPRADOR;
        Usuario usr = new Usuario(req.getNombre(), req.getApellido(), req.getCorreo(), req.getTelefono(), r);
        String pwd = "Tu contraseña en Choppstore es: <b>" + usr.getPassword() + "</b>";
        String redirect = usuarioService.registraUsuario(usr);
        emailService.envia(req.getCorreo(), pwd, "Contraseña Choppstore");
        return redirect;
    }
}
