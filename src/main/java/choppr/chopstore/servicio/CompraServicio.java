package choppr.chopstore.servicio;

import choppr.chopstore.datos.CompraDatos;
import choppr.chopstore.excepciones.ElementNotFoundException;
import choppr.chopstore.excepciones.ForbiddenException;
import choppr.chopstore.excepciones.InvalidValueException;

import java.time.LocalDate;


/**
 * Interfaz que provee servicios relacionados con los productos
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.2
 */

public interface CompraServicio {

    /**
     * Crea una nueva compra para un usuario
     * @param idusuario es el identificador del usuario
     * @param pago es el nombre de la categoría del producto
     * @param fecha es el nombre del productoía
     * @throws InvalidValueException si el nombre, imagen, descripción o detalles son de longitud mayor al máximo permitido o si el precio o cantidad son negativos
     */

    public CompraDatos hazCompra (String idusuario, Double pago, LocalDate fecha);
    
}
