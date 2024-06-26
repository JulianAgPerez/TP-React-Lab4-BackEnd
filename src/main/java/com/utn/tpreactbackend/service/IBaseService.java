package com.utn.tpreactbackend.service;

import com.utn.tpreactbackend.entities.Base;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<E extends Base, Id extends Serializable> {
    List<E> findAllByCategoriaId(Id idCategoria) throws Exception;
    Page<E> findAll(Pageable pageable) throws Exception;
    List<E> findAll() throws Exception;
    E findById(Id id) throws Exception;
    E save(E entity) throws Exception;
    E update(Id id, E entity) throws Exception;
    boolean darDeAlta(Id id) throws Exception;

    boolean delete(Id id) throws Exception;
}
