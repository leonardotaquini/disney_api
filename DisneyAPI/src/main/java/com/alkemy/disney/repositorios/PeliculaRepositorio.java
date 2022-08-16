package com.alkemy.disney.repositorios;

import com.alkemy.disney.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepositorio extends JpaRepository<Pelicula, Long>{
    
}
