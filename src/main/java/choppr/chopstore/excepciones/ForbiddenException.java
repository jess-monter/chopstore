package choppr.chopstore.excepciones;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ ResponseStatus (code = HttpStatus.FORBIDDEN, reason = "No se cuentan con los permisos para realizar la acción")
public class ForbiddenException extends RuntimeException {

}
