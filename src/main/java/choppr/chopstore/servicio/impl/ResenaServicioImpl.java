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

/**
 * Clase que implementa la interfaz ResenaServicio
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

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

    /**
     * Regresa las reseñas del producto especificado y guarda el valor de la calificación promedio, como un porcentaje
     * @param idproducto es el identificador del producto
     * @param porcentaje es un arreglo de tamaño 1 donde se guarda como cadena el porcentaje de la calificación promedio
     * @return un arreglo con las reseñas del producto
     */

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

    /**
     * Regresa el código del estado actual de la reseña para el usuario y producto especificados
     * @param idusuario es el identificador del usuario
     * @param idproducto es el identificador del producto
     * @return 0 si el usuario no ha comprado el producto, 1 si el usuario ha comprado el producto pero no lo ha reseñado y 2 si el usuario ha comprado el producto y lo ha reseñado
     */

    @ Override
    public int estadoResena (String idusuario, String idproducto) {
        Integer usuario = Integer.parseInt (idusuario);
        Integer producto = Integer.parseInt (idproducto);
        Resena resena = resenaRepositorio.findResenaByIdusuarioAndIdproducto (usuario, producto);
        if (resena == null) {
            List <Compra> compras = compraRepositorio.buscaComprasDeUsuarioProducto (usuario, producto);
            return compras.isEmpty () ? 0 : 1;
        }
        return 2;
    }

    /**
     * Crea una nueva reseña de un usuario para un producto
     * @param idusuario es el identificador del usuario
     * @param idproducto es el identificador del producto
     * @param comentario es el contenido de la reseña
     * @param calificacion es la calificación de la reseña
     * @throws ForbiddenException si el usuario no ha comprado el producto, el usuario ya ha reseñado el producto, el comentario es null, el comentario es de longitud mayor a 256 o si la calificación no está entre 1 y 5
     */

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
