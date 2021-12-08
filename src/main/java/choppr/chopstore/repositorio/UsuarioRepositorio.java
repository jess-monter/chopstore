package choppr.chopstore.repositorio;

import choppr.chopstore.modelo.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interfaz para conectar con la base de datos y realizar consultas relacionadas a la tabla usuario
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

@ Repository
public interface UsuarioRepositorio extends JpaRepository <Usuario, Integer> {
    
    /**
     * Realiza una consulta que recupera el usuario con el identificador especificado
     * @param idusuario es el identificador del usuario
     * @return el usuario recuperado o null si no exíste el usuario
     */

    public Usuario findUsuarioByIdusuario (Integer idusuario);

    /**
     * Metodo que consulta a un usuario para contrarlo por correo
     * @param correo El correo electronico
     * @return El usuario recuperado.
     */
    Optional<Usuario> findByCorreo(String correo);

}
