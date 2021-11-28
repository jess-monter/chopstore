package choppr.chopstore.servicio;

import choppr.chopstore.datos.ResenaDatos;
import choppr.chopstore.excepciones.ForbiddenException;

/**
 * Interfaz que provee servicios relacionados con las reseñas
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

public interface ResenaServicio {

    /**
     * Regresa las reseñas del producto especificado y guarda el valor de la calificación promedio, como un porcentaje
     * @param idproducto es el identificador del producto
     * @param porcentaje es un arreglo de tamaño 1 donde se guarda como cadena el porcentaje de la calificación promedio
     * @return un arreglo con las reseñas del producto
     */
    
    public ResenaDatos [] obtenResenasCalificacion (String idproducto, String [] porcentaje);

    /**
     * Regresa el código del estado actual de la reseña para el usuario y producto especificados
     * @param idusuario es el identificador del usuario
     * @param idproducto es el identificador del producto
     * @return 0 si el usuario no ha comprado el producto, 1 si el usuario ha comprado el producto pero no lo ha reseñado y 2 si el usuario ha comprado el producto y lo ha reseñado
     */

    public int estadoResena (String idusuario, String idproducto);

    /**
     * Crea una nueva reseña de un usuario para un producto
     * @param idusuario es el identificador del usuario
     * @param idproducto es el identificador del producto
     * @param comentario es el contenido de la reseña
     * @param calificacion es la calificación de la reseña
     * @throws ForbiddenException si el usuario no ha comprado el producto, el usuario ya ha reseñado el producto, el comentario es null, el comentario es de longitud mayor a 256 o si la calificación no está entre 1 y 5
     */

    public void publicaResena (String idusuario, String idproducto, String comentario, String calificacion);

}
