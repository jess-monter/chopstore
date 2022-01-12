package choppr.chopstore.servicio.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import choppr.chopstore.modelo.Compra;
import choppr.chopstore.modelo.Usuario;
import choppr.chopstore.repositorio.CompraRepositorio;
import choppr.chopstore.servicio.CompraServicio;
import choppr.chopstore.datos.CompraDatos;
import choppr.chopstore.repositorio.UsuarioRepositorio;

import java.time.LocalDate;

@Service
public class CompraServicioImpl implements CompraServicio {


    @ Autowired
    private CompraRepositorio compraRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    /**
     * Crea una nueva reseña de un usuario para un producto
     * @param idusuario es el identificador del usuario
     * @param idproducto es el identificador del producto
     * @param comentario es el contenido de la reseña
     * @param calificacion es la calificación de la reseña
     * @throws ForbiddenException si el usuario no ha comprado el producto o el usuario ya ha reseñado el producto
     * @throws InvalidValueException si el comentario es de longitud mayor al máximo permitido o si la calificación no está entre 1 y 5
     */

    @ Override
    public CompraDatos hazCompra (String idusuario, Double pago, LocalDate fecha) {
        Compra compra = new Compra();
        Usuario usuario = usuarioRepositorio.findUsuarioByIdusuario (Integer.parseInt (idusuario));
        compra.setIdusuario(usuario.getIdusuario ());
        compra.setPago(pago);
        compra.setFecha(fecha);
        compra.setUsuario(usuario);

        compraRepositorio.save(compra);

        return new CompraDatos(compra);
    }
    
}
