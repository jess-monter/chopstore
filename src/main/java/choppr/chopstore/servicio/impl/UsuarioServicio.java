package choppr.chopstore.servicio.impl;

import choppr.chopstore.modelo.Usuario;
import choppr.chopstore.repositorio.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    public String registraUsuario(Usuario usuario){
        boolean coincidence = usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent();
        if(coincidence)
            throw new IllegalStateException("El correo ya está registrado.");

        String encoded = bCryptPasswordEncoder.encode(usuario.getPassword());

        usuario.setContrasena(encoded);
        usuario.setEnabled(true);
        usuarioRepository.save(usuario);
        return "redirect:/register/confirm";
    }
}
