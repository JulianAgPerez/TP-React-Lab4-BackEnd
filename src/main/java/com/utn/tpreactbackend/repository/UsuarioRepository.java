package com.utn.tpreactbackend.repository;

import com.utn.tpreactbackend.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {
    Usuario findByNombreUsuario(String nombreUsuario);
}
