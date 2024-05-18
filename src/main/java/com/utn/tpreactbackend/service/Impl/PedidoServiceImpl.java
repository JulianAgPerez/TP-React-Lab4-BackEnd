package com.utn.tpreactbackend.service.Impl;

import com.utn.tpreactbackend.entities.Pedido;
import com.utn.tpreactbackend.repository.BaseRepository;
import com.utn.tpreactbackend.service.IPedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl extends BaseServiceImpl<Pedido,Long> implements IPedidoService {
    public PedidoServiceImpl(BaseRepository<Pedido, Long> baseRepository) {
        super(baseRepository);
    }
}
