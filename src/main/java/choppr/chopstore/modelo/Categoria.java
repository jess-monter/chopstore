package choppr.chopstore.modelo;

import lombok.Data;
import javax.persistence.*;

/**
 * Clase que representa una instancia de entidad de la tabla categoria en la base de datos
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

@ Data
@ Entity
@ Table (name = "categoria")
public class Categoria {
    
    @ Id
    @ GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idcategoria;

    @ Column (name = "nombre")
    private String nombre;

}
