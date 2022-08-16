package com.alkemy.disney.servicios.impl;

import com.alkemy.disney.dto.PeliculaDTO;
import com.alkemy.disney.entidades.Pelicula;
import com.alkemy.disney.mapper.PeliculaMapper;
import com.alkemy.disney.mapper.PersonajeMapper;
import com.alkemy.disney.repositorios.PeliculaRepositorio;
import com.alkemy.disney.servicios.BaseServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PeliculaServicioImpl implements BaseServicio<PeliculaDTO> {

    @Autowired
    PeliculaRepositorio peliculaRepositorio;

    @Autowired
    PersonajeMapper personajeMapper;
    
    @Autowired
    PeliculaMapper peliculaMapper;

    @Override
    public List<PeliculaDTO> listar() throws Exception {
        try {
            List<Pelicula> peliculas = peliculaRepositorio.findAll();
            return peliculaMapper.pelicula2PeliculaDTOList(peliculas, false);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public PeliculaDTO buscarPorId(Long id) throws Exception {
        Pelicula pelicula = peliculaRepositorio.findById(id).orElse(null);
        if (pelicula != null) {
            return peliculaMapper.pelicula2PeliculaDTO(pelicula, false);
        } else {
            throw new Exception("No se encontro la pelicula");
        }
    }

    @Override
    @Transactional
    public PeliculaDTO guardar(PeliculaDTO peliculaDTO) throws Exception {
        try {
            Pelicula pelicula = peliculaMapper.peliculaDTO2Pelicula(peliculaDTO, true);
            Pelicula guardada = peliculaRepositorio.save(pelicula);
            return peliculaMapper.pelicula2PeliculaDTO(pelicula, false);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public PeliculaDTO actualizar(PeliculaDTO peliculaDTO) throws Exception {
        Pelicula pelicula = peliculaRepositorio.findById(peliculaDTO.getId()).orElse(null);
        if (pelicula != null) {
            peliculaDTO.setPersonajes(personajeMapper.personaje2PersonajeDTOList(pelicula.getPersonajes(), false));
            Pelicula actualizada = peliculaMapper.peliculaDTO2Pelicula(peliculaDTO, false);
            peliculaRepositorio.save(actualizada);
            return peliculaMapper.pelicula2PeliculaDTO(actualizada, false);
        } else {
            throw new Exception("La pelicula no existe");
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        peliculaRepositorio.deleteById(id);
    }

}
