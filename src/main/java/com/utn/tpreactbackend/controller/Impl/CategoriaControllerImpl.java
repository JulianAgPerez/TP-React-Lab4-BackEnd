package com.utn.tpreactbackend.controller.Impl;

import com.utn.tpreactbackend.controller.ICategoriaController;
import com.utn.tpreactbackend.entities.Categoria;
import com.utn.tpreactbackend.service.Impl.CategoriaServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path= "/categorias")
public class CategoriaControllerImpl extends BaseControllerImpl<Categoria, CategoriaServiceImpl> implements ICategoriaController {


    public CategoriaControllerImpl(CategoriaServiceImpl servicio) {
        super(servicio);
    }
}
