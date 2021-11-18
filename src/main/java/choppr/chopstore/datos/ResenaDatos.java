package choppr.chopstore.datos;

import choppr.chopstore.modelo.Resena;

import lombok.Data;

@ Data
public class ResenaDatos {

    private String usuario;
    private Integer calificacion;
    private String comentario;

    public ResenaDatos (Resena resena) {
        usuario = resena.getUsuario ().getNombre ();
        calificacion = resena.getCalificacion ();
        comentario = resena.getComentario ();
    }
    
}
