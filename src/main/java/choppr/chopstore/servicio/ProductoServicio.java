package choppr.chopstore.servicio;

import choppr.chopstore.datos.ProductoDatos;

public interface ProductoServicio {
    
    public ProductoDatos [] [] buscaProductos (String busqueda, String categoria);

    public ProductoDatos [] obtenProductosDeUsuario (String idusario);

    public ProductoDatos consultaPorId (String idproducto);

    public ProductoDatos [] recomiendaProductos (String idusuario);

    public ProductoDatos [] obtenProductosMasVendidos ();

    public void eliminaProducto (String idusuario, String idproducto);

}
