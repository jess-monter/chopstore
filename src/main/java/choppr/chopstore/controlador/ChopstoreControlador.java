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

@ Controller
public class ChopstoreControlador {

    @ Autowired
    private ProductoServicio productoServicio;

    @ Autowired
    private ResenaServicio resenaServicio;

    @ RequestMapping ("/")
    public String inicio () {
        return "inicio";
    }

    @ RequestMapping ("/vendedor")
    public String vendedor (Principal principal, Model modelo) {
        //Requiere que estén implementado el sistema de inicio/cierre de sesión
        //modelo.addAttribute ("productos", productoServicio.obtenProductosDeUsuario (principal.getName ()));
        modelo.addAttribute ("productos", productoServicio.obtenProductosDeUsuario ("2"));
        return "vendedor";
    }

    @ RequestMapping ("/comprador")
    public String comprador (Principal principal, Model modelo) {
        //Requiere que estén implementado el sistema de inicio/cierre de sesión
        //modelo.addAttribute ("recomendaciones", productoServicio.recomiendaProductos (principal.getName ()));
        modelo.addAttribute ("recomendaciones", productoServicio.recomiendaProductos ("1"));
        modelo.addAttribute ("masvendidos", productoServicio.obtenProductosMasVendidos ());
        return "comprador";
    }

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

    @ GetMapping ("/producto")
    public String producto (HttpServletRequest peticion, Model modelo, Principal principal) {
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

    @ PostMapping ("/elimina")
    public String elimina (HttpServletRequest peticion, Principal principal) {
        //Requiere que estén implementado el sistema de inicio/cierre de sesión
        //String idusuario = principal.getName ();
        String idusuario = "2";
        String idproducto = peticion.getParameter ("idproducto");
        productoServicio.eliminaProducto (idusuario, idproducto);
        return "redirect:/vendedor";
    }

    @ PostMapping ("/resena")
    public String resena (HttpServletRequest peticion, Model modelo, Principal principal) {
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