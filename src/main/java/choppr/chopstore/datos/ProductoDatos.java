package choppr.chopstore.datos;

import choppr.chopstore.modelo.Producto;

import lombok.Data;

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
    
    public ProductoDatos (Producto producto) {

        idproducto = producto.getIdproducto ();
        nombre = producto.getNombre ();
        descripcion = producto.getDescripcion ();
        precio = producto.getPrecio ();
        imagen = producto.getImagen ();
        cantidad = producto.getCantidad ();
        detalles = producto.getDetalles ();

    }

}
