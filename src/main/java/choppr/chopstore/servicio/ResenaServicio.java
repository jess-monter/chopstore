package choppr.chopstore.servicio;

import choppr.chopstore.datos.ResenaDatos;

public interface ResenaServicio {
    
    public ResenaDatos [] obtenResenasCalificacion (String idproducto, String [] porcentaje);

    public int estadoResena (String idusuario, String idproducto);

    public void publicaResena (String idusuario, String idproducto, String comentario, String calificacion);

}
