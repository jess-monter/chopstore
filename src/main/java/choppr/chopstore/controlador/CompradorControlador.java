package choppr.chopstore.controlador;

import choppr.chopstore.datos.ProductoDatos;
import choppr.chopstore.servicio.ProductoServicio;
import choppr.chopstore.datos.ResenaDatos;
import choppr.chopstore.servicio.ResenaServicio;
import choppr.chopstore.servicio.impl.UsuarioServicio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;

/**
 * Clase que atiende las peticiones de un comprador al servidor
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.1
 */

@ Controller
public class CompradorControlador {

    @ Autowired
    private ProductoServicio productoServicio;

    @ Autowired
    private ResenaServicio resenaServicio;

    @ Autowired
    private UsuarioServicio usuarioServicio;

    /**
     * Atiende una petición de la página de inicio cuando se ha iniciado sesión como comprador
     * @param autentificacion es el token de autentificación del usuario
     * @param modelo es el contenedor con la información que se envía a la página
     * @return la página de inicio de comprador
     */

    @ RequestMapping ("/comprador")
    @ Secured ("ROLE_COMPRADOR")
    public String comprador (Authentication autentificacion, Model modelo) {
        String idusuario = usuarioServicio.obtenIdusuario (autentificacion);
        modelo.addAttribute ("recomendaciones", productoServicio.recomiendaProductos (idusuario));
        modelo.addAttribute ("masvendidos", productoServicio.obtenProductosMasVendidos ());
        return "comprador";
    }

    /**
     * Atiende una petición de búsuqeda
     * @param peticion es el contenedor con los parámetros de la petición
     * @param modelo es el contenedor con la información que se envía a la página
     * @return la página de búsqueda
     */

    @ PostMapping ("/busqueda")
    @ Secured ("ROLE_COMPRADOR")
    public String busqueda (HttpServletRequest peticion, Model modelo) {
        String busqueda = peticion.getParameter ("busqueda");
        String categoria = peticion.getParameter ("categoria");
        ProductoDatos [] [] productos = productoServicio.buscaProductos (busqueda, categoria);
        modelo.addAttribute ("busqueda", busqueda);
        modelo.addAttribute ("categoria", categoria);
        modelo.addAttribute ("productos", productos);
        return "busqueda";
    }

    /**
     * Atiende una petición de consultar producto
     * @param peticion es el contenedor con los parámetros de la petición
     * @param autentificacion es el token de autentificación del usuario
     * @param modelo es el contenedor con la información que se envía a la página
     * @return la página de producto
     */

    @ GetMapping ("/producto")
    @ Secured ("ROLE_COMPRADOR")
    public String producto (HttpServletRequest peticion, Authentication autentificacion, Model modelo) {
        String idusuario = usuarioServicio.obtenIdusuario (autentificacion);
        String idproducto = peticion.getParameter ("idproducto");
        ProductoDatos producto = productoServicio.consultaPorId (idproducto);
        String [] porcentaje = new String [1];
        ResenaDatos [] resenas = resenaServicio.obtenResenasCalificacion (idproducto, porcentaje);
        int estado = resenaServicio.estadoResena (idusuario, idproducto);
        modelo.addAttribute ("producto", producto);
        modelo.addAttribute ("resenas", resenas);
        modelo.addAttribute ("porcentaje", porcentaje [0]);
        modelo.addAttribute ("estado", estado);
        return "producto";
    }

    /**
     * Atiende una petición de publicar reseña
     * @param peticion es el contenedor con los parámetros de la petición
     * @param autentificacion es el token de autentificación del usuario
     * @param modelo es el contenedor con la información que se envía a la página
     * @return la página de producto
     */

    @ PostMapping ("/resena")
    @ Secured ("ROLE_COMPRADOR")
    public String resena (HttpServletRequest peticion, Authentication autentificacion, Model modelo) {
        String idusuario = usuarioServicio.obtenIdusuario (autentificacion);
        String idproducto = peticion.getParameter ("idproducto");
        String comentario = peticion.getParameter ("comentario");
        String calificacion = peticion.getParameter ("calificacion");
        resenaServicio.publicaResena (idusuario, idproducto, comentario, calificacion);
        return "redirect:/producto?idproducto=" + idproducto;
    }

    /**
     * Atiende una petición de publicar reseña
     * @param peticion es el contenedor con los parámetros de la petición
     * @param autentificacion es el token de autentificación del usuario
     * @param modelo es el contenedor con la información que se envía a la página
     * @return la página de producto
     */

    @ GetMapping ("/carrito")
    @ Secured ("ROLE_COMPRADOR")
    public String carrito (HttpServletRequest peticion, Authentication autentificacion, Model modelo) {
        String idusuario = usuarioServicio.obtenIdusuario (autentificacion);
        System.out.println(idusuario);
        return "carrito";
    }

}