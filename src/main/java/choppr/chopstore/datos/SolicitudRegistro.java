package choppr.chopstore.datos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase que modela los campos de un formulario de registro
 * @author Eric Toporek Coca
 * @author Fransisco Alejandro Arganis Ramirez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */
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
