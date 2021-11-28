package choppr.chopstore.datos;

import choppr.chopstore.modelo.Resena;

import lombok.Data;

/**
 * Clase que representa los datos de una reseña y que se puede mandar a la capa de vista sin comprometer información sensible
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

@ Data
public class ResenaDatos {

    private String usuario;
    private Integer calificacion;
    private String comentario;

    /**
     * Construye un nuevo objeto ResenaDatos a partir de una instancia de entidad de la tabla resena
     * @param producto un objeto Resena recuperado de la base de datos
     */

    public ResenaDatos (Resena resena) {
        usuario = resena.getUsuario ().getNombre ();
        calificacion = resena.getCalificacion ();
        comentario = resena.getComentario ();
    }
    
}
