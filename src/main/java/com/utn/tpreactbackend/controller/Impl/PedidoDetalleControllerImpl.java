package com.utn.tpreactbackend.controller.Impl;

import com.utn.tpreactbackend.entities.PedidoDetalle;
import com.utn.tpreactbackend.service.Impl.PedidoDetalleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidoDetalles")
public class PedidoDetalleControllerImpl {

    @Autowired
    private PedidoDetalleServiceImpl pedidoDetalleService;

    @GetMapping
    public List<PedidoDetalle> getAllPedidoDetalles() {
        return pedidoDetalleService.getAllPedidoDetalles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDetalle> getPedidoDetalleById(@PathVariable Long id) {
        PedidoDetalle pedidoDetalle = pedidoDetalleService.getPedidoDetalleById(id);
        if (pedidoDetalle != null) {
            return ResponseEntity.ok(pedidoDetalle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public PedidoDetalle createPedidoDetalle(@RequestBody PedidoDetalle pedidoDetalle) {
        return pedidoDetalleService.savePedidoDetalle(pedidoDetalle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDetalle> updatePedidoDetalle(@PathVariable Long id, @RequestBody PedidoDetalle pedidoDetalle) {
        PedidoDetalle existingPedidoDetalle = pedidoDetalleService.getPedidoDetalleById(id);
        if (existingPedidoDetalle != null) {
            pedidoDetalle.setId(id);
            PedidoDetalle updatedPedidoDetalle = pedidoDetalleService.savePedidoDetalle(pedidoDetalle);
            return ResponseEntity.ok(updatedPedidoDetalle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedidoDetalle(@PathVariable Long id) {
        if (pedidoDetalleService.getPedidoDetalleById(id) != null) {
            pedidoDetalleService.deletePedidoDetalle(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

