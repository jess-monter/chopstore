package choppr.chopstore.modelo;

import lombok.Data;
import javax.persistence.*;

/**
 * Clase que representa una instancia de entidad de la tabla producto en la base de datos
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

@ Data
@ Entity
@ Table (name = "producto")
public class Producto {
    
    @ Id
    @ GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idproducto;

    @ Column (name = "idusuario", insertable = false, updatable = false)
    private Integer idusuario;

    @ Column (name = "idcategoria", insertable = false, updatable = false)
    private Integer idcategoria;

    @ Column (name = "nombre")
    private String nombre;

    @ Column (name = "descripcion")
    private String descripcion;

    @ Column (name = "precio", precision = 10, scale = 2)
    private Double precio;

    @ Column (name = "imagen")
    private String imagen;

    @ Column (name = "cantidad")
    private Integer cantidad;

    @ Column (name = "detalles")
    private String detalles;

    @ JoinColumn (name = "idusuario", referencedColumnName = "idusuario")
    @ ManyToOne (optional = false)
    private Usuario usuario;

    @ JoinColumn (name = "idcategoria", referencedColumnName = "idcategoria")
    @ ManyToOne (optional = false)
    private Categoria categoria;

}
