package choppr.chopstore.seguridad.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * Clase que establece la configuración de seguridad de métodos
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.0
 */

@ Configuration
@ EnableGlobalMethodSecurity (prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

}
