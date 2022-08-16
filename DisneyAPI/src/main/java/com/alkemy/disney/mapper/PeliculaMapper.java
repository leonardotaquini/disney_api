package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.PeliculaDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.entidades.Pelicula;
import com.alkemy.disney.entidades.Personaje;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PeliculaMapper {
    
    @Autowired
    PersonajeMapper personajeMapper;
    
    public Pelicula peliculaDTO2Pelicula(PeliculaDTO dto, boolean cargarPersonajes){
        
        Pelicula pelicula = new Pelicula();
        if(dto.getId() != null){
            pelicula.setId(dto.getId());
        }
        pelicula.setTitulo(dto.getTitulo());
        pelicula.setImagen(dto.getImagen());
        pelicula.setCalificacion(dto.getCalificacion());
        pelicula.setFechaCreacion(dto.getFechaCreacion());
        if(cargarPersonajes){
           List<PersonajeDTO> personajesDTO = dto.getPersonajes();
           List<Personaje> personajes = personajeMapper.personajeDTO2PersonajeList(personajesDTO, false);
           pelicula.setPersonajes(personajes);
        }
        return pelicula;
    }
    
    public PeliculaDTO pelicula2PeliculaDTO(Pelicula pelicula, boolean cargarPersonajes){
        
        PeliculaDTO dto = new PeliculaDTO();
        dto.setId(pelicula.getId());
        dto.setTitulo(pelicula.getTitulo());
        dto.setImagen(pelicula.getImagen());
        dto.setCalificacion(pelicula.getCalificacion());
        dto.setFechaCreacion(pelicula.getFechaCreacion());
        if(cargarPersonajes){
            List<Personaje> personajes = pelicula.getPersonajes();
            List<PersonajeDTO> dtos = personajeMapper.personaje2PersonajeDTOList(personajes, false);
            dto.setPersonajes(dtos);
        }
        return dto;
    }
    
    public List<Pelicula> peliculaDTO2PeliculaList( List<PeliculaDTO> dtos, boolean cargarPersonajes ){
        List<Pelicula> peliculas = new ArrayList();
        for (PeliculaDTO dto : dtos) {
            peliculas.add(peliculaDTO2Pelicula(dto, cargarPersonajes));
        }
        return peliculas;
    }
    
    public List<PeliculaDTO> pelicula2PeliculaDTOList(List<Pelicula> peliculas, boolean cargarPersonajes){
        List<PeliculaDTO> dtos = new ArrayList();
        for (Pelicula pelicula : peliculas) {
            dtos.add(pelicula2PeliculaDTO(pelicula, cargarPersonajes));
        }
        return dtos;
    }
    
}
