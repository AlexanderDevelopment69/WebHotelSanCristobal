package com.hotelsancristobal.service;

import com.hotelsancristobal.model.Administrador;
import com.hotelsancristobal.model.Cliente;
import com.hotelsancristobal.repository.AdministradorRepository;
import com.hotelsancristobal.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsServices implements UserDetailsService {


    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        System.out.println("CORREO ELECTRONICO ENVIADO: " + username);
//        Cliente cliente = clienteRepository.findByEmail(username);
//
//        if (cliente == null) {
//            throw new RuntimeException("Username or password not found");
//        }
//        return new CustomUserDetails(cliente);
//    }

//    public Collection<? extends GrantedAuthority> authorities () {
//        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
//    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.println("CORREO ELECTRONICO ENVIADO: " + username);

        Cliente cliente = clienteRepository.findByEmail(username);
        if (cliente != null) {
            return new CustomUserDetails(cliente);
        }

        Administrador administrador = administradorRepository.findByEmail(username);
        if (administrador != null) {
            return new CustomAdminDetails(administrador);
        }

        throw new RuntimeException("Usuario o contraseña no encontrados");
    }

    public Collection<? extends GrantedAuthority> authorities(UserDetails userDetails) {
        if (userDetails instanceof CustomUserDetails) {
            return Arrays.asList(new SimpleGrantedAuthority("CLIENT"));
        } else if (userDetails instanceof CustomAdminDetails) {
            return Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
        }

        return Arrays.asList(new SimpleGrantedAuthority("CLIENT"));
    }



}
