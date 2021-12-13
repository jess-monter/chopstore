package choppr.chopstore.servicio.validacion;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que valida la entrada de un correo electrónico
 * @author Eric Toporek Coca
 * @author Fransico Alejandro Arganis Ramirez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */
@Service
public class CorreoValidador implements Predicate<String> {

    @Override
    public boolean test(String s) {
        String regex = "[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
}
