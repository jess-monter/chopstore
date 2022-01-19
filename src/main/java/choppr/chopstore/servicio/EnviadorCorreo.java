package choppr.chopstore.servicio;

/**
 * Interfaz que nos servirá para implementar el servicio de correo electrónico.
 * @author Eric Toporek Coca
 * @author Fransisco Alejandro Arganis Ramírez
 * @author Jessica Monter Gallardo
 * @version 1.1
 */

public interface EnviadorCorreo {
    /**
     * Método que envía un correo electrónico
     * @param destinatario A quien se le envía el correo.
     * @param correo El contenido del correo.
     * @param asunto EL asunto del correo.
     */
    void envia(String destinatario, String correo, String asunto);

    /**
     * Envía la contraseña al destinatario especificado
     * @param destinatario es el correo del usuario que recibe la contraseña
     * @param contrasena es la contraseña del usuario que recibe tras su registro
     */

    public void enviaContrasena (String destinatario, String contrasena);


     /**
     * Envía la contraseña al destinatario especificado
     * @param destinatario es el correo del usuario que recibe la confirmacion
     * @param idcompra el identificador de la compra
     * @param productos los productos involucrados en la compra
     */

    public void enviaConfirmacionCompra (String destinatario, String idcompra, String [] productos);
}
