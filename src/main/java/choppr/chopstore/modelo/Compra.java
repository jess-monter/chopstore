package choppr.chopstore.modelo;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@ Data
@ Entity
@ Table (name = "compra")
public class Compra {

    @ Id
    @ GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idcompra;

    @ Column (name = "idusuario", insertable = false, updatable = false)
    private Integer idusuario;

    @ Column (name = "pago", precision = 10, scale = 2)
    private Double pago;

    @ Column (name = "fecha")
    private LocalDate fecha;

    @ JoinColumn (name = "idusuario", referencedColumnName = "idusuario")
    @ ManyToOne (optional = false)
    private Usuario usuario;
    
}
