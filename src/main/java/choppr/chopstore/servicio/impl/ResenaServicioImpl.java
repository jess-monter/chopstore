package choppr.chopstore.servicio.impl;

import choppr.chopstore.modelo.Resena;
import choppr.chopstore.repositorio.ResenaRepositorio;
import choppr.chopstore.servicio.ResenaServicio;
import choppr.chopstore.datos.ResenaDatos;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@ Service
public class ResenaServicioImpl implements ResenaServicio {
    
    @ Autowired
    private ResenaRepositorio resenaRepositorio;

    @ Override
    public ResenaDatos [] obtenResenasCalificacion (String idproducto, String [] porcentaje) {
        List <Resena> todasResenasProducto = resenaRepositorio.findResenasByIdproducto (Integer.parseInt (idproducto));
        if (todasResenasProducto.size () == 0) return null;
        ResenaDatos [] resenas = new ResenaDatos [todasResenasProducto.size ()];
        double promedio = 0;
        int indice = 0;
        for (Resena resena : todasResenasProducto) {
            resenas [indice] = new ResenaDatos (resena);
            promedio += resena.getCalificacion ();
            indice ++;
        }
        promedio /= resenas.length;
        porcentaje [0] = String.valueOf (Math.round (20 * promedio));
        return resenas;
    }

}
