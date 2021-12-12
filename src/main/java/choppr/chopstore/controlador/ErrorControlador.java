package choppr.chopstore.controlador;

import choppr.chopstore.excepciones.*;
import choppr.chopstore.datos.SolicitudRegistro;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Clase que maneja los errores
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.2
 */

@ ControllerAdvice
public class ErrorControlador {

    /**
     * Responde a una excepcion de tipo EmailAlreadyTakenException
     * @param e es la excepción que se dispara en el error
     * @return la página de registro
     */

    @ ExceptionHandler (EmailAlreadyTakenException.class)
    public ModelAndView correoYaRegistrado (Exception e) {
        ModelAndView modeloVista = new ModelAndView ("register");
        modeloVista.addObject ("req", new SolicitudRegistro ());
        modeloVista.addObject ("regCorreo", e.getMessage ());
        return modeloVista;
    }

    /**
     * Responde a una excepción de tipo ForbiddenException, AccessDeniedException o HttpRequestMethodNotSupportedException
     * @param e es la excepción que se dispara en el error
     * @return la página de error
     */

    @ ExceptionHandler ({ForbiddenException.class, AccessDeniedException.class, HttpRequestMethodNotSupportedException.class})
    public ModelAndView noPermitido (Exception e) {
        ModelAndView modeloVista = new ModelAndView ("error", HttpStatus.FORBIDDEN);
        modeloVista.addObject ("status", 403);
        return modeloVista;
    }

    /**
     * Responde a una excepción de tipo ElementNotFoundException o NullPointerException
     * @param e es la excepción que se dispara en el error
     * @return la página de error
     */

    @ ExceptionHandler ({ElementNotFoundException.class, NullPointerException.class})
    public ModelAndView noEncontrado (Exception e) {
        ModelAndView modeloVista = new ModelAndView ("error", HttpStatus.NOT_FOUND);
        modeloVista.addObject ("status", 404);
        return modeloVista;
    }

    /**
     * Responde a una excepcion de tipo InvalidValueException
     * @param e es la excepción que se dispara en el error
     * @return la página de error
     */

    @ ExceptionHandler (InvalidValueException.class)
    public ModelAndView noValido (Exception e) {
        ModelAndView modeloVista = new ModelAndView ("error", HttpStatus.NOT_ACCEPTABLE);
        modeloVista.addObject ("status", 406);
        return modeloVista;
    }

    /**
     * Responde a cualquier excepción que no ha sida atendida por otro método de este controlador
     * @param e es la excepción que se dispara en el error
     * @return la página de error
     */

    @ ExceptionHandler (Exception.class)
    public ModelAndView error (Exception e) {
        return new ModelAndView ("error");
    }
    
}