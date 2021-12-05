package choppr.chopstore.repositorio;

import choppr.chopstore.modelo.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz para conectar con la base de datos y realizar consultas relacionadas a la tabla categoria
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

@ Repository
public interface CategoriaRepositorio extends JpaRepository <Categoria, Integer> {

    /**
     * Realiza una consulta que recupera la categoría con el nombre especificado
     * @param nombre es el nombre de la categoría
     * @return la categoria recuperada o null si no exíste la categoría
     */

    public Categoria findCategoriaByNombre (String nombre);
    
}
