package com.hotelsancristobal.service;

import com.hotelsancristobal.model.Cliente;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Data
public class CustomUserDetails implements UserDetails {

    Cliente cliente;


    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Cliente cliente) {
        this.cliente=cliente;
        this.authorities = Arrays.asList(new SimpleGrantedAuthority("CLIENT"));
    }

//    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
//                             String nombre) {
//
//        this.username = username;
//        this.password = password;
//        this.authorities = authorities;
//        this.nombre= nombre;
//
//
//    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return cliente.getPassword();
    }

    @Override
    public String getUsername() {
        return cliente.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
