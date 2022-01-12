package choppr.chopstore.servicio.impl;

import choppr.chopstore.modelo.Usuario;
import choppr.chopstore.repositorio.UsuarioRepositorio;
import choppr.chopstore.excepciones.EmailAlreadyTakenException;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Servicio que gestiona la interacción con los usuarios en cuestiones de registro e inicio de sesión
 * @author Eric Toporek Coca
 * @author Fransisco Alejandro Arganis Ramirez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class UsuarioServicio implements UserDetailsService {

    private final UsuarioRepositorio usuarioRepository;
    private final static String USER_NOT_FOUND = "El usuario %s no fue encontrado";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        return usuarioRepository.findByCorreo(correo)
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND,correo)));
    }

    /**
     * Método que registra a un usuario para mandarlo a la base de datos.
     * @param usuario  Un objeto de tipo usuario
     * @return El sitio de redirección para el controlador.
     */
    public String registraUsuario(Usuario usuario){
        boolean coincidence = usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent();
        if (coincidence) throw new EmailAlreadyTakenException (usuario.getCorreo ());

        String encoded = bCryptPasswordEncoder.encode(usuario.getPassword());

        usuario.setContrasena(encoded);
        usuario.setEnabled(true);
        usuarioRepository.save(usuario);
        return "redirect:/register/confirm";
    }

    /**
     * Obtiene una representación en cadena del identificador del usuario que realiza una petición
     * @param autentificacion es el token de autentificación del usuario
     * @return el identificador del usuario
     */

    public String obtenIdusuario (Authentication autentificacion) {
        Usuario usuario = (Usuario) autentificacion.getPrincipal ();
        return usuario.getIdusuario ().toString ();
    }

    /**
     * Obtiene una representación en cadena del identificador del usuario que realiza una petición
     * @param autentificacion es el token de autentificación del usuario
     * @return el identificador del usuario
     */

    public String obtenCorreousuario (Authentication autentificacion) {
        Usuario usuario = (Usuario) autentificacion.getPrincipal ();
        return usuario.getCorreo ();
    }

}
