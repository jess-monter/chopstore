package choppr.chopstore.repositorio;

import choppr.chopstore.modelo.Compra;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@ Repository
public interface CompraRepositorio extends JpaRepository <Compra, Integer> {

    @ Query (value = "SELECT compra.idcompra, idusuario, pago, fecha FROM compra JOIN involucrar WHERE compra.idcompra = involucrar.idcompra AND idusuario = :u AND idproducto = :p", nativeQuery = true)
    public List <Compra> buscaComprasDeUsuarioProducto (@ Param ("u") Integer idusuario, @ Param ("p") Integer idproducto);
    
}
