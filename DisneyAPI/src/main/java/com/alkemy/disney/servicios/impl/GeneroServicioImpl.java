package com.alkemy.disney.servicios.impl;

import com.alkemy.disney.dto.GeneroDTO;
import com.alkemy.disney.entidades.Genero;
import com.alkemy.disney.repositorios.GeneroRepositorio;
import com.alkemy.disney.servicios.BaseServicio;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GeneroServicioImpl implements BaseServicio<GeneroDTO>{
    
    @Autowired
    GeneroRepositorio generoRepositorio;
    
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<GeneroDTO> listar() throws Exception {
        try {
            List<Genero> generos = generoRepositorio.findAll();
            List<GeneroDTO> generosDTO = generos
                    .stream()
                    .map( genero -> modelMapper.map(genero, GeneroDTO.class)).collect(Collectors.toList());
            return generosDTO;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public GeneroDTO buscarPorId(Long id) throws Exception {
        Optional<Genero> genero = generoRepositorio.findById(id);
        if(genero.isPresent()){
            return modelMapper.map(genero, GeneroDTO.class);
        }else{
        throw new Exception("No se encontro el genero");
    }
    }

    @Override
    @Transactional
    public GeneroDTO guardar(GeneroDTO generoDTO) throws Exception {
        try {
            Genero genero = modelMapper.map(generoDTO, Genero.class);
            Genero guardado = generoRepositorio.save(genero);
            return modelMapper.map(guardado, GeneroDTO.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public GeneroDTO actualizar(GeneroDTO generoDTO) throws Exception {
        Optional<Genero> genero = generoRepositorio.findById(generoDTO.getId());
        if(genero.isPresent()){
            return guardar(generoDTO);
        }else{
            throw new Exception("El genero indicado no existe.");
        }
    }

    @Override
    @Transactional
    public void eliminar(Long id) throws Exception {
        generoRepositorio.deleteById(id);
    }
    
}
