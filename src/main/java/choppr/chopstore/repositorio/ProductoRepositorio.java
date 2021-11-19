package choppr.chopstore.repositorio;

import choppr.chopstore.modelo.Producto;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@ Repository
public interface ProductoRepositorio extends JpaRepository <Producto, Integer> {

    public List <Producto> findAll ();

    public Producto findProductoByIdproducto (Integer idproducto);

    @ Query (value = "SELECT * FROM producto JOIN categoria WHERE producto.idcategoria = categoria.idcategoria AND categoria.nombre = :c AND producto.nombre LIKE :pc", nativeQuery = true)
    public List <Producto> buscaCoincidenciasPorCategoria (@ Param ("pc") String palabraClave, @ Param ("c") String categoria);

    @ Query (value = "SELECT * FROM producto WHERE nombre LIKE :pc", nativeQuery = true)
    public List <Producto> buscaTodasCoincidencias (@ Param ("pc") String palabraCave);

    @ Query (value = "SELECT * FROM producto JOIN categoria WHERE producto.idcategoria = categoria.idcategoria AND categoria.nombre = :c", nativeQuery = true)
    public List <Producto> buscaTodasCategorias (@ Param ("c") String categoria);

}
