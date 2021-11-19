package choppr.chopstore.modelo;

import lombok.Data;
import javax.persistence.*;

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
