package com.alkemy.disney.controladores;

import com.alkemy.disney.dto.PeliculaDTO;
import com.alkemy.disney.servicios.impl.PeliculaServicioImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/peliculas")
public class PeliculaControlador {
    
    @Autowired
    private PeliculaServicioImpl peliculaServicio;
    
    @GetMapping
    public ResponseEntity< List<PeliculaDTO> > listarPeliculas() throws Exception{
        List<PeliculaDTO> peliculas = peliculaServicio.listar();
        return ResponseEntity.ok().body(peliculas);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> buscarPorId(@PathVariable("id") Long id) throws Exception{
        PeliculaDTO pelicula = peliculaServicio.buscarPorId(id);
        return ResponseEntity.ok().body(pelicula);
    }
    
    @PostMapping
    public ResponseEntity<PeliculaDTO> guardar(@RequestBody PeliculaDTO pelicula) throws Exception{
       PeliculaDTO guardada = peliculaServicio.guardar(pelicula);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }
    
    @PutMapping
    public ResponseEntity<PeliculaDTO> editar(@RequestBody PeliculaDTO peliculaDTO) throws Exception{
        PeliculaDTO pelicula = peliculaServicio.actualizar(peliculaDTO);
        return ResponseEntity.status(HttpStatus.OK).body(pelicula);
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")Long id) throws Exception{
        peliculaServicio.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
