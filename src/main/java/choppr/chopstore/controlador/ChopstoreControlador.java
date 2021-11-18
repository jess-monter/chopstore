package choppr.chopstore.controlador;

import choppr.chopstore.datos.ProductoDatos;
import choppr.chopstore.servicio.ProductoServicio;
import choppr.chopstore.datos.ResenaDatos;
import choppr.chopstore.servicio.ResenaServicio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String vendedor () {
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

    @ PostMapping ("/producto")
    public String producto (HttpServletRequest peticion, Model modelo) {
        String idproducto = peticion.getParameter ("idproducto");
        ProductoDatos producto = productoServicio.consultaPorId (idproducto);
        String [] porcentaje = new String [1];
        ResenaDatos [] resenas = resenaServicio.obtenResenasCalificacion (idproducto, porcentaje);
        modelo.addAttribute ("producto", producto);
        modelo.addAttribute ("resenas", resenas);
        modelo.addAttribute ("porcentaje", porcentaje [0]);
        return "producto";
    }

}