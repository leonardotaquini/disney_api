package com.alkemy.disney.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "pelicula")
@Getter
@Setter
@SQLDelete(sql = "UPDATE pelicula SET estado = true WHERE id=?")
@Where(clause = "estado=false")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String imagen;
    
    private String titulo;
    
    @Column(name = "fecha_creacion")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaCreacion;
    
    private Integer calificacion;
    
    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE})
    @JoinTable(
            name = "pelicula_personajes",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id")
    )
    private List<Personaje> personajes = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "genero_id", insertable = false, updatable = false)
    private Genero genero;
    
    @Column(name = "genero_id")
    private Long generoId;
    
    private boolean estado = Boolean.FALSE;

}
