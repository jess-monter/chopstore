package choppr.chopstore.controlador;

import choppr.chopstore.excepciones.ForbiddenException;
import choppr.chopstore.excepciones.ElementNotFoundException;

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
 * @version 1.0
 */

@ ControllerAdvice
public class ErrorControlador {

    /**
     * Responde a una excepción de tipo ElementNotFoundException o NullPointerException
     * @param e es la excepción que se dispara en el error
     * @return la página de error
     */

    @ ExceptionHandler ({ElementNotFoundException.class, NullPointerException.class})
    public ModelAndView noEncontrado (Exception e) {
        ModelAndView modeloVista = new ModelAndView ("error", HttpStatus.NOT_FOUND);
        modeloVista.addObject ("imagen", 0);
        modeloVista.addObject ("mensaje", "No se puede encontrar un elemento solicitado");
        return modeloVista;
    }

    /**
     * Responde a una excepción de tipo ForbiddenException, AccessDeniedException o HttpRequestMethodNotSupportedException
     * @param e es la excepción que se dispara en el error
     * @return la página de error
     */

    @ ExceptionHandler ({ForbiddenException.class, AccessDeniedException.class, HttpRequestMethodNotSupportedException.class})
    public ModelAndView prohibido (Exception e) {
        ModelAndView modeloVista = new ModelAndView ("error", HttpStatus.FORBIDDEN);
        modeloVista.addObject ("imagen", 1);
        modeloVista.addObject ("mensaje", "Se ha intentado realizar una acción no permitida");
        return modeloVista;
    }

    /**
     * Responde a cualquier excepción que no ha sida atendida por otro método de este controlador
     * @param e es la excepción que se dispara en el error
     * @return la página de error
     */

    @ ExceptionHandler (Exception.class)
    public ModelAndView error (Exception e) {
        ModelAndView modeloVista = new ModelAndView ("error");
        modeloVista.addObject ("imagen", 2);
        modeloVista.addObject ("mensaje", "Algo salió mal al procesar tu petición");
        return modeloVista;
    }
    
}