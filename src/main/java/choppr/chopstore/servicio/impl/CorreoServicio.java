package choppr.chopstore.servicio.impl;

import choppr.chopstore.servicio.EnviadorCorreo;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * Servicio que se encargará de manejar la gestión de los correos electrónicos
 * @author Eric Toporek Coca
 * @author Fransisco Alejandro Arganis Ramirez
 * @author Jessica Monter Gallardo
 */
@Service
@AllArgsConstructor
public class CorreoServicio implements EnviadorCorreo {

    private final JavaMailSender mailSender;

    private final static Logger LOGGER = LoggerFactory
            .getLogger(CorreoServicio.class);

    /**
     * Método que envía un correo electrónico
     * @param destinatario Una dirección de correo electrónico
     * @param correo El cuerpo del correo electrónico contenido
     * @param asunto El asunto del correo electrónico a enviar.
     */
    @Override
    @Async
    public void envia(String destinatario, String correo, String asunto) {
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setText(correo,true);
            helper.setTo(destinatario);
            helper.setSubject(asunto);
            helper.setFrom("chopstore.tester@gmail.com");
            mailSender.send(mimeMessage);
        }catch (MessagingException e){
            LOGGER.error("Falla en enviar correo.",e);
            throw new IllegalStateException("Falla en enviar correo.");
        }
    }
}
