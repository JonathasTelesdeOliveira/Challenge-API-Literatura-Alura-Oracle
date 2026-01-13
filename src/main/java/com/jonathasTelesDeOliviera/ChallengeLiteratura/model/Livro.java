package com.jonathasTelesDeOliviera.ChallengeLiteratura.model;

import com.jonathasTelesDeOliviera.ChallengeLiteratura.dto.LivrosDTO;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;

    @ElementCollection
    @CollectionTable(name = "livro_languages", joinColumns = @JoinColumn(name = "livro_id"))
    @Column(name = "language")
    private List<String> languages;

    private Integer download_count;



    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<DadosAutor> autores = new HashSet<>();



    public Livro(LivrosDTO livrosDTO) {
        this.title = livrosDTO.title();
        this.languages = livrosDTO.languages();
        this.download_count = livrosDTO.download_count();
    }

    public Livro() {}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<DadosAutor> getAutores() {
        return autores;
    }

    public void setAutores(Set<DadosAutor> autores) {
        this.autores = autores;
    }

    public Integer getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Integer download_count) {
        this.download_count = download_count;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return
                 title;
    }
}



