package com.utn.tpreactbackend.controller.Impl;

import com.utn.tpreactbackend.controller.IInstrumentoController;
import com.utn.tpreactbackend.entities.Instrumento;
import com.utn.tpreactbackend.service.IInstrumentoService;
import com.utn.tpreactbackend.service.Impl.InstrumentoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instrumentos")
@CrossOrigin(origins = "*")
public class InstrumentoControllerImpl extends BaseControllerImpl<Instrumento, InstrumentoServiceImpl> implements IInstrumentoController{
    public InstrumentoControllerImpl(InstrumentoServiceImpl servicio) {
        super(servicio);
    }


/*
    @Autowired
    private IInstrumentoService instrumentoService;

    @GetMapping("/productos")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(instrumentoService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(instrumentoService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
*/
}
