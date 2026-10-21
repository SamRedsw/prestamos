package com.example.prestamos.controller;

import com.example.prestamos.model.Herramienta;
import com.example.prestamos.service.HerramientaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/herramientas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HerramientaController {

    private final HerramientaService herramientaService;

    @PostMapping
    public ResponseEntity<Herramienta> registrar(@Valid @RequestBody Herramienta herramienta) {
        return new ResponseEntity<>(herramientaService.registrarHerramienta(herramienta), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Herramienta>> consultarTodas() {
        return ResponseEntity.ok(herramientaService.obtenerTodas());
    }
}