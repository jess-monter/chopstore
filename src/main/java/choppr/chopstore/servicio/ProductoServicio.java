package choppr.chopstore.servicio;

import choppr.chopstore.datos.ProductoDatos;
import choppr.chopstore.excepciones.ElementNotFoundException;
import choppr.chopstore.excepciones.ForbiddenException;

/**
 * Interfaz que provee servicios relacionados con los productos
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.1
 */

public interface ProductoServicio {  

    /**
     * Crea un nuevo producto
     * @param productoDatos datos del producto
     * @return los datos del producto
     */
    
    public ProductoDatos creaProducto (ProductoDatos productoDatos);

    /**
     * Regresa los productos que coninciden con los parámetros de búsqueda especificados
     * @param busqueda es una cadena con palabras clave para hacer buscar coincidencias con el nombre de los productos
     * @param categoria es la categoría donde se van a buscar los productos
     * @return un arreglo con los productos encontrados, organizados por grupos de 4 productos, o null si no se encontraron productos
     */
    
    public ProductoDatos [] [] buscaProductos (String busqueda, String categoria);

    /**
     * Regresa los productos del usuario especificado
     * @param idusario es el identificador del usuario
     * @return un arreglo con todos los productos del usuario o null si el usuario no tiene productos
     */

    public ProductoDatos [] obtenProductosDeUsuario (String idusario);

    /**
     * Regresa el producto especificado
     * @param idproducto es el identificador del producto
     * @return el producto con el identificador especificado
     * @throws ElementNotFoundException si no exíste el producto
     */

    public ProductoDatos consultaPorId (String idproducto);

    /**
     * Regresa los productos recomendados para el usuario especificado
     * @param idusuario es el identificador del usuario
     * @return un arreglo de 4 productos recomendados
     */

    public ProductoDatos [] recomiendaProductos (String idusuario);

    /**
     * Regresa los productos más vendidos
     * @return un arreglo de los 4 productos más vendidos
     */

    public ProductoDatos [] obtenProductosMasVendidos ();

    /**
     * Crea un nuevo producto para un usuario
     * @param idusuario es el identificador del usuario
     * @param nombreCategoria es el nombre de la categoría del producto
     * @param nombre es el nombre del producto
     * @param descripcion es la descripción del producto
     * @param precio es el precio del producto
     * @param imagen es la imagen del producto
     * @param cantidad es la cantidad del producto
     * @param detalles son los detalles del producto
     * @throws ElementNotFoundException si no existe la categoría
     * @throws ForbiddenException si el nombre, imagen, descripción o detalles son de longitud mayor al máximo permitido o si el precio o cantidad son negativos
     */

    public void publicaProducto (String idusuario, String nombreCategoria, String nombre, String descripcion, String precio, String imagen, String cantidad, String detalles);

    /**
     * Elimina el producto especificado
     * @param idusuario es el identificador del usuario que desea eliminar el producto
     * @param idproducto es el identificador del producto que se desea eliminar
     * @throws ElementNotFoundException si no exíste el producto
     * @throws ForbiddenException si el usuario no es dueño del producto
     */

    public void eliminaProducto (String idusuario, String idproducto);


    /**
     * Regresa el producto especificado modificado
     * @param idproducto es el identificador del producto a modificar
     * @param productoDatos datos a modificar
     * @return el producto con el identificador especificado
     * @throws ElementNotFoundException si no exíste el producto
     */

    public ProductoDatos editaProducto (String idproducto, ProductoDatos productoDatos);

}
