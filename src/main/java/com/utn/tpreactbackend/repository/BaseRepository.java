package com.utn.tpreactbackend.repository;

import com.utn.tpreactbackend.entities.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseRepository<E extends Base,Id extends Serializable> extends JpaRepository<E,Id> {
    @Query("SELECT i FROM Instrumento i WHERE i.idCategoria = :idCategoria")
    List<E> findAllByCategoriaId(@Param("idCategoria") Id idCategoria);
}
