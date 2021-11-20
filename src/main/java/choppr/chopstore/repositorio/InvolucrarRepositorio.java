package choppr.chopstore.repositorio;

import choppr.chopstore.modelo.Involucrar;
import choppr.chopstore.modelo.IdInvolucrar;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@ Repository
public interface InvolucrarRepositorio extends JpaRepository <Involucrar, IdInvolucrar> {
    
    @ Query (value = "SELECT idproducto, SUM(cantidad) AS total FROM involucrar GROUP BY idproducto ORDER BY total DESC LIMIT 4", nativeQuery = true)
    public List <Object []> consultaMayoresVentas ();

}
