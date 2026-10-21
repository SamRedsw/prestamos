package com.example.prestamos.controller;

import com.example.prestamos.dto.PrestamoRequest;
import com.example.prestamos.model.Prestamo;
import com.example.prestamos.service.PrestamoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/prestamos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PrestamoController {

    private final PrestamoService prestamoService;

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody PrestamoRequest dto) {
        try {
            Prestamo prestamo = prestamoService.registrarPrestamo(dto);
            return new ResponseEntity<>(prestamo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Prestamo>> consultarTodos() {
        return ResponseEntity.ok(prestamoService.obtenerTodos());
    }

    @GetMapping("/herramienta/{id}")
    public ResponseEntity<List<Prestamo>> consultarPorHerramienta(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.obtenerPorHerramienta(id));
    }
}