package choppr.chopstore.seguridad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

/**
 * Clase de configuración para asegurar la autenticación con el front-end
 * @author Eric Toporek Coca
 * @author Fransisco Alejandro Arganis Ramirez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */
@Configuration
public class ThymeleafConfig {
    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }
}
