package choppr.chopstore.servicio;

import choppr.chopstore.datos.ProductoDatos;

public interface ProductoServicio {
    
    public ProductoDatos [] [] buscaProductos (String busqueda, String categoria);

    public ProductoDatos consultaPorId (String idproducto);

    public ProductoDatos [] recomiendaProductos (String idusuario);

    public ProductoDatos [] obtenProductosMasVendidos ();

}
