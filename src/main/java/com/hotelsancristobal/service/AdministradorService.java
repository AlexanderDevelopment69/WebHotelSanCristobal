package com.hotelsancristobal.service;

import com.hotelsancristobal.model.Administrador;
import com.hotelsancristobal.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Administrador registrarAdministrador(Administrador administrador) {
        String passwordHash = passwordEncoder.encode(administrador.getPassword());
        administrador.setPassword(passwordHash);
        return administradorRepository.save(administrador);
    }

    public List<Administrador> obtenerTodosAdministradores() {
        return administradorRepository.findAll();
    }

    public Optional<Administrador> obtenerAdministradorPorId(Long id) {
        return administradorRepository.findById(id);
    }

    public Administrador obtenerAdministradorPorEmail(String username) {
        return administradorRepository.findByEmail(username);
    }

    public Administrador actualizarAdministrador(Long id, Administrador nuevoAdministrador) {
        if (administradorRepository.existsById(id)) {
            nuevoAdministrador.setId(id);
            return administradorRepository.save(nuevoAdministrador);
        } else {
            return null;
        }
    }

    public void guardarAdministrador(Administrador administrador) {
        administradorRepository.save(administrador);
    }

    public void actualizarDatosAdministrador(Administrador administrador) {
        Administrador administradorExistente = administradorRepository.findByEmail(administrador.getEmail());

        if (administradorExistente != null) {
            administradorExistente.setNombres(administrador.getNombres());
            administradorExistente.setApellidos(administrador.getApellidos());
            administradorExistente.setDni(administrador.getDni());

            // Puedes agregar más campos según sea necesario

            administradorRepository.save(administradorExistente);
        }
    }

    public boolean verificarPasswordActual(String username, String oldPassword) {
        Administrador administrador = administradorRepository.findByEmail(username);
        return passwordEncoder.matches(oldPassword, administrador.getPassword());
    }

    public void actualizarPassword(String username, String newPassword) {
        Administrador administrador = administradorRepository.findByEmail(username);
        administrador.setPassword(passwordEncoder.encode(newPassword));
        administradorRepository.save(administrador);
    }

    public void eliminarAdministrador(Long id) {
        administradorRepository.deleteById(id);
    }
}
