package choppr.chopstore.repositorio;

import choppr.chopstore.modelo.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@ Repository
public interface UsuarioRepositorio extends JpaRepository <Usuario, Integer> {
    
    public Usuario findUsuarioByIdusuario (Integer idusuario);

}
