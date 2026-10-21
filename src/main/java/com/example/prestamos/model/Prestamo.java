package com.example.prestamos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El responsable es obligatorio")
    @Column(nullable = false, length = 100)
    private String responsable;

    @NotNull(message = "La fecha del prestamo es obligatoria")
    @Column(name = "fecha_prestamo", nullable = false)
    private LocalDate fechaPrestamo;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "Debe prestar al menos 1 unidad")
    @Column(nullable = false)
    private Integer cantidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "herramienta_id", nullable = false)
    private Herramienta herramienta;
}