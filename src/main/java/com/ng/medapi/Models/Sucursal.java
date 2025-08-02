package com.ng.medapi.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Sucursal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "El nombre de la sucursal no puede estar vacio")
    private String nombre;

    @NotBlank(message = "La dirección no puede estar vacia")
    private String direccion;

    @NotBlank(message = "El teléfono no puede estar vacio")
    private String telefono;

    @NotNull(message = "La longitud no puede ser nula")
    private Double longitud;

    @NotNull(message = "La latitud no puede ser nula")
    private Double latitud;


}
