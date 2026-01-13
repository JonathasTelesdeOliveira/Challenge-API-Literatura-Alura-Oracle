package com.jonathasTelesDeOliviera.ChallengeLiteratura.repository;

import com.jonathasTelesDeOliviera.ChallengeLiteratura.model.DadosAutor;
import com.jonathasTelesDeOliviera.ChallengeLiteratura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<DadosAutor, Long> {

    Optional<DadosAutor> findByName(String name);

    @Query("""
                SELECT DISTINCT a
                FROM DadosAutor a
                LEFT JOIN FETCH a.livros
                ORDER BY a.name
            """)
    List<DadosAutor> findByAutor();

}
