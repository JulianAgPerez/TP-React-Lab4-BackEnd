package com.utn.tpreactbackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class PedidoDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @JsonBackReference //Sirve para resaltar que es hija (y evitar referencia circular)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "instrumento_id")
    private Instrumento instrumento;
}
