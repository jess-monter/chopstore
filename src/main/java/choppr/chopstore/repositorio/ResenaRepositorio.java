package choppr.chopstore.repositorio;

import choppr.chopstore.modelo.Resena;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@ Repository
public interface ResenaRepositorio extends JpaRepository <Resena, Integer> {

    public List <Resena> findResenasByIdproducto (Integer idproducto);

}