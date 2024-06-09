package com.utn.tpreactbackend.repository;

import com.utn.tpreactbackend.entities.PedidoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Long> {
    @Query("SELECT pd FROM PedidoDetalle pd JOIN pd.pedido p WHERE p.fechaPedido BETWEEN :fechaDesde AND :fechaHasta")
    List<PedidoDetalle> findPedidosBetweenDates(@Param("fechaDesde") LocalDate fechaDesde, @Param("fechaHasta") LocalDate fechaHasta);
}

