package choppr.chopstore.controlador;

import choppr.chopstore.datos.ProductoDatos;
import choppr.chopstore.servicio.ProductoServicio;
import choppr.chopstore.datos.ResenaDatos;
import choppr.chopstore.servicio.ResenaServicio;
import choppr.chopstore.servicio.impl.UsuarioServicio;
import choppr.chopstore.datos.CompraDatos;
import choppr.chopstore.servicio.CompraServicio;
import choppr.chopstore.datos.InvolucrarDatos;
import choppr.chopstore.servicio.InvolucrarServicio;

import choppr.chopstore.servicio.impl.CorreoServicio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.json.*;


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

    @Autowired
    private CompraServicio compraServicio;

    @Autowired
    private InvolucrarServicio involucrarServicio;

    @Autowired
    private CorreoServicio correoServicio;

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
     * Atiende una petición de revisar pedido
     * @param peticion es el contenedor con los parámetros de la petición
     * @param autentificacion es el token de autentificación del usuario
     * @param modelo es el contenedor con la información que se envía a la página
     * @return la página de producto
     */
    @ PostMapping ("/pedido")
    @ Secured ("ROLE_COMPRADOR")
    public String pedido (HttpServletRequest peticion, Authentication autentificacion, Model modelo) {
        return "pedido";
    }

    /**
     * Atiende una petición de realización de compra
     * @param peticion es el contenedor con los parámetros de la petición
     * @param autentificacion es el token de autentificación del usuario
     * @param modelo es el contenedor con la información que se envía a la página
     * @return la página de producto
     */
    @ RequestMapping (value="/comprar", method=RequestMethod.POST)
    @ Secured ("ROLE_COMPRADOR")
    public String comprar (HttpServletRequest peticion, Authentication autentificacion, Model modelo) {
        String idusuario = usuarioServicio.obtenIdusuario (autentificacion);
        String correo = usuarioServicio.obtenCorreousuario(autentificacion);
        String carrito = peticion.getParameter("cart");
        JSONArray carrito_p = new JSONArray(carrito);
        LocalDate fecha = LocalDate.now();
        CompraDatos compra = compraServicio.hazCompra(idusuario, 7000.00, fecha);
        for(int i=0; i<carrito_p.length(); i++) {
            Integer cantidad = carrito_p.getJSONObject(i).getInt("count");
            Integer productoId = carrito_p.getJSONObject(i).getInt("product_id");
            InvolucrarDatos involucrar = involucrarServicio.agregaProductosCompra(compra.idcompra, productoId, cantidad);
        }
        correoServicio.enviaConfirmacionCompra(correo, "Gracias por tu compra");

        return "thankyou";
    }

    /**
     * Atiende una petición de agradecimiento
     * @param peticion es el contenedor con los parámetros de la petición
     * @param autentificacion es el token de autentificación del usuario
     * @param modelo es el contenedor con la información que se envía a la página
     * @return la página de producto
     */
    @ PostMapping ("/thankyou")
    @ Secured ("ROLE_COMPRADOR")
    public String thankyou (HttpServletRequest peticion, Authentication autentificacion, Model modelo) {
        return "thankyou";
    }
}