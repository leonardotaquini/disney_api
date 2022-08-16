package com.alkemy.disney.dto;

import java.util.List;
import lombok.Data;

@Data
public class PersonajeDTO {

    private Long id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private Double peso;
    private String historia;
    private List<PeliculaDTO> peliculas;

}
