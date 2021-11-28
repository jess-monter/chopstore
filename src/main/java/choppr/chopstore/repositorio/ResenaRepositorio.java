package choppr.chopstore.repositorio;

import choppr.chopstore.modelo.Resena;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz para conectar con la base de datos y realizar consultas relacionadas a la tabla resena
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

@ Repository
public interface ResenaRepositorio extends JpaRepository <Resena, Integer> {

    /**
     * Realiza una consulta que recupera todas las reseñas del producto con el identificador especificado
     * @param idproducto es el identificador del producto
     * @return una lista con los resultados de la consulta
     */

    public List <Resena> findResenasByIdproducto (Integer idproducto);

    /**
     * Realiza una consulta que recupera la reseña del usuario y producto especificados
     * @param idusuario es el identificador del usuario
     * @param idproducto es el identificador del producto
     * @return la reseña recuperada o null si no exíste la reseña
     */

    public Resena findResenaByIdusuarioAndIdproducto (Integer idusuario, Integer idproducto);

    /**
     * Inserta o actualiza, si ya exíste, la reseña especificada de la base de datos
     * @param resena es la reseña a insertar o actualizar
     * @return la reseña guardada
     */

    public <S extends Resena> S save (S resena);

}