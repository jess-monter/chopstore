package choppr.chopstore.excepciones;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Excepción que genera un error 404
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

@ ResponseStatus (code = HttpStatus.NOT_FOUND, reason = "No se encuentra el elemento solicitado")
public class ElementNotFoundException extends RuntimeException {

}
