package choppr.chopstore.datos;

import choppr.chopstore.modelo.Compra;

import java.time.LocalDate;

import lombok.Data;


/**
 * Clase que representa los datos de una compra y que se puede mandar a la capa de vista sin comprometer información sensible
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */
public class CompraDatos {

    private Integer idcompra;
    private String usuario;
    private Double pago;
    private LocalDate fecha;


    /**
     * Construye un nuevo objeto CompraDatos a partir de una instancia de entidad de la tabla Compra
     * @param producto un objeto Compra recuperado de la base de datos
     */
    public CompraDatos(Compra compra) {
        idcompra = compra.getIdcompra();
        usuario = compra.getUsuario().getNombre();
        pago = compra.getPago();
        fecha = compra.getFecha();
    }

}
