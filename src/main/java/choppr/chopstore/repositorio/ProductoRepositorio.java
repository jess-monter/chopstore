package choppr.chopstore.repositorio;

import choppr.chopstore.modelo.Producto;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interfaz para conectar con la base de datos y realizar consultas relacionadas a la tabla producto
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

@ Repository
public interface ProductoRepositorio extends JpaRepository <Producto, Integer> {

    /**
     * Realiza una consulta que recupera todos los productos
     * @return una lista con los resultados de la consulta
     */

    public Producto createProduct(Producto producto);

    /**
     * Realiza una consulta que recupera todos los productos
     * @return una lista con los resultados de la consulta
     */

    public List <Producto> findAll ();

    /**
     * Realiza una consulta que recupera el producto con el identificador especificado
     * @param idproducto es el identificador del producto
     * @return el producto recuperado o null si no exíste el producto
     */

    public Producto findProductoByIdproducto (Integer idproducto);

    /**
     * Realiza una consulta que recupera todos los productos de un usuario
     * @param idusuario es el identificador del usuario
     * @return una lista con los resultados de la consulta
     */

    public List <Producto> findProductosByIdusuario (Integer idusuario);

    /**
     * Elimina, si exíste, el producto especificado de la base de datos
     * @param producto es el producto a eliminar
     */

    public void delete (Producto producto);

    /**
     * Realiza una consulta que recupera todos los productos tales que su nombre coincide con una palabra clave y tienen la categoría especificada
     * @param palabraClave es la cadena con la que se va a buscar una coincidencia del nombre de los productos
     * @param categoria es la categoría que deben tener los productos
     * @return una lista con los resultados de la consulta
     */

    @ Query (value = "SELECT * FROM producto JOIN categoria WHERE producto.idcategoria = categoria.idcategoria AND categoria.nombre = :c AND producto.nombre LIKE :pc", nativeQuery = true)
    public List <Producto> buscaCoincidenciasPorCategoria (@ Param ("pc") String palabraClave, @ Param ("c") String categoria);

    /**
     * Realiza una consulta que recupera todos los productos tales que su nombre coincide con una palabra clave
     * @param palabraClave es la cadena con la que se va a buscar una coincidencia del nombre de los productos
     * @return una lista con los resultados de la consulta
     */

    @ Query (value = "SELECT * FROM producto WHERE nombre LIKE :pc", nativeQuery = true)
    public List <Producto> buscaTodasCoincidencias (@ Param ("pc") String palabraClave);

    /**
     * Realiza una consulta que recupera todos los productos tales que tienen la categoría especificada
     * @param categoria es la categoría que deben tener los productos
     * @return una lista con los resultados de la consulta
     */

    @ Query (value = "SELECT * FROM producto JOIN categoria WHERE producto.idcategoria = categoria.idcategoria AND categoria.nombre = :c", nativeQuery = true)
    public List <Producto> buscaTodasCategorias (@ Param ("c") String categoria);

}
