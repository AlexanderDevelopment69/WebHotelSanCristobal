package com.hotelsancristobal.service;

import com.hotelsancristobal.model.Administrador;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class CustomAdminDetails implements UserDetails {

    Administrador administrador;

    private Collection<? extends GrantedAuthority> authorities;


    public CustomAdminDetails(Administrador administrador) {
        this.administrador = administrador;
        this.authorities = Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @Override
    public String getPassword() {
        return administrador.getPassword();
    }

    @Override
    public String getUsername() {
        return administrador.getEmail();
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
