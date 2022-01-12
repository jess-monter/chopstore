package choppr.chopstore.servicio;


import choppr.chopstore.datos.InvolucrarDatos;
import choppr.chopstore.excepciones.InvalidValueException;



/**
 * Interfaz que provee servicios relacionados con los productos
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.2
 */

public interface InvolucrarServicio {

    /**
     * Crea una nueva compra para un usuario
     * @param idcompra el identificador de la compra
     * @param idproducto el identificador del producto comprado
     * @param cantidad cantidad comprada del producto
     * @throws InvalidValueException si el nombre, imagen, descripción o detalles son de longitud mayor al máximo permitido o si el precio o cantidad son negativos
     */

    public InvolucrarDatos agregaProductosCompra (Integer idcompra, Integer idproducto, Integer cantidad);


}
