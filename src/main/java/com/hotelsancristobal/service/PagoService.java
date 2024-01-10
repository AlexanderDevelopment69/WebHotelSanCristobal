package com.hotelsancristobal.service;

import com.hotelsancristobal.model.Pago;
import com.hotelsancristobal.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {
    @Autowired
    private PagoRepository pagoRepository;

    public Pago registrarPago(Pago pago) {
        // Lógica para validar y guardar el pago
        return pagoRepository.save(pago);
    }

    public List<Pago> obtenerTodosPagos() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> obtenerPagoPorId(Long id) {
        return pagoRepository.findById(id);
    }

    public Pago actualizarPago(Long id, Pago nuevoPago) {
        if (pagoRepository.existsById(id)) {
            nuevoPago.setIdPago(id);
            return pagoRepository.save(nuevoPago);
        } else {
            // Manejar caso en el que el pago no existe
            return null;
        }
    }

    public void eliminarPago(Long id) {
        pagoRepository.deleteById(id);
    }
}
