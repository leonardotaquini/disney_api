package com.alkemy.disney.servicios.impl;

import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.entidades.Personaje;
import com.alkemy.disney.repositorios.PersonajeRepositorio;
import com.alkemy.disney.servicios.BaseServicio;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonajeServicioImpl implements BaseServicio<PersonajeDTO>{
    
    @Autowired
    PersonajeRepositorio personajeRepositorio;
    
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<PersonajeDTO> listar() throws Exception {
        try {
            List<Personaje> personajes = personajeRepositorio.findAll();
            List<PersonajeDTO> personajesDTO = personajes
                    .stream()
                    .map( personaje -> modelMapper.map(personaje, PersonajeDTO.class)).collect(Collectors.toList());
            return personajesDTO;
        } catch (Exception e) {
           throw new Exception(e.getMessage());
        }
    }

    @Override
    public PersonajeDTO buscarPorId(Long id) throws Exception {
        Optional<Personaje> personaje = personajeRepositorio.findById(id);
        if(personaje.isPresent()){
            return modelMapper.map(personaje, PersonajeDTO.class);
        }else{
            throw new Exception("No se encontro el personaje");
        }
    }

    @Override
    @Transactional
    public PersonajeDTO guardar(PersonajeDTO personajeDTO) throws Exception {
        try {
            Personaje personaje = modelMapper.map(personajeDTO, Personaje.class);
            Personaje guardado = personajeRepositorio.save(personaje);
            return modelMapper.map(guardado, PersonajeDTO.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public PersonajeDTO actualizar(PersonajeDTO personajeDTO) throws Exception {
        Optional<Personaje> personaje = personajeRepositorio.findById(personajeDTO.getId());
        if(personaje.isPresent()){
            return guardar(personajeDTO);
        }else{
            throw new Exception("El personaje no existe");
        }
    }

    @Override
    @Transactional
    public void eliminar(Long id) throws Exception {
        personajeRepositorio.deleteById(id);
    }
    
}
