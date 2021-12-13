package choppr.chopstore.servicio.impl;

import choppr.chopstore.servicio.EnviadorCorreo;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Servicio que se encargará de manejar la gestión de los correos electrónicos
 * @author Eric Toporek Coca
 * @author Fransisco Alejandro Arganis Ramirez
 * @author Jessica Monter Gallardo
 * @version 1.1
 */

@ Service
@ AllArgsConstructor
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

    /**
     * Envía la contraseña al destinatario especificado
     * @param destinatario es el correo del usuario que recibe la contraseña
     * @param contrasena es la contraseña del usuario que recibe tras su registro
     */

    @ Override
    @ Async
    public void enviaContrasena (String destinatario, String contrasena) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage ();
            MimeMessageHelper helper = new MimeMessageHelper (mimeMessage, true, "UTF-8");
            helper.setFrom ("chopstore.tester@gmail.com");
            helper.setTo(destinatario);
            helper.setSubject("Contraseña Chopstore");
            String body = "<body><div style='background-color: #0d7377; color: white; padding: 4em'><div><p style='font-size: 4em'>¡Bienvenido(a)!</p></div><div><img src='cid:ChopstoreLogo' alt='Chopstore'></div><div><p style='font-size: 2em'>Te has registrado con éxito en Chopstore. Tu contraseña es: <span style='background-color: #17ccd3; color: black; border-radius: 50rem; display: inline-block; padding: .35em .65em; font-size: 1em; font-weight: 512; line-height: 1; text-align: center; white-space: nowrap'>"
                          + contrasena
                          + "</span></p></div></div></body>";
            helper.setText (body, true);
            helper.addInline ("ChopstoreLogo", new ClassPathResource ("/static/img/chopstore-correo.png"));
            mailSender.send (mimeMessage);
        } catch (MessagingException e){
            LOGGER.error ("Falla en enviar correo.", e);
            throw new IllegalStateException ("Falla en enviar correo.");
        }
    }
}
