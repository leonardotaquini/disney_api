package com.alkemy.disney.repositorios;

import com.alkemy.disney.entidades.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepositorio extends JpaRepository<Personaje, Long> {
    
}
