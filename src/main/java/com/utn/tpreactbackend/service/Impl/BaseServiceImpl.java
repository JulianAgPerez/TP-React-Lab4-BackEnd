package com.utn.tpreactbackend.service.Impl;

import com.utn.tpreactbackend.entities.Base;
import com.utn.tpreactbackend.repository.BaseRepository;
import com.utn.tpreactbackend.service.IBaseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public abstract class BaseServiceImpl<E extends Base, Id extends Serializable> implements IBaseService<E, Id> {

    @Autowired
    protected BaseRepository<E,Id> baseRepository;

    public BaseServiceImpl(BaseRepository<E, Id> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    @Transactional
    public Page<E> findAll(Pageable pageable) throws Exception{
        try{
            return baseRepository.findAll(pageable);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<E> findAll() throws Exception {
        try{
            List<E> entities = baseRepository.findAll();
            if(entities.isEmpty()) throw new Exception("Aún no se han cargado registros");
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public List<E> findAllByCategoriaId(Id idCategoria) throws Exception {
        try {
            List<E> entities = baseRepository.findAllByCategoriaId(idCategoria);
            if (!entities.isEmpty())
                return entities;
            else
                throw new Exception("No se encontraron entidades con el ID de categoría: " + idCategoria);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E findById(Id id) throws Exception {
        Optional<E> entityOptional = baseRepository.findById(id);
        if (entityOptional.isPresent()) {
            return entityOptional.get();
        } else {
            throw new Exception("No se encontró ninguna entidad con el ID: " + id);
        }
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception {
        try{
            entity = baseRepository.save(entity);
            return entity;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(Id id, E entity) throws Exception {
        try{
            Optional<E> entityOptional = baseRepository.findById(id);
            if (entityOptional.isEmpty()) throw new Exception("No existe ningún registro con el ID indicado");
            return baseRepository.save(entity);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean darDeAlta(Id id) throws Exception {
        try{
            Optional<E> entityOptional = baseRepository.findById(id);
            if (entityOptional.isPresent()) {
                E entity = entityOptional.get();
                if (!entity.isBaja()){
                    throw new Exception("El registro indicado ya está dado de alta.");
                }
                entity.setBaja(false);
                baseRepository.save(entity);
                return true;
            } else {
                throw new Exception("No existe ningún registro con el ID indicado");
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public boolean delete(Id id) throws Exception {
        try{
            Optional<E> entityOptional = baseRepository.findById(id);
            if (entityOptional.isPresent()) {
                E entity = entityOptional.get();
                if (entity.isBaja()){
                    throw new Exception("El registro indicado ya está dado de baja.");
                }
                entity.setBaja(true);
                baseRepository.save(entity);
                return true;
            } else {
                throw new Exception("No existe ningún registro con el ID indicado");
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}