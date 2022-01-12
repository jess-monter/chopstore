package choppr.chopstore.servicio.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import choppr.chopstore.modelo.Compra;
import choppr.chopstore.repositorio.CompraRepositorio;
import choppr.chopstore.servicio.CompraServicio;
import choppr.chopstore.datos.CompraDatos;

import choppr.chopstore.repositorio.ProductoRepositorio;
import choppr.chopstore.modelo.Producto;

import choppr.chopstore.modelo.Involucrar;
import choppr.chopstore.servicio.InvolucrarServicio;
import choppr.chopstore.repositorio.InvolucrarRepositorio;
import choppr.chopstore.datos.InvolucrarDatos;


@Service
public class InvolucrarServicioImpl implements InvolucrarServicio {


    @ Autowired
    private CompraRepositorio compraRepositorio;

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private InvolucrarRepositorio involucrarRepositorio;

    /**
     * Crea una nueva reseña de un usuario para un producto
     * @param idcompra es el identificador de la compra
     * @param idproducto es el identificador del producto
     * @param cantidad es la cantidad a comprar
     * @throws InvalidValueException si el comentario es de longitud mayor al máximo permitido o si la calificación no está entre 1 y 5
     */

    @ Override
    public InvolucrarDatos agregaProductosCompra (Integer idcompra, Integer idproducto, Integer cantidad) {
        Involucrar involucrar = new Involucrar();
        Compra compra = compraRepositorio.findCompraByIdcompra(idcompra);
        Producto producto = productoRepositorio.findProductoByIdproducto(idproducto);
        involucrar.setIdcompra(compra.getIdcompra());
        involucrar.setIdproducto(producto.getIdproducto());
        involucrar.setProducto(producto);
        involucrar.setCompra(compra);
        involucrar.setCantidad(cantidad);

        involucrarRepositorio.save(involucrar);

        producto.setCantidad(producto.getCantidad() - cantidad);

        productoRepositorio.save(producto);


        return new InvolucrarDatos(involucrar);
    }
    
}

