package com.alkemy.disney.repositorios;

import com.alkemy.disney.entidades.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepositorio extends JpaRepository<Genero, Long> {
    
}
