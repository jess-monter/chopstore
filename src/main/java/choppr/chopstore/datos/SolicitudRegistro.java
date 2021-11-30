package choppr.chopstore.datos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SolicitudRegistro {

    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private boolean role;
}
