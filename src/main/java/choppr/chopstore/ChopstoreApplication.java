package choppr.chopstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase que inicia la aplicación
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

@ SpringBootApplication
public class ChopstoreApplication {

	/**
	 * Inicia la ejecución de la aplicación
	 * @param args son argumentos de la línea de comandos
	 */

	public static void main (String [] args) {
		SpringApplication.run (ChopstoreApplication.class, args);
	}

}
