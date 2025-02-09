package org.api.events.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * <b>Write Security Beans in this configuration class</b>
 */
@Configuration
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/public/**", "/static/**", "/").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(formLogin -> formLogin
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .permitAll()
//                );
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        log.info("---------- Configuring SecurityFilterChain ---------------");
        http.cors().disable();
        http.csrf().disable();
       // http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().permitAll());
      //  http.oauth2Login(Customizer.withDefaults());
        return http.build();
    }




}
