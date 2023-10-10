package com.magicland.MagicLandPark.config;

import com.magicland.MagicLandPark.config.DetaliiPersoanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;

@Configuration
public class ConfiguratieSecuritate implements WebMvcConfigurer {

    // Configurare securitate
    @Configuration
    @Order(1) // Aceasta specifica ordinea de incarcare a configuratiei
    public static class ConfigurareSecuritate extends WebSecurityConfigurerAdapter {

        @Autowired
        private DetaliiPersoanaService detaliiPersoanaService;


        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(detaliiPersoanaService).passwordEncoder(new BCryptPasswordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .cors().disable()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/register").permitAll()
                    .antMatchers("/current-user").permitAll()
                    .antMatchers("/persoane/tipuri").permitAll()
                    .antMatchers("/persoane/**").permitAll()
                    .antMatchers("/activitati/denumire/**").permitAll()
                    .antMatchers("/activitati").permitAll()
                    .antMatchers("/activitati/**").permitAll()
                    .antMatchers("/tipuriActivitate").permitAll()
                    .antMatchers("/niveleDificultate").permitAll()
                    .antMatchers("/persoane/rezervari/**").permitAll()
                    .antMatchers("/rezervari/delete/**").permitAll()
                    .antMatchers("/rezervare").permitAll()
                    .antMatchers("/rezervare/**").permitAll()
                    .antMatchers("/rezervare/nrBon/**").permitAll()
                    .antMatchers("/tichete").permitAll()
                    .antMatchers("/tichete/**").permitAll()
                    .antMatchers("/trimite-email").permitAll()
                    .antMatchers("/tichete/bonuri/**").permitAll()
                    .antMatchers("/adauga-CategoriiTichete").permitAll()
                    .antMatchers("/categorii-tichete").permitAll()
                    .antMatchers("/tipuriTichet").permitAll()
                    .antMatchers("/zone").permitAll()
                    .antMatchers("/harta/**").permitAll()
                    .antMatchers("/reservation/{id}/details").permitAll()
                    .antMatchers("/reservation/**").permitAll()
                    .antMatchers("/subscribe").permitAll()
                    .antMatchers("/unsubscribe").permitAll()
                    .antMatchers("/changepassword").permitAll()
                    .antMatchers("/reset").permitAll()
                    .antMatchers("/withCategorii").permitAll()
//                    .antMatchers("/**/delete/**").hasAuthority("ADMINISTRATOR")
                    .anyRequest().authenticated()
                    .and().httpBasic();
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("*");
            }
        };
    }

    // Configurare CORS
    @Configuration
    @Order(2) // Aceasta specifica ordinea de incarcare a configuratiei
    public static class ConfigurareCORS implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:4200")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowedHeaders("*");
        }
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("emma.silvia7@gmail.com");
        mailSender.setPassword("rymnbildyikjdjbp");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }


}
