package com.jonathasTelesDeOliviera.ChallengeLiteratura.repository;

import com.jonathasTelesDeOliviera.ChallengeLiteratura.model.DadosAutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<DadosAutor, Long> {

    Optional<DadosAutor> findByName(String name);
}
