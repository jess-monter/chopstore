package choppr.chopstore.controlador;

import choppr.chopstore.datos.ProductoDatos;
import choppr.chopstore.servicio.ProductoServicio;
import choppr.chopstore.datos.ResenaDatos;
import choppr.chopstore.servicio.ResenaServicio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Clase que atiende las peticiones al servidor
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.4
 */

@ Controller
public class ChopstoreControlador {

    @ Autowired
    private ProductoServicio productoServicio;

    @ Autowired
    private ResenaServicio resenaServicio;

    /**
     * Atiende una petición de la página de inicio cuando no se ha iniciado sesión
     * @return la página de inicio
     */

    @ RequestMapping ("/")
    public String inicio () {
        return "inicio";
    }

    /**
     * Atiende una petición de la página de inicio cuando se ha iniciado sesión como vendedor
     * @param principal es el usuario autentificado
     * @param modelo es el contenedor con la información que se envía a la página
     * @return la página de inicio de vendedor
     */

    @ RequestMapping ("/vendedor")
    public String vendedor (Principal principal, Model modelo) {
        //Requiere que estén implementado el sistema de inicio/cierre de sesión
        //modelo.addAttribute ("productos", productoServicio.obtenProductosDeUsuario (principal.getName ()));
        modelo.addAttribute ("productos", productoServicio.obtenProductosDeUsuario ("2"));
        return "vendedor";
    }

    /**
     * Atiende una petición de la página de inicio cuando se ha iniciado sesión como comprador
     * @param principal es el usuario autentificado
     * @param modelo es el contenedor con la información que se envía a la página
     * @return la página de inicio de comprador
     */

    @ RequestMapping ("/comprador")
    public String comprador (Principal principal, Model modelo) {
        //Requiere que estén implementado el sistema de inicio/cierre de sesión
        //modelo.addAttribute ("recomendaciones", productoServicio.recomiendaProductos (principal.getName ()));
        modelo.addAttribute ("recomendaciones", productoServicio.recomiendaProductos ("1"));
        modelo.addAttribute ("masvendidos", productoServicio.obtenProductosMasVendidos ());
        return "comprador";
    }

    /**
     * Atiende una petición de búsuqeda
     * @param petición es el contenedor con los parámetros de la petición
     * @param modelo es el contenedor con la información que se envía a la página
     * @return la página de búsqueda
     */

    @ PostMapping ("/busqueda")
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
     * @param petición es el contenedor con los parámetros de la petición
     * @param principal es el usuario autentificado
     * @param modelo es el contenedor con la información que se envía a la página
     * @return la página de producto
     */

    @ GetMapping ("/producto")
    public String producto (HttpServletRequest peticion, Principal principal, Model modelo) {
        //Requiere que estén implementado el sistema de inicio/cierre de sesión
        //String idusuario = principal.getName ();
        String idusuario = "1";
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
     * Atiende una petición de la página de crear productos
     * @return la página de crear productos
     */

    @ GetMapping ("/crear")
    public String crear () {
        return "crear";
    }

    /**
     * Atiende una petición de publicar un producto
     * @return la página de inicio de vendedor
     */

    @ PostMapping ("/publica")
    public String publica (HttpServletRequest peticion, Principal principal) {
        //Requiere que estén implementado el sistema de inicio/cierre de sesión
        //String idusuario = principal.getName ();
        String idusuario = "2";
        String categoria = peticion.getParameter ("categoria");
        String nombre = peticion.getParameter ("nombre");
        String descripcion = peticion.getParameter ("descripcion");
        String precio = peticion.getParameter ("precio");
        String imagen = peticion.getParameter ("imagen");
        String cantidad = peticion.getParameter ("cantidad");
        String detalles = peticion.getParameter ("detalles");
        productoServicio.publicaProducto (idusuario, categoria, nombre, descripcion, precio, imagen, cantidad, detalles);
        return "redirect:/vendedor";
    }

    /**
     * Atiende una petición de la página de actualizar productos
     * @param petición es el contenedor con los parámetros de la petición
     * @param principal es el usuario autentificado
     * @param modelo es el contenedor con la información que se envía a la página
     * @return la página de actualizar productos
     */

    @ PostMapping ("/actualizar")
    public String actualizar (HttpServletRequest peticion, Principal principal, Model modelo) {
        //Requiere que estén implementado el sistema de inicio/cierre de sesión
        //String idusuario = principal.getName ();
        String idusuario = "2";
        String idproducto = peticion.getParameter ("idproducto");
        ProductoDatos producto = productoServicio.consultaPorIdVerificaUsuario (idproducto, idusuario);
        modelo.addAttribute ("producto", producto);
        return "actualizar";
    }

    /**
     * Atiende una petición de editar un producto
     * @return la página de inicio de vendedor
     */

    @ PostMapping ("/edita")
    public String pedita (HttpServletRequest peticion, Principal principal) {
        //Requiere que estén implementado el sistema de inicio/cierre de sesión
        //String idusuario = principal.getName ();
        String idusuario = "2";
        String idproducto = peticion.getParameter ("idproducto");
        String categoria = peticion.getParameter ("categoria");
        String nombre = peticion.getParameter ("nombre");
        String descripcion = peticion.getParameter ("descripcion");
        String precio = peticion.getParameter ("precio");
        String imagen = peticion.getParameter ("imagen");
        String cantidad = peticion.getParameter ("cantidad");
        String detalles = peticion.getParameter ("detalles");
        productoServicio.editaProducto (idproducto, idusuario, categoria, nombre, descripcion, precio, imagen, cantidad, detalles);
        return "redirect:/vendedor";
    }

    /**
     * Atiende una petición de eliminar un producto
     * @param petición es el contenedor con los parámetros de la petición
     * @param principal es el usuario autentificado
     * @return la página de inicio de vendedor
     */

    @ PostMapping ("/elimina")
    public String elimina (HttpServletRequest peticion, Principal principal) {
        //Requiere que estén implementado el sistema de inicio/cierre de sesión
        //String idusuario = principal.getName ();
        String idusuario = "2";
        String idproducto = peticion.getParameter ("idproducto");
        productoServicio.eliminaProducto (idusuario, idproducto);
        return "redirect:/vendedor";
    }

    /**
     * Atiende una petición de publicar reseña
     * @param petición es el contenedor con los parámetros de la petición
     * @param principal es el usuario autentificado
     * @param modelo es el contenedor con la información que se envía a la página
     * @return la página de producto
     */

    @ PostMapping ("/resena")
    public String resena (HttpServletRequest peticion, Principal principal, Model modelo) {
        //Requiere que estén implementado el sistema de inicio/cierre de sesión
        //String idusuario = principal.getName ();
        String idusuario = "1";
        String idproducto = peticion.getParameter ("idproducto");
        String comentario = peticion.getParameter ("comentario");
        String calificacion = peticion.getParameter ("calificacion");
        resenaServicio.publicaResena (idusuario, idproducto, comentario, calificacion);
        return "redirect:/producto?idproducto=" + idproducto;
    }
    
}