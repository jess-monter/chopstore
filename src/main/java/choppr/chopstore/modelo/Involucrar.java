package choppr.chopstore.modelo;

import lombok.Data;
import javax.persistence.*;

@ Data
@ Entity
@ Table (name = "involucrar")
@ IdClass (IdInvolucrar.class)
public class Involucrar {

    @ Id
    @ Column (name = "idcompra", insertable = false, updatable = false)
    private Integer idcompra;

    @ Id
    @ Column (name = "idproducto", insertable = false, updatable = false)
    private Integer idproducto;

    @ Column (name = "cantidad")
    private Integer cantidad;
    
    @ JoinColumn (name = "idcompra", referencedColumnName = "idcompra")
    @ ManyToOne (optional = false)
    private Compra compra;

    @ JoinColumn (name = "idproducto", referencedColumnName = "idproducto")
    @ ManyToOne (optional = false)
    private Producto producto;

}
