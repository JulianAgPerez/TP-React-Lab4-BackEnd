package com.utn.tpreactbackend.service.Impl;
import com.utn.tpreactbackend.entities.PedidoDetalle;
import com.utn.tpreactbackend.repository.PedidoDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoDetalleServiceImpl {

    @Autowired
    private PedidoDetalleRepository pedidoDetalleRepository;

    public List<PedidoDetalle> getAllPedidoDetalles() {
        return pedidoDetalleRepository.findAll();
    }

    public PedidoDetalle getPedidoDetalleById(Long id) {
        return pedidoDetalleRepository.findById(id).orElse(null);
    }

    public PedidoDetalle savePedidoDetalle(PedidoDetalle pedidoDetalle) {
        return pedidoDetalleRepository.save(pedidoDetalle);
    }

    public void deletePedidoDetalle(Long id) {
        pedidoDetalleRepository.deleteById(id);
    }

}

