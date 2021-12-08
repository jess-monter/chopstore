package choppr.chopstore.controlador;

import choppr.chopstore.datos.ProductoDatos;
import choppr.chopstore.servicio.ProductoServicio;
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
 * Clase que atiende las peticiones de un vendedor al servidor
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.1
 */

@ Controller
public class VendedorControlador {

    @ Autowired
    private ProductoServicio productoServicio;

    @ Autowired
    private UsuarioServicio usuarioServicio;

    /**
     * Atiende una petición de la página de inicio cuando se ha iniciado sesión como vendedor
     * @param autentificacion es el token de autentificación del usuario
     * @param modelo es el contenedor con la información que se envía a la página
     * @return la página de inicio de vendedor
     */

    @ RequestMapping ("/vendedor")
    @ Secured ("ROLE_VENDEDOR")
    public String vendedor (Authentication autentificacion, Model modelo) {
        String idusuario = usuarioServicio.obtenIdusuario (autentificacion);
        modelo.addAttribute ("productos", productoServicio.obtenProductosDeUsuario (idusuario));
        return "vendedor";
    }

    /**
     * Atiende una petición de la página de crear productos
     * @return la página de crear productos
     */

    @ GetMapping ("/crear")
    @ Secured ("ROLE_VENDEDOR")
    public String crear () {
        return "crear";
    }

    /**
     * Atiende una petición de publicar un producto
     * @param peticion es el contenedor con los parámetros de la petición
     * @param autentificacion es el token de autentificación del usuario
     * @return la página de inicio de vendedor
     */

    @ PostMapping ("/publica")
    @ Secured ("ROLE_VENDEDOR")
    public String publica (HttpServletRequest peticion, Authentication autentificacion) {
        String idusuario = usuarioServicio.obtenIdusuario (autentificacion);
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
     * @param peticion es el contenedor con los parámetros de la petición
     * @param autentificacion es el token de autentificación del usuario
     * @param modelo es el contenedor con la información que se envía a la página
     * @return la página de actualizar productos
     */

    @ PostMapping ("/actualizar")
    @ Secured ("ROLE_VENDEDOR")
    public String actualizar (HttpServletRequest peticion, Authentication autentificacion, Model modelo) {
        String idusuario = usuarioServicio.obtenIdusuario (autentificacion);
        String idproducto = peticion.getParameter ("idproducto");
        ProductoDatos producto = productoServicio.consultaPorIdVerificaUsuario (idproducto, idusuario);
        modelo.addAttribute ("producto", producto);
        return "actualizar";
    }

    /**
     * Atiende una petición de editar un producto
     * @param peticion es el contenedor con los parámetros de la petición
     * @param autentificacion es el token de autentificación del usuario
     * @return la página de inicio de vendedor
     */

    @ PostMapping ("/edita")
    @ Secured ("ROLE_VENDEDOR")
    public String pedita (HttpServletRequest peticion, Authentication autentificacion) {
        String idusuario = usuarioServicio.obtenIdusuario (autentificacion);
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
     * @param peticion es el contenedor con los parámetros de la petición
     * @param autentificacion es el token de autentificación del usuario
     * @return la página de inicio de vendedor
     */

    @ PostMapping ("/elimina")
    @ Secured ("ROLE_VENDEDOR")
    public String elimina (HttpServletRequest peticion, Authentication autentificacion) {
        String idusuario = usuarioServicio.obtenIdusuario (autentificacion);
        String idproducto = peticion.getParameter ("idproducto");
        productoServicio.eliminaProducto (idusuario, idproducto);
        return "redirect:/vendedor";
    }

}