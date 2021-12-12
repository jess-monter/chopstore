package choppr.chopstore.excepciones;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Excepción que genera un error 409
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

@ ResponseStatus (code = HttpStatus.CONFLICT, reason = "El correo ya está registrado")
public class EmailAlreadyTakenException extends RuntimeException {
    
    /**
     * Construye una nueva excepción de tipo EmailAlreadyTakenException con el correo especificado como mensaje
     * @param correo es el correo que se intentó utilizar pero ya está registrado
     */

    public EmailAlreadyTakenException (String correo) {
        super (correo);
    }

}
