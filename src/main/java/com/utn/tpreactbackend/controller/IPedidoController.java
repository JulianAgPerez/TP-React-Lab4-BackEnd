package com.utn.tpreactbackend.controller;
import com.utn.tpreactbackend.entities.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IPedidoController extends IBaseController<Pedido,Long>{
    ResponseEntity<Object> createPedido(@RequestBody Pedido pedido);
}
