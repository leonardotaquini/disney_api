package com.alkemy.disney.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class PeliculaDTO {
    
    private Long id;
    private String titulo;
    private String imagen;
    private Integer calificacion;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCreacion;
    private List<PersonajeDTO> personajes;
    
}
