package com.utn.tpreactbackend.repository;

import com.utn.tpreactbackend.entities.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido, Long> {
    @Query("SELECT DAY(p.fechaPedido), COUNT(p) FROM Pedido p WHERE MONTH(p.fechaPedido) = :mes AND YEAR(p.fechaPedido) = :anio GROUP BY DAY(p.fechaPedido)")
    List<Object[]> countByMonthAndYear(@Param("mes") int mes, @Param("anio") int anio);

    @Query("SELECT COUNT(pd), pd.instrumento FROM PedidoDetalle pd GROUP BY pd.instrumento")
    List<Object[]> countByInstrumento();

}
