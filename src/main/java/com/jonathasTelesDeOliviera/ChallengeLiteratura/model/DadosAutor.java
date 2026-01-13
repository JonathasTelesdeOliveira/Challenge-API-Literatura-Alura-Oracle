package com.jonathasTelesDeOliviera.ChallengeLiteratura.model;

import com.jonathasTelesDeOliviera.ChallengeLiteratura.dto.DadosAutorDTO;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "autor")
public class DadosAutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Integer birth_year;
    private Integer death_year;

    @ManyToMany(mappedBy = "autores")
    private Set<Livro> livros = new HashSet<>();

    public DadosAutor(DadosAutorDTO dadosAutorDTO) {
        this.name = dadosAutorDTO.name();
        this.birth_year = dadosAutorDTO.birth_year();
        this.death_year = dadosAutorDTO.death_year();
    }

    public DadosAutor() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Livro> getLivros() {
        return livros;
    }

    public void setLivros(Set<Livro> livros) {
        this.livros = livros;
    }

    public Integer getDeath_year() {
        return death_year;
    }

    public void setDeath_year(Integer death_year) {
        this.death_year = death_year;
    }

    public Integer getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


