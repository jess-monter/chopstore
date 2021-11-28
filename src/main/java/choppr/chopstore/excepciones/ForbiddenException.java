package choppr.chopstore.excepciones;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Excepción que genera un error 403
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

@ ResponseStatus (code = HttpStatus.FORBIDDEN, reason = "No se cuentan con los permisos para realizar la acción")
public class ForbiddenException extends RuntimeException {

}
