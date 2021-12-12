package choppr.chopstore.excepciones;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Excepción que genera un error 406
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

@ ResponseStatus (code = HttpStatus.NOT_ACCEPTABLE, reason = "Un valor dado no está permitido")
public class InvalidValueException extends RuntimeException {
    
}
