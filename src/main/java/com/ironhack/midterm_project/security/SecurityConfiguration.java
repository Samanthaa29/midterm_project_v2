package com.ironhack.midterm_project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean //para sobreescribir configuración security
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(); //vamos a utilitzar basic auth
        http.csrf().disable(); //desactivamos la protección CSRF porque nosotros no vamos a manejar el HTML
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/checkings/id/{id}", "/creditCards/id/{id}", "/savings/id/{id}", "/studentCheckings/id/{id}").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/checkings/id/{id}", "/creditCards/id/{id}", "/savings/id/{id}", "/studentCheckings/id/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/checkings", "/creditCards", "/savings", "/studentCheckings").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/accountHolders/{id}", "/admins/{id}", "/thirdPartys/{id}").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/accountHolders/{id}", "/admins/{id}", "/thirdPartys/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/accountHolders", "/admins", "/thirdPartys").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/myAccounts").hasRole("USER")
                .antMatchers(HttpMethod.PATCH, "/checkings/{id}/balance", "/creditCards/{id}/balance", "/studentCheckings/{id}/balance", "/savings/{id}/balance","/checking/{id}/secretKey").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/checkings", "/creditCards", "/savings", "/studentCheckings").authenticated()
                .antMatchers(HttpMethod.POST, "/thirdPartys").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/checkings/{id}", "/creditCards/{id}", "/savings/{id}", "/studentCheckings/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/transference").hasRole("USER")
                .anyRequest().permitAll();
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
