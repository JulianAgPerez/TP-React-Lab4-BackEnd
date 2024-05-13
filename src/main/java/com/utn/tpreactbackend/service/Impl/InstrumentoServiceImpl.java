package com.utn.tpreactbackend.service.Impl;

import com.utn.tpreactbackend.entities.Instrumento;
import com.utn.tpreactbackend.repository.BaseRepository;
import com.utn.tpreactbackend.service.IInstrumentoService;
import org.springframework.stereotype.Service;

@Service
public class InstrumentoServiceImpl extends BaseServiceImpl<Instrumento, Long> implements IInstrumentoService {
    public InstrumentoServiceImpl(BaseRepository<Instrumento,Long> baseRepository){
        super(baseRepository);
    }
}
