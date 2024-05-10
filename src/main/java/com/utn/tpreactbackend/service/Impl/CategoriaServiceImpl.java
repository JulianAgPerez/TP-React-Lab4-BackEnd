package com.utn.tpreactbackend.service.Impl;

import com.utn.tpreactbackend.entities.Categoria;
import com.utn.tpreactbackend.repository.BaseRepository;
import com.utn.tpreactbackend.service.ICategoriaService;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl extends BaseServiceImpl<Categoria, Long> implements ICategoriaService {
    public CategoriaServiceImpl(BaseRepository<Categoria, Long> baseRepository){
        super(baseRepository);
    }
}
