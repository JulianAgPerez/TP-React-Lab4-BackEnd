package com.utn.tpreactbackend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Pedido extends Base {
    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    */

    @Column(name = "fechaPedido")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Argentina/Buenos_Aires")
    @Temporal(TemporalType.DATE)    //se usa para especificar que solo se debe almacenar la parte de la fecha (sin la hora) en la db
    private Date fechaPedido;
    @Column(name = "totalPedido")
    private double totalPedido;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference //sirve para marcar que es padre ( y evitar referencia circular)
    private List<PedidoDetalle> pedidoDetalles;
}
/*
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<PedidoDetalle> pedidoDetalles;
 */