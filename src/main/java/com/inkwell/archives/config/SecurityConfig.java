package com.inkwell.archives.config;


import com.inkwell.archives.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import javax.swing.plaf.basic.BasicGraphicsUtils;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception {
    //TODO: For now, we are going to hold over csrf security protocol implementation until last part of the project. Why? We want to speed up everything just to focus on this implementation.

    return http
            .csrf(csrf -> csrf.disable())
            /*.authorizeHttpRequests(auth -> {
              auth.requestMatchers("/auth/login").permitAll();
              auth.requestMatchers("/auth/signup").permitAll();
            })*/
            .httpBasic(Customizer.withDefaults())
            // change stateless session to if_required because of there is not token authentication procedures.
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
            .formLogin(form -> form
                    // The view users will see if they want to log in
                    .loginPage("/authenticate")
                    // Handles submit at form
                    .loginProcessingUrl("/process-login")
                    // The view users will be redirected to if login succeeds
                    .defaultSuccessUrl("/home", true)
                    //
                    .failureUrl("/login?error=true")

            )
            .logout((logoutConfigurer) -> logoutConfigurer
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/authenticate")

            )
            .build();
  }

  // Authentication manager
  @Bean
  public AuthenticationManager authenticationManager(
          AuthenticationConfiguration authenticationConfiguration
  ) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  // Authentication provider: getting users from database
  @Bean
  public AuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsService) {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();


    provider.setPasswordEncoder(passwordEncoder());
    provider.setUserDetailsService(userDetailsService);
    return provider;
  }

  // Password Encoder
  @Bean
  public PasswordEncoder passwordEncoder() {

    return new BCryptPasswordEncoder();
  }
}
