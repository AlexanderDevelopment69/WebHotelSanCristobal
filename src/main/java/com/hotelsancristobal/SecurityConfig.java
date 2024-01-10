package com.hotelsancristobal;

import com.hotelsancristobal.service.CustomUserDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomUserDetailsServices customUserDetailsServices;


    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {


        httpSecurity
//                .csrf().disable()
                .authorizeHttpRequests()
//                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/css/**", "/fonts/**", "/image/**", "/js/**", "/royal-Doc/**", "/scss/**", "/vendors/**").permitAll()
                .requestMatchers("/index", "/habitaciones", "/nosotros", "/contacto", "/clientes/login", "/clientes/registro").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/clientes/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/index")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/index").permitAll() // Redirige a la página de inicio después de cerrar sesión
                .permitAll();
//                .and()
//                .sessionManagement(sessionManagement ->
//                        sessionManagement
//                                .maximumSessions(1)
//                                .expiredUrl("/clientes/login?expired")
//                                .maxSessionsPreventsLogin(true)
//                );


        return httpSecurity.build();

    }

//    @SuppressWarnings("removal")
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
//
//        http.csrf().disable().authorizeHttpRequests()
//                .requestMatchers("/css/**", "/fonts/**", "/image/**", "/js/**", "/royal-Doc/**", "/scss/**", "/vendors/**").permitAll()
//                .requestMatchers("/clientes/registro").permitAll()
//                .requestMatchers("/index").permitAll().and()
//                .formLogin()
//                .loginPage("/clientes/login")
//                .loginProcessingUrl("/clientes/login")
//                .defaultSuccessUrl("/index", true).permitAll()
//                .and()
//                .logout()
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login?logout").permitAll();
//
//        return http.build();
//
//    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailsServices).passwordEncoder(passwordEncoder());
//    }


}
