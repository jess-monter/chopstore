package choppr.chopstore.servicio.impl;

import choppr.chopstore.datos.SolicitudRegistro;
import choppr.chopstore.modelo.RolUsuario;
import choppr.chopstore.modelo.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistroServicio {

    private final UsuarioServicio usuarioService;
    private final CorreoValidador emailValidator;
    private final CorreoServicio emailService;

    public String registro(SolicitudRegistro req){
        RolUsuario r;
        boolean isValid = emailValidator.test(req.getCorreo());
        if (!isValid)
            throw new IllegalStateException("Correo no válido");
        if (req.isRole()) //isRole == true -> VENDEDOR
            r = RolUsuario.VENDEDOR;
        else
            r = RolUsuario.COMPRADOR;

        Usuario usr = new Usuario(req.getNombre(), req.getApellido(), req.getCorreo(), req.getTelefono(), r);
        String pwd = "Tu contraseña en Choppstore es: <b>" + usr.getPassword() + "</b>";
        emailService.envia(req.getCorreo(), pwd, "Contraseña Choppstore");
        return usuarioService.registraUsuario(usr);
    }
}
