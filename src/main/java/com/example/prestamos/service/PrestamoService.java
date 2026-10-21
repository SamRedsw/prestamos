package com.example.prestamos.service;

import com.example.prestamos.dto.PrestamoRequest;
import com.example.prestamos.model.Prestamo;

import com.example.prestamos.model.Herramienta;

import com.example.prestamos.repository.HerramientaRepository;
import com.example.prestamos.repository.PrestamoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final HerramientaRepository herramientaRepository;

    @Transactional
    public Prestamo registrarPrestamo(PrestamoRequest dto) {
        //reglas
        if (dto.getCantidad() <= 0) {
            throw new RuntimeException("La cantidad prestada debe ser mayor a cero");
        }

        Herramienta herramienta = herramientaRepository.findById(dto.getHerramientaId())
                .orElseThrow(() -> new RuntimeException("La herramienta indicada no existe"));

        if (dto.getCantidad() > herramienta.getCantidad()) {
            throw new RuntimeException("Stock insuficiente. Solicitadas: " + dto.getCantidad() +
                    ", Disponibles: " + herramienta.getCantidad());
        }

        //descontar inventario dentro de la misma transaccion
        herramienta.setCantidad(herramienta.getCantidad() - dto.getCantidad());
        herramientaRepository.save(herramienta);

        Prestamo prestamo = Prestamo.builder()
                .responsable(dto.getResponsable())
                .fechaPrestamo(dto.getFechaPrestamo())
                .cantidad(dto.getCantidad())
                .herramienta(herramienta)
                .build();

        return prestamoRepository.save(prestamo);
    }

    @Transactional(readOnly = true)
    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Prestamo> obtenerPorHerramienta(Long herramientaId) {
        return prestamoRepository.findByHerramientaId(herramientaId);
    }
}