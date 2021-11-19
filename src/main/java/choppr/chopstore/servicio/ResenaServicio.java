package choppr.chopstore.servicio;

import choppr.chopstore.datos.ResenaDatos;

public interface ResenaServicio {
    
    public ResenaDatos [] obtenResenasCalificacion (String idproducto, String [] porcentaje);

}
