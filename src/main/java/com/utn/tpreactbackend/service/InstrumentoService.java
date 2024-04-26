package com.utn.tpreactbackend.service;

import com.utn.tpreactbackend.entities.Instrumento;
import com.utn.tpreactbackend.repository.InstrumentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class InstrumentoService {
    @Autowired
    private InstrumentoRepository instrumentoRepository;

    @Transactional
    public List<Instrumento> findAll() throws Exception{
        try{
            List<Instrumento> entities = instrumentoRepository.findAll();
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Instrumento findById(Long id) throws Exception{
        try{
            Optional<Instrumento> entityOptional = instrumentoRepository.findById(id);
            return entityOptional.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
