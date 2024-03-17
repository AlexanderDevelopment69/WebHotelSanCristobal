package com.hotelsancristobal;

import com.hotelsancristobal.service.CustomUserDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
@EnableWebSecurity
public class SecurityConfig1 {

    @Autowired
    CustomUserDetailsServices customUserDetailsServices;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @SuppressWarnings("removal")
    @Bean
    @Order(1)
    public static SecurityFilterChain filterChain1(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/css/**", "/fonts/**", "/image/**", "/js/**", "/royal-Doc/**", "/scss/**", "/sounds/**","/vendors/**", "/assetsAdmin/**").permitAll()
                .requestMatchers("/index", "/habitaciones", "/nosotros", "/contacto", "/clientes/login", "/clientes/registro","/chat/send-message","/reserva/reserva_form/**","/reserva/verificar-disponibilidad/**" ).permitAll()
                .requestMatchers("/administrador/login", "/administrador/registro","/administrador/index").permitAll()
                .anyRequest().authenticated()

                .and()

                .formLogin()
                    .usernameParameter("email")
                    .loginPage("/clientes/login")
                    .defaultSuccessUrl("/index", true)
//                     .successHandler((request, response, authentication) -> response.sendRedirect("/index"))
//                     .successHandler(savedRequestAwareAuthenticationSuccessHandler())
                    .permitAll()
                .and()
                .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/index").permitAll();

        return httpSecurity.build();
    }


//    @SuppressWarnings("removal")
//    @Bean
//    @Order(2)
//    public SecurityFilterChain filterChain2(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/assetsAdmin/**").permitAll()
//                .requestMatchers("/administrador/login", "/administrador/registro").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .usernameParameter("email")  // Cambia a tu parámetro de nombre de usuario para administradores
//                .loginPage("/administrador/login")
//                .successHandler((request, response, authentication) -> {
//                    response.sendRedirect("/administrador/index");  // Cambia a la página de administrador
//                })
//                .permitAll()
//                .and()
//                .logout()
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/administrador/index").permitAll() // Redirige a la página de inicio después de cerrar sesión
//                .permitAll();
//
//        return httpSecurity.build();
//
//    }


    @Bean
    public static AuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
        SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setDefaultTargetUrl("/index"); // Página a la que se redirigirá después del inicio de sesión
        return successHandler;
    }


}
