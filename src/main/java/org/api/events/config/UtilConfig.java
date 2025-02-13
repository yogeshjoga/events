package org.api.events.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UtilConfig {

    private static final Logger log = LoggerFactory.getLogger(UtilConfig.class);



    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        log.info("\u001B[1;32m :: Configuring BCrypt Password Encoder :: \u001B[0m");
        // for password printing into console
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String plainPassword = "yogesh";
        String encodedPassword = passwordEncoder.encode(plainPassword);
        // end

        log.info("\u001B[1;32m :: YOUR PASSWORD  " + encodedPassword +"  :: \u001B[0m");
        return new BCryptPasswordEncoder();
    }
}
