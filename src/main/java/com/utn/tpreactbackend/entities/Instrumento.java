package com.utn.tpreactbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Instrumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String instrumento;
    private String marca;
    private String modelo;
    private String imagen;
    private Double precio;
    @Column(name = "costoEnvio")
    private String costoEnvio;
    @Column(name = "cantidadVendida")
    private Integer cantidadVendida;
    private String descripcion;
}
