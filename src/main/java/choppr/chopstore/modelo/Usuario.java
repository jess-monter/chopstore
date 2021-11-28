package choppr.chopstore.modelo;

import lombok.Data;
import javax.persistence.*;

/**
 * Clase que representa una instancia de entidad de la tabla usuario en la base de datos
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

@ Data
@ Entity
@ Table (name = "usuario")
public class Usuario {

    @ Id
    @ GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idusuario;

    @ Column (name = "nombre")
    private String nombre;

    @ Column (name = "apellido")
    private String apellido;

    @ Column (name = "correo")
    private String correo;

    @ Column (name = "contrasena")
    private String contrasena;

    @ Column (name = "rol")
    private String rol;

    @ Column (name = "telefono")
    private String telefono;

}
