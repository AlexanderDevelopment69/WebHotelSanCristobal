package com.hotelsancristobal.service;

import com.hotelsancristobal.model.TipoHabitacion;
import com.hotelsancristobal.repository.TipoHabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoHabitacionService {

    private final TipoHabitacionRepository tipoHabitacionRepository;

    @Autowired
    public TipoHabitacionService(TipoHabitacionRepository tipoHabitacionRepository) {
        this.tipoHabitacionRepository = tipoHabitacionRepository;
    }

    public List<TipoHabitacion> getAllTiposHabitacion() {
        return tipoHabitacionRepository.findAll();
    }

    public Optional<TipoHabitacion> getTipoHabitacionById(Long id) {
        return tipoHabitacionRepository.findById(id);
    }

    public TipoHabitacion saveTipoHabitacion(TipoHabitacion tipoHabitacion) {
        return tipoHabitacionRepository.save(tipoHabitacion);
    }

    public void deleteTipoHabitacion(Long id) {
        tipoHabitacionRepository.deleteById(id);
    }
}
