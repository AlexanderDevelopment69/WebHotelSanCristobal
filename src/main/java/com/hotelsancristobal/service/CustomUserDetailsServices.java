package com.hotelsancristobal.service;

import com.hotelsancristobal.model.Cliente;
import com.hotelsancristobal.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
public class CustomUserDetailsServices implements UserDetailsService {


    @Autowired
    private ClienteRepository clienteRepository;



    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.println("CORREO ELECTRONICO ENVIADO: " + username);
        Cliente cliente = clienteRepository.findByEmail(username);

        if (cliente == null) {
            throw new RuntimeException("Username or password not found");
        }
        return new CustomUserDetails(cliente);
    }
    public Collection<? extends GrantedAuthority> authorities () {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }
}
