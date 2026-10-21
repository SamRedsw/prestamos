package com.example.prestamos.service;

import com.example.prestamos.model.Herramienta;
import com.example.prestamos.repository.HerramientaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HerramientaService {

    private final HerramientaRepository herramientaRepository;

    @Transactional
    public Herramienta registrarHerramienta(Herramienta herramienta) {
        if (herramienta.getCantidad() < 0) {
            throw new RuntimeException("La cantidad no puede ser negativa");
        }
        return herramientaRepository.save(herramienta);
    }

    @Transactional(readOnly = true)
    public List<Herramienta> obtenerTodas() {
        return herramientaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Herramienta obtenerPorId(Long id) {
        return herramientaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Herramienta no encontrada con ID: " + id));
    }
}
