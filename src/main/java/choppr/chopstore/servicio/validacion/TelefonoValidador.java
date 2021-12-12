package choppr.chopstore.servicio.validacion;

import java.util.function.Predicate;
import org.springframework.stereotype.Service;

/**
 * Predicado que verfica si una cadena es un número de teléfono válido
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

@ Service
public class TelefonoValidador implements Predicate <String> {

    /**
     * Evalúa este predicado con la cadena especificada
     * @param t es la cadena argumento
     * @return true si la cadena es un número de teléfono válido y false en otro caso
     */

    @ Override
    public boolean test (String t) {
        if (t == null || t.length () != 10) return false;
        return t.chars ().allMatch (Character::isDigit);
    }

}
