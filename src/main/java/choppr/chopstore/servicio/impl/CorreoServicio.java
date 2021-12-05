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

@Service
@AllArgsConstructor
public class CorreoServicio implements EnviadorCorreo {

    private final JavaMailSender mailSender;

    private final static Logger LOGGER = LoggerFactory
            .getLogger(CorreoServicio.class);

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
