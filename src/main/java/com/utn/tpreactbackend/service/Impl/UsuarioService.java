package com.utn.tpreactbackend.service.Impl;

import com.utn.tpreactbackend.entities.Usuario;
import com.utn.tpreactbackend.repository.BaseRepository;
import com.utn.tpreactbackend.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends BaseServiceImpl<Usuario,Long>{
    @Autowired
    private UsuarioRepository repository;

    public UsuarioService(BaseRepository<Usuario, Long> baseRepository) {
        super(baseRepository);
    }

    @Transactional
    public Usuario findByNombreUsuario(String nombreUsuario) {
        return repository.findByNombreUsuario(nombreUsuario);
    }
    @Override
    @Transactional
    public Usuario save(Usuario entity) {
        return repository.save(entity);
    }
}
