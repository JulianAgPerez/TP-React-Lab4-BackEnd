package com.utn.tpreactbackend.controller.Impl;
import com.utn.tpreactbackend.controller.IPedidoController;
import com.utn.tpreactbackend.entities.Pedido;
import com.utn.tpreactbackend.repository.PedidoRepository;
import com.utn.tpreactbackend.service.Impl.PedidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

        //Calcula el total del pedido sumando los precios de los instrumentos por la cantidad
        double total = pedido.getPedidoDetalles().stream()
                .mapToDouble(detalle -> detalle.getInstrumento().getPrecio() * detalle.getCantidad())
                .sum();
        pedido.setTotalPedido(total);

        //Guarda en la db
        Pedido savedPedido = pedidoRepository.save(pedido);
        return ResponseEntity.ok("El pedido con id " + savedPedido.getId() + " se guardó correctamente");
    }
}
