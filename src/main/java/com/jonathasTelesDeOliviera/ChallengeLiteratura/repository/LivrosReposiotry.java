package com.jonathasTelesDeOliviera.ChallengeLiteratura.repository;

import com.jonathasTelesDeOliviera.ChallengeLiteratura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivrosReposiotry extends JpaRepository<Livro, Long> {

    Optional<Livro> findByTitle(String title);


}

