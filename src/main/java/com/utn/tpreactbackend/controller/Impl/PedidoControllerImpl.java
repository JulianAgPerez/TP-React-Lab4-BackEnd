package com.utn.tpreactbackend.controller.Impl;
import com.utn.tpreactbackend.controller.IPedidoController;
import com.utn.tpreactbackend.entities.Pedido;
import com.utn.tpreactbackend.entities.PedidoDetalle;
import com.utn.tpreactbackend.repository.PedidoRepository;
import com.utn.tpreactbackend.service.IPedidoService;
import com.utn.tpreactbackend.service.Impl.PedidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoControllerImpl  extends BaseControllerImpl<Pedido, PedidoServiceImpl> implements IPedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoControllerImpl(PedidoServiceImpl servicio) {
        super(servicio);
    }

    @GetMapping("/last")
    public ResponseEntity<Pedido> getLastPedido() {
        try {
            Pedido lastPedido = servicio.findLast();
            if (lastPedido != null) {
                return ResponseEntity.ok(lastPedido);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Override
    @PostMapping("/crear")
    public ResponseEntity<Object> createPedido(@RequestBody Pedido pedido) {
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

        // Crea un mapa para almacenar la información de la respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "El pedido con id " + savedPedido.getId() + " se guardó correctamente");
        response.put("idPedido", savedPedido.getId());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
