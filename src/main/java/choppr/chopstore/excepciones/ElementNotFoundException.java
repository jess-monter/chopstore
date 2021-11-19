package choppr.chopstore.excepciones;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ ResponseStatus (code = HttpStatus.NOT_FOUND, reason = "No se encuentra el elemento solicitado")
public class ElementNotFoundException extends RuntimeException {

}
