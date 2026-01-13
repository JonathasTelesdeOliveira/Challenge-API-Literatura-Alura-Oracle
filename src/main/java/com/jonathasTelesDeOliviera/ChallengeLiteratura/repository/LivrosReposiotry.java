package com.jonathasTelesDeOliviera.ChallengeLiteratura.repository;

import com.jonathasTelesDeOliviera.ChallengeLiteratura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivrosReposiotry extends JpaRepository<Livro, Long> {

    Optional<Livro> findByTitle(String title);

    @Query("""
                SELECT DISTINCT l
                FROM Livro l
                LEFT JOIN FETCH l.autores
                LEFT JOIN FETCH l.languages
                ORDER BY l.title
            """)
    List<Livro> findByLivro();
}

