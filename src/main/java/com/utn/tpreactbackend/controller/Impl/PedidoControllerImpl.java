package com.utn.tpreactbackend.controller.Impl;
import com.utn.tpreactbackend.controller.IPedidoController;
import com.utn.tpreactbackend.entities.Pedido;
import com.utn.tpreactbackend.entities.PedidoDetalle;
import com.utn.tpreactbackend.repository.PedidoRepository;
import com.utn.tpreactbackend.service.IPedidoService;
import com.utn.tpreactbackend.service.Impl.PedidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoControllerImpl  extends BaseControllerImpl<Pedido, PedidoServiceImpl> implements IPedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoControllerImpl(PedidoServiceImpl servicio) {
        super(servicio);
    }

    @Override
    @PostMapping("/crear")
    public ResponseEntity<String> createPedido(@RequestBody Pedido pedido) {
        //Establece la fecha actual
        pedido.setFechaPedido(new Date());

        // Inicializa pedidoDetalles si es null
        if (pedido.getPedidoDetalles() == null) {
            pedido.setPedidoDetalles(new ArrayList<>());
        }

        //Calcula el total del pedido sumando los precios de los instrumentos por la cantidad
        double total = pedido.getPedidoDetalles().stream()
                .mapToDouble(detalle -> detalle.getInstrumento().getPrecio() * detalle.getCantidad())
                .sum();
        pedido.setTotalPedido(total);

        // Asigna el pedido a cada detalle
        for (PedidoDetalle detalle : pedido.getPedidoDetalles()) {
            detalle.setPedido(pedido);
        }

        //Guarda en la db
        Pedido savedPedido = pedidoRepository.save(pedido);
        return ResponseEntity.ok("El pedido con id " + savedPedido.getId() + " se guardó correctamente");
    }

}
