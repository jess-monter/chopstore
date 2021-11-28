package choppr.chopstore.repositorio;

import choppr.chopstore.modelo.Compra;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interfaz para conectar con la base de datos y realizar consultas relacionadas a la tabla compra
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

@ Repository
public interface CompraRepositorio extends JpaRepository <Compra, Integer> {

    /**
     * Realiza una consulta que recupera todas las compras que ha hecho un usuario de un cierto producto
     * @param idusuario el identificador del usuario
     * @param idproducto el identificador del producto
     * @return una lista con los resultados de la consulta
     */

    @ Query (value = "SELECT compra.idcompra, idusuario, pago, fecha FROM compra JOIN involucrar WHERE compra.idcompra = involucrar.idcompra AND idusuario = :u AND idproducto = :p", nativeQuery = true)
    public List <Compra> buscaComprasDeUsuarioProducto (@ Param ("u") Integer idusuario, @ Param ("p") Integer idproducto);
    
}
