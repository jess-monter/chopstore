package choppr.chopstore.repositorio;

import choppr.chopstore.modelo.Involucrar;
import choppr.chopstore.modelo.IdInvolucrar;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Interfaz para conectar con la base de datos y realizar consultas relacionadas a la tabla involucrar
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

@ Repository
public interface InvolucrarRepositorio extends JpaRepository <Involucrar, IdInvolucrar> {
    
    /**
     * Realiza una consulta que recupera el identificador y el total de veces que ha sido comprado, de los cuatro productos más vendidos
     * @return una lista con los resultados de la consulta
     */
    
    @ Query (value = "SELECT idproducto, SUM(cantidad) AS total FROM involucrar GROUP BY idproducto ORDER BY total DESC LIMIT 4", nativeQuery = true)
    public List <Object []> consultaMayoresVentas ();

}
