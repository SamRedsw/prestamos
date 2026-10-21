package com.example.prestamos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrestamoRequest {

    @NotNull(message = "El ID de la herramienta es obligatorio")
    private Long herramientaId;

    @NotBlank(message = "El responsable es obligatorio")
    private String responsable;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fechaPrestamo;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a cero")
    private Integer cantidad;
}