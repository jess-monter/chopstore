package choppr.chopstore.modelo;

import lombok.Data;
import java.io.Serializable;

/**
 * Clase que representa el identifcador de una instancia de entidad de la tabla involucrar en la base de datos
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

@ Data
public class IdInvolucrar implements Serializable {

    private Integer idcompra;
    private Integer idproducto;

}
