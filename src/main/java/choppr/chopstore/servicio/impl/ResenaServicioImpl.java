package choppr.chopstore.servicio.impl;

import choppr.chopstore.modelo.Resena;
import choppr.chopstore.repositorio.CompraRepositorio;
import choppr.chopstore.repositorio.ProductoRepositorio;
import choppr.chopstore.repositorio.ResenaRepositorio;
import choppr.chopstore.repositorio.UsuarioRepositorio;
import choppr.chopstore.servicio.ResenaServicio;
import choppr.chopstore.datos.ResenaDatos;
import choppr.chopstore.modelo.Compra;
import choppr.chopstore.excepciones.ForbiddenException;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@ Service
public class ResenaServicioImpl implements ResenaServicio {

    @ Autowired
    private ResenaRepositorio resenaRepositorio;

    @ Autowired
    private CompraRepositorio compraRepositorio;

    @ Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @ Autowired
    private ProductoRepositorio productoRepositorio;

    @ Override
    public ResenaDatos [] obtenResenasCalificacion (String idproducto, String [] porcentaje) {
        List <Resena> todasResenasProducto = resenaRepositorio.findResenasByIdproducto (Integer.parseInt (idproducto));
        if (todasResenasProducto.isEmpty ()) return null;
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

    @ Override
    public int estadoResena (String idusuario, String idproducto) {
        Integer usuario = Integer.parseInt (idusuario);
        Integer producto = Integer.parseInt (idproducto);
        Resena resena = resenaRepositorio.findResenaByIdusuarioAndIdproducto (usuario, producto);
        if (resena == null) {
            List <Compra> compras = compraRepositorio.buscaComprasDeUsuarioProducto (usuario, producto);
            return compras.isEmpty () ? 0 : 1;
        } else return 2;
    }

    @ Override
    public void publicaResena (String idusuario, String idproducto, String comentario, String calificacion) {
        if (estadoResena (idusuario, idproducto) != 1) throw new ForbiddenException ();
        if (comentario == null || comentario.length () > 256) throw new ForbiddenException ();
        Integer calfInt = Integer.parseInt (calificacion);
        if (calfInt < 1 || 5 < calfInt) throw new ForbiddenException ();
        Resena resena = new Resena ();
        Integer usuario = Integer.parseInt (idusuario);
        Integer producto = Integer.parseInt (idproducto);
        resena.setIdusuario (usuario);
        resena.setIdproducto (producto);
        resena.setCalificacion (calfInt);
        resena.setComentario (comentario);
        resena.setUsuario (usuarioRepositorio.findUsuarioByIdusuario (usuario));
        resena.setProducto (productoRepositorio.findProductoByIdproducto (producto));
        resenaRepositorio.save (resena);
    }

}
