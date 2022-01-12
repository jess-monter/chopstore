package choppr.chopstore.datos;

import choppr.chopstore.modelo.Involucrar;

import lombok.Data;


/**
 * Clase que representa los datos de una compra y que se puede mandar a la capa de vista sin comprometer información sensible
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */
@ Data
public class InvolucrarDatos {

    private Integer idcompra;
    private Integer idproducto;
    private Integer cantidad;
    
    /**
     * Construye un nuevo objeto CompraDatos a partir de una instancia de entidad de la tabla Compra
     * @param producto un objeto Compra recuperado de la base de datos
     */
    public InvolucrarDatos(Involucrar involucrar) {
        idcompra = involucrar.getIdcompra();
        idproducto = involucrar.getIdproducto();
        cantidad = involucrar.getCantidad();
    }
}
