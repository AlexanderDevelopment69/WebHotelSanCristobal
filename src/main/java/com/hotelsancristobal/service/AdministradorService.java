package com.hotelsancristobal.service;

import com.hotelsancristobal.model.Administrador;
import com.hotelsancristobal.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository administradorRepository;

    public Administrador registrarAdministrador(Administrador administrador) {
        // Lógica para validar y guardar el administrador
        return administradorRepository.save(administrador);
    }

    public List<Administrador> obtenerTodosAdministradores() {
        return administradorRepository.findAll();
    }

    public Optional<Administrador> obtenerAdministradorPorId(Long id) {
        return administradorRepository.findById(id);
    }

    public Administrador actualizarAdministrador(Long id, Administrador nuevoAdministrador) {
        if (administradorRepository.existsById(id)) {
            nuevoAdministrador.setId(id);
            return administradorRepository.save(nuevoAdministrador);
        } else {
            // Manejar caso en el que el administrador no existe
            return null;
        }
    }

    public void eliminarAdministrador(Long id) {
        administradorRepository.deleteById(id);
    }

    public Administrador obtenerAdministradorPorEmail(String email) {
        return administradorRepository.findByEmail(email);
    }
}
