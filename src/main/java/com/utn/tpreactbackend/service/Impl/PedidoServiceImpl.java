package com.utn.tpreactbackend.service.Impl;

import com.utn.tpreactbackend.entities.Pedido;
import com.utn.tpreactbackend.repository.BaseRepository;
import com.utn.tpreactbackend.repository.PedidoRepository;
import com.utn.tpreactbackend.service.IPedidoService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoServiceImpl extends BaseServiceImpl<Pedido,Long> implements IPedidoService {
    public PedidoServiceImpl(BaseRepository<Pedido, Long> baseRepository) {
        super(baseRepository);
    }
    @Autowired
    private PedidoRepository pedidoRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Pedido> findAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        // Inicializar explícitamente la colección lazy
        pedidos.forEach(pedido -> Hibernate.initialize(pedido.getPedidoDetalles()));
        return pedidos;
    }
    @Transactional(readOnly = true)
    public Pedido findLast(){
        Pedido pedido = pedidoRepository.findAll().getLast();
        if (pedido != null) {
            // Forzar la carga de la colección pedidoDetalles antes de devolver el Pedido
            pedido.getPedidoDetalles().size();
        }
        return pedido;
    }

    @Override
    public List<Object[]> countByMonthAndYear(int mes, int anio) {
        return pedidoRepository.countByMonthAndYear(mes, anio);
    }

    @Override
    public List<Object[]> countByInstrumento() {
        return pedidoRepository.countByInstrumento();
    }

}
