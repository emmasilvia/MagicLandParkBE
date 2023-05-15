package com.magicland.MagicLandPark.config;

import com.magicland.MagicLandPark.config.DetaliiPersoanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
                    .antMatchers("/test").permitAll()
                    .antMatchers("/persoane/tipuri").permitAll()
                    .antMatchers("/activitati/nume/**").permitAll()
                    .antMatchers("/activitati").permitAll()
                    .antMatchers("/rezervari").permitAll()
//                    .antMatchers("/**/delete/**").hasAuthority("ADMINISTRATOR")
                    .anyRequest().authenticated()
                    .and().httpBasic();
        }
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

//    // Configurare CORS
//    @Configuration
//    @Order(2) // Aceasta specifica ordinea de incarcare a configuratiei
//    public static class ConfigurareCORS implements WebMvcConfigurer {
//
//        @Override
//        public void addCorsMappings(CorsRegistry registry) {
//            registry.addMapping("/**")
//                    .allowedOrigins("http://localhost:4200")
//                    .allowedMethods("GET", "POST", "PUT", "DELETE")
//                    .allowedHeaders("*");
//        }
//    }


}
