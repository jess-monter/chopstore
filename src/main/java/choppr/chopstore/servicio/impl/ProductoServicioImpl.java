package choppr.chopstore.servicio.impl;

import choppr.chopstore.modelo.Categoria;
import choppr.chopstore.modelo.Producto;
import choppr.chopstore.modelo.Usuario;
import choppr.chopstore.repositorio.ProductoRepositorio;
import choppr.chopstore.repositorio.UsuarioRepositorio;
import choppr.chopstore.repositorio.InvolucrarRepositorio;
import choppr.chopstore.repositorio.CategoriaRepositorio;
import choppr.chopstore.datos.ProductoDatos;
import choppr.chopstore.servicio.ProductoServicio;
import choppr.chopstore.excepciones.ElementNotFoundException;
import choppr.chopstore.excepciones.ForbiddenException;

import java.lang.Math;
import java.util.Random;
import java.util.Iterator;
import java.util.List;
import java.util.HashSet;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Clase que implementa la interfaz ProductoServicio
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.3
 */

@ Service
public class ProductoServicioImpl implements ProductoServicio {

    static final int MAX_STRING_SIZE = 256;
    
    @ Autowired
    private ProductoRepositorio productoRepositorio;

    @ Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @ Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @ Autowired
    private InvolucrarRepositorio involucrarRepositorio;

    /**
     * Regresa los productos que coninciden con los parámetros de búsqueda especificados
     * @param busqueda es una cadena con palabras clave para hacer buscar coincidencias con el nombre de los productos
     * @param categoria es la categoría donde se van a buscar los productos
     * @return un arreglo con los productos encontrados, organizados por grupos de 4 productos, o null si no se encontraron productos
     */

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
        if (coincidencias.isEmpty ()) return null;
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

    /**
     * Regresa los productos del usuario especificado
     * @param idusario es el identificador del usuario
     * @return un arreglo con todos los productos del usuario o null si el usuario no tiene productos
     */

    @ Override
    public ProductoDatos [] obtenProductosDeUsuario (String idusuario) {
        List <Producto> productos = productoRepositorio.findProductosByIdusuario (Integer.parseInt (idusuario));
        if (productos.isEmpty ()) return null;
        ProductoDatos [] productosUsuario = new ProductoDatos [productos.size ()];
        int indice = 0;
        for (Producto producto : productos) {
            productosUsuario [indice] = new ProductoDatos (producto);
            indice ++;
        }
        return productosUsuario;
    }

    /**
     * Regresa el producto especificado
     * @param idproducto es el identificador del producto
     * @return el producto con el identificador especificado
     * @throws ElementNotFoundException si no exíste el producto
     */

    @ Override
    public ProductoDatos consultaPorId (String idproducto) {
        Producto coincidencia = productoRepositorio.findProductoByIdproducto (Integer.parseInt (idproducto));
        if (coincidencia == null) throw new ElementNotFoundException ();
        return new ProductoDatos (coincidencia);
    }

    /**
     * Recupera un producto especificado y verifica que el usuario sea dueño del mismo
     * @param idproducto es el identificador del producto
     * @param idusuario es el identificador del usuario que debe ser dueño del producto
     * @return el producto con el identificador especificado
     * @throws ElementNotFoundException si no exíste el producto
     * @throws ForbiddenException si el usuario no es dueño del producto
     */

    @ Override
    public ProductoDatos consultaPorIdVerificaUsuario (String idproducto, String idusuario) {
        Producto coincidencia = productoRepositorio.findProductoByIdproducto (Integer.parseInt (idproducto));
        if (coincidencia == null) throw new ElementNotFoundException ();
        if (coincidencia.getIdusuario () != Integer.parseInt (idusuario)) throw new ForbiddenException ();
        return new ProductoDatos (coincidencia);
    }

    /**
     * Regresa los productos recomendados para el usuario especificado
     * @param idusuario es el identificador del usuario
     * @return un arreglo de 4 productos recomendados
     */

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
            while (indice <= inicioAleatorio) {
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

    /**
     * Regresa los productos más vendidos
     * @return un arreglo de los 4 productos más vendidos
     */

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

    /**
     * Crea un nuevo producto para un usuario
     * @param idusuario es el identificador del usuario
     * @param nombreCategoria es el nombre de la categoría del producto
     * @param nombre es el nombre del producto
     * @param descripcion es la descripción del producto
     * @param precio es el precio del producto
     * @param imagen es la imagen del producto
     * @param cantidad es la cantidad del producto
     * @param detalles son los detalles del producto
     * @throws ElementNotFoundException si no existe la categoría
     * @throws ForbiddenException si el nombre, imagen, descripción o detalles son de longitud mayor al máximo permitido o si el precio o cantidad son negativos
     */
    
     @ Override
    public void publicaProducto (String idusuario, String nombreCategoria, String nombre, String descripcion, String precio, String imagen, String cantidad, String detalles) {
        if (nombre.length () > MAX_STRING_SIZE || imagen.length () > MAX_STRING_SIZE || descripcion.length () > MAX_STRING_SIZE || detalles.length () > MAX_STRING_SIZE) throw new ForbiddenException ();
        Double precDbl = (double) Math.round (100 * Double.parseDouble (precio)) / 100;
        Integer cantInt = Integer.parseInt (cantidad);
        if (precDbl < 0 || cantInt < 0) throw new ForbiddenException ();
        Producto producto = new Producto ();
        Usuario usuario = usuarioRepositorio.findUsuarioByIdusuario (Integer.parseInt (idusuario));
        Categoria categoria = categoriaRepositorio.findCategoriaByNombre (nombreCategoria);
        if (categoria == null) throw new ElementNotFoundException ();
        producto.setIdusuario (usuario.getIdusuario ());
        producto.setIdcategoria (categoria.getIdcategoria ());
        producto.setNombre (nombre);
        producto.setDescripcion (descripcion);
        producto.setPrecio (precDbl);
        producto.setImagen (imagen);
        producto.setCantidad (cantInt);
        producto.setDetalles (detalles);
        producto.setUsuario (usuario);
        producto.setCategoria (categoria);
        productoRepositorio.save (producto);
    }

    /**
     * Edita la información de un producto existente de un usuario
     * @param idproducto es el identificador del producto
     * @param idusuario es el identificador del usuario
     * @param nombreCategoria es el nombre de la categoría del producto
     * @param nombre es el nombre del producto
     * @param descripcion es la descripción del producto
     * @param precio es el precio del producto
     * @param imagen es la imagen del producto
     * @param cantidad es la cantidad del producto
     * @param detalles son los detalles del producto
     * @throws ElementNotFoundException si no existe el producto o la categoría
     * @throws ForbiddenException si el usuario no es dueño del producto, si el nombre, imagen, descripción o detalles son de longitud mayor al máximo permitido o si el precio o cantidad son negativos
     */
    
    @ Override
    public void editaProducto (String idproducto, String idusuario, String nombreCategoria, String nombre, String descripcion, String precio, String imagen, String cantidad, String detalles) {
        if (nombre.length () > MAX_STRING_SIZE || imagen.length () > MAX_STRING_SIZE || descripcion.length () > MAX_STRING_SIZE || detalles.length () > MAX_STRING_SIZE) throw new ForbiddenException ();
        Double precDbl = (double) Math.round (100 * Double.parseDouble (precio)) / 100;
        Integer cantInt = Integer.parseInt (cantidad);
        if (precDbl < 0 || cantInt < 0) throw new ForbiddenException ();
        Producto producto = productoRepositorio.findProductoByIdproducto (Integer.parseInt (idproducto));
        if (producto == null) throw new ElementNotFoundException ();
        if (producto.getIdusuario () != Integer.parseInt (idusuario)) throw new ForbiddenException ();
        Categoria categoria = categoriaRepositorio.findCategoriaByNombre (nombreCategoria);
        if (categoria == null) throw new ElementNotFoundException ();
        producto.setIdcategoria (categoria.getIdcategoria ());
        producto.setNombre (nombre);
        producto.setDescripcion (descripcion);
        producto.setPrecio (precDbl);
        producto.setImagen (imagen);
        producto.setCantidad (cantInt);
        producto.setDetalles (detalles);
        producto.setCategoria (categoria);
        productoRepositorio.save (producto);
    }

    /**
     * Elimina el producto especificado
     * @param idusuario es el identificador del usuario que desea eliminar el producto
     * @param idproducto es el identificador del producto que se desea eliminar
     * @throws ElementNotFoundException si no exíste el producto
     * @throws ForbiddenException si el usuario no es dueño del producto
     */
    
    @ Override
    public void eliminaProducto (String idusuario, String idproducto) {
        Producto producto = productoRepositorio.findProductoByIdproducto (Integer.parseInt (idproducto));
        if (producto == null) throw new ElementNotFoundException ();
        if (producto.getIdusuario () != Integer.parseInt (idusuario)) throw new ForbiddenException ();
        productoRepositorio.delete (producto);
    }

}
