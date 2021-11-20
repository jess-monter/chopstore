package choppr.chopstore.servicio.impl;

import choppr.chopstore.modelo.Producto;
import choppr.chopstore.repositorio.ProductoRepositorio;
import choppr.chopstore.servicio.ProductoServicio;
import choppr.chopstore.datos.ProductoDatos;
import choppr.chopstore.repositorio.InvolucrarRepositorio;
import choppr.chopstore.excepciones.ElementNotFoundException;
import choppr.chopstore.excepciones.ForbiddenException;

import java.util.Random;
import java.util.Iterator;
import java.util.List;
import java.util.HashSet;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@ Service
public class ProductoServicioImpl implements ProductoServicio {
    
    @ Autowired
    private ProductoRepositorio productoRepositorio;

    @ Autowired
    private InvolucrarRepositorio involucrarRepositorio;

    @ Override
    public ProductoDatos [] [] buscaProductos (String busqueda, String categoria) {
        HashSet <Producto> coincidencias = new HashSet <> ();
        String [] palabrasClave = busqueda.split (" ");
        if (categoria.equals ("Todas las categorías")) {
            if (busqueda.equals ("")) coincidencias.addAll (productoRepositorio.findAll ());
            else for (String clave : palabrasClave) if (! clave.equals ("")) coincidencias.addAll (productoRepositorio.buscaTodasCoincidencias ("%" + clave + "%"));
        } else {
            if (busqueda.equals ("")) coincidencias.addAll (productoRepositorio.buscaTodasCategorias (categoria));
            else for (String clave : palabrasClave) if (! clave.equals ("")) coincidencias.addAll (productoRepositorio.buscaCoincidenciasPorCategoria ("%" + clave + "%", categoria));
        }
        if (coincidencias.size () == 0) return null;
        ProductoDatos [] [] productosAgrupados = new ProductoDatos [(coincidencias.size () + 3) >> 2] [4];
        int i = 0;
        int j = 0;
        for (Producto producto : coincidencias) {
            if (j == 4) {
                i += 1;
                j = 0;
            }
            productosAgrupados [i] [j] = new ProductoDatos (producto);
            j += 1;
        }
        return productosAgrupados;
    }

    @ Override
    public ProductoDatos [] obtenProductosDeUsuario (String idusuario) {
        List <Producto> productos = productoRepositorio.findProductosByIdusuario (Integer.parseInt (idusuario));
        if (productos.size () == 0) return null;
        ProductoDatos [] productosUsuario = new ProductoDatos [productos.size ()];
        int indice = 0;
        for (Producto producto : productos) {
            productosUsuario [indice] = new ProductoDatos (producto);
            indice ++;
        }
        return productosUsuario;
    }

    @ Override
    public ProductoDatos consultaPorId (String idproducto) {
        Producto coincidencia = productoRepositorio.findProductoByIdproducto (Integer.parseInt (idproducto));
        if (coincidencia == null) throw new ElementNotFoundException ();
        ProductoDatos producto = new ProductoDatos (coincidencia);
        return producto;
    }

    @ Override
    public ProductoDatos [] recomiendaProductos (String idusuario) {
        // Lo ideal sería usar algún método de machine learning para recomendaciones
        // Por simplicidad, aquí las recomendaciones se eligen aleatoriamente
        Random rng = new Random ();
        List <Producto> todosProductos = productoRepositorio.findAll ();
        ProductoDatos [] recomendaciones = new ProductoDatos [4];
        int indice = 0;
        if (todosProductos.size () <= 4) {
            for (Producto producto : todosProductos) {
                recomendaciones [indice] = new ProductoDatos (producto);
                indice ++;
            }
        } else {
            Iterator <Producto> iterador = todosProductos.iterator ();
            int inicioAleatorio = rng.nextInt (todosProductos.size () - 4);
            while (indice < inicioAleatorio) {
                iterador.next ();
                indice ++;
            }
            indice = 0;
            while (indice < 4) {
                recomendaciones [indice] = new ProductoDatos (iterador.next ());
                indice ++;
            }
        }
        return recomendaciones;
    }

    @ Override
    public ProductoDatos [] obtenProductosMasVendidos () {
        Producto producto;
        ProductoDatos [] masVendidos = new ProductoDatos [4];
        List <Object []> ventas = involucrarRepositorio.consultaMayoresVentas ();
        int indice = 0;
        for (Object [] arreglo : ventas) {
            producto = productoRepositorio.findProductoByIdproducto ((Integer) arreglo [0]);
            masVendidos [indice] = new ProductoDatos (producto);
            indice ++;
        }
        return masVendidos;
    }

    @ Override
    public void eliminaProducto (String idusuario, String idproducto) {
        Producto producto = productoRepositorio.findProductoByIdproducto (Integer.parseInt (idproducto));
        if (producto == null) throw new ElementNotFoundException ();
        if (producto.getIdusuario () != Integer.parseInt (idusuario)) throw new ForbiddenException ();
        productoRepositorio.delete (producto);
    }

}
