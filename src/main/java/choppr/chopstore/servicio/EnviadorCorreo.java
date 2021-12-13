package choppr.chopstore.servicio;

/**
 * Interfaz que nos servirá para implementar el servicio de correo electrónico.
 * @author Eric Toporek Coca
 * @author Fransisco Alejandro Arganis Ramírez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */
public interface EnviadorCorreo {
    /**
     * Método que envía un correo electrónico
     * @param destinatario A quien se le envía el correo.
     * @param correo El contenido del correo.
     * @param asunto EL asunto del correo.
     */
    void envia(String destinatario, String correo, String asunto);
}
