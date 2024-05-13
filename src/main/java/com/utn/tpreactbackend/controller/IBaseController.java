package com.utn.tpreactbackend.controller;

import com.utn.tpreactbackend.entities.Base;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

public interface IBaseController <E extends Base, Id extends Serializable> {
    ResponseEntity<?> findAllByCategoriaId(@PathVariable Id idCategoria);
    ResponseEntity<?> findAll();

    ResponseEntity<?> findAll(Pageable pageable);

    ResponseEntity<?> findById(@PathVariable Id id);

    ResponseEntity<?> save(@RequestBody E entity);

    ResponseEntity<?> update(@PathVariable Id id, @RequestBody E entity);

    ResponseEntity<?> darDeAlta(@PathVariable Id id);

    ResponseEntity<?> delete(@PathVariable Id id);
}
