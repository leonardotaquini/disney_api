package com.alkemy.disney.entidades;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "genero")
@Getter
@Setter
@SQLDelete(sql = "UPDATE genero SET deleted = true WHERE id=?")
@Where(clause = "estado=false")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nombre;
    private String imagen;
    private Boolean estado = Boolean.FALSE;
    

}
