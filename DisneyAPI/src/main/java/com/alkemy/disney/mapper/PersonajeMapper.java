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
public class PersonajeMapper {
    
    @Autowired
    PeliculaMapper peliculaMapper;
    
    
    public PersonajeDTO personaje2PersonajeDTO(Personaje personaje, boolean cargarPeliculas){
        PersonajeDTO dto = new PersonajeDTO();
        dto.setNombre(personaje.getNombre());
        dto.setEdad(personaje.getEdad());
        dto.setHistoria(personaje.getHistoria());
        dto.setImagen(personaje.getImagen());
        dto.setPeso(personaje.getPeso());
        if(cargarPeliculas){
            List<Pelicula> peliculas = personaje.getPeliculas();
            List<PeliculaDTO> peliculasDTO = peliculaMapper.pelicula2PeliculaDTOList(peliculas, false);
            dto.setPeliculas(peliculasDTO);
        }
        return dto;
    }
    
    public Personaje personajeDTO2Personaje(PersonajeDTO dto, boolean cargarPeliculas){
        Personaje personaje = new Personaje();
        personaje.setNombre(dto.getNombre());
        personaje.setEdad(dto.getEdad());
        personaje.setHistoria(dto.getHistoria());
        personaje.setImagen(dto.getImagen());
        personaje.setPeso(dto.getPeso());
        if(cargarPeliculas){
            List<PeliculaDTO> peliculasDTO = dto.getPeliculas();
            List<Pelicula> peliculas = peliculaMapper.peliculaDTO2PeliculaList(peliculasDTO, false);
            personaje.setPeliculas(peliculas);
        }
        return personaje;
    }
    
    public List<PersonajeDTO> personaje2PersonajeDTOList(List<Personaje> personajes, boolean cargarPeliculas){
        List<PersonajeDTO> dtos = new ArrayList();
        for (Personaje personaje : personajes) {
            dtos.add(personaje2PersonajeDTO(personaje, cargarPeliculas));
        }
        return dtos;
    }
    
    public List<Personaje> personajeDTO2PersonajeList(List<PersonajeDTO> dtos, boolean cargarPeliculas){
        List<Personaje> personajes = new ArrayList<>();
        for (PersonajeDTO dto : dtos) {
            personajes.add(personajeDTO2Personaje(dto, cargarPeliculas));
        }
        return personajes;
    }
    
}
