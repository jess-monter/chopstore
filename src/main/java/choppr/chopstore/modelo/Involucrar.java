package choppr.chopstore.modelo;

import lombok.Data;


import javax.persistence.*;

/**
 * Clase que representa una instancia de entidad de la tabla involucrar en la base de datos
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

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
    
    @ JoinColumn (name = "idcompra", referencedColumnName = "idcompra", insertable = false, updatable = false)
    @ ManyToOne (optional = false)
    private Compra compra;

    @ JoinColumn (name = "idproducto", referencedColumnName = "idproducto", insertable = false, updatable = false)
    @ ManyToOne (optional = false)
    private Producto producto;

}
