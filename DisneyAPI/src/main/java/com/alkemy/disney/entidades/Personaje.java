package com.alkemy.disney.entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "personaje")
@Getter
@Setter
@SQLDelete(sql = "UPDATE personaje SET estado = true WHERE id=?")
@Where(clause = "estado=false")
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String imagen;
    
    private String nombre;
    
    private Integer edad;
    
    private Double peso;
    
    private String historia;
    
    private Boolean estado = Boolean.FALSE;
    @ManyToMany(mappedBy = "personajes", cascade = CascadeType.ALL)
    private List<Pelicula> peliculas = new ArrayList<>();
    
}
