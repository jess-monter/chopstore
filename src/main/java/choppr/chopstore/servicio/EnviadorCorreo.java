package choppr.chopstore.servicio;

public interface EnviadorCorreo {
    void envia(String destinatario, String correo, String asunto);
}
