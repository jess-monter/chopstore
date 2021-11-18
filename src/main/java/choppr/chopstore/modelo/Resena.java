package choppr.chopstore.modelo;

import lombok.Data;
import javax.persistence.*;

@ Data
@ Entity
@ Table (name = "resena")
public class Resena {

    @ Id
    @ GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idresena;

    @ Column (name = "idusuario", insertable = false, updatable = false)
    private Integer idusuario;

    @ Column (name = "idproducto", insertable = false, updatable = false)
    private Integer idproducto;

    @ Column (name = "calificacion")
    private Integer calificacion;

    @ Column (name = "comentario")
    private String comentario;

    @ JoinColumn (name = "idusuario", referencedColumnName = "idusuario")
    @ ManyToOne (optional = false)
    private Usuario usuario;

    @ JoinColumn (name = "idproducto", referencedColumnName = "idproducto")
    @ ManyToOne (optional = false)
    private Producto producto;

}