package com.utn.tpreactbackend.controller.Impl;

import com.utn.tpreactbackend.controller.IInstrumentoController;
import com.utn.tpreactbackend.entities.Instrumento;
import com.utn.tpreactbackend.entities.Pedido;
import com.utn.tpreactbackend.entities.PreferenceMp;
import com.utn.tpreactbackend.service.Impl.InstrumentoServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instrumentos/productos")
@CrossOrigin(origins = "*")
public class InstrumentoControllerImpl extends BaseControllerImpl<Instrumento, InstrumentoServiceImpl> implements IInstrumentoController{
    public InstrumentoControllerImpl(InstrumentoServiceImpl servicio) {
        super(servicio);
    }
    @PostMapping("/create_preference_mp")
    public PreferenceMp crearPreferenciaMercadoPago(@RequestBody Pedido pedido){
        MercadoPagoController cMercadoPago = new MercadoPagoController();
        PreferenceMp preference = cMercadoPago.getPreferenciaIdMercadoPago(pedido);
        return preference;
    }
}