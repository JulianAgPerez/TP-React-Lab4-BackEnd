package com.utn.tpreactbackend.service;

import com.utn.tpreactbackend.entities.Pedido;

import java.util.List;

public interface IPedidoService extends IBaseService<Pedido, Long>{
    List<Object[]> countByMonthAndYear(int mes, int anio) throws Exception;
    List<Object[]> countByInstrumento() throws Exception;
}
