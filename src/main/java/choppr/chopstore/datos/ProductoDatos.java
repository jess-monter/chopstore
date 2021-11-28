package choppr.chopstore.datos;

import choppr.chopstore.modelo.Producto;

import lombok.Data;

/**
 * Clase que representa los datos de un producto y que se puede mandar a la capa de vista sin comprometer información sensible
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

@ Data
public class ProductoDatos {
    
    private Integer idproducto;
    private String categoria;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;
    private Integer cantidad;
    private String detalles;

    /**
     * Construye un nuevo objeto ProductoDatos a partir de una instancia de entidad de la tabla producto
     * @param producto un objeto Producto recuperado de la base de datos
     */
    
    public ProductoDatos (Producto producto) {

        idproducto = producto.getIdproducto ();
        categoria = producto.getCategoria ().getNombre ();
        nombre = producto.getNombre ();
        descripcion = producto.getDescripcion ();
        precio = producto.getPrecio ();
        imagen = producto.getImagen ();
        cantidad = producto.getCantidad ();
        detalles = producto.getDetalles ();

    }

}
