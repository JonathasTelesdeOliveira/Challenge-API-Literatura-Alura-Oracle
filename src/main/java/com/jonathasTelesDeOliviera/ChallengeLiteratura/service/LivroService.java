package com.jonathasTelesDeOliviera.ChallengeLiteratura.service;


import com.jonathasTelesDeOliviera.ChallengeLiteratura.dto.DadosAutorDTO;
import com.jonathasTelesDeOliviera.ChallengeLiteratura.dto.LivrosDTO;
import com.jonathasTelesDeOliviera.ChallengeLiteratura.model.DadosAutor;
import com.jonathasTelesDeOliviera.ChallengeLiteratura.model.Livro;
import com.jonathasTelesDeOliviera.ChallengeLiteratura.repository.AutorRepository;
import com.jonathasTelesDeOliviera.ChallengeLiteratura.repository.LivrosReposiotry;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    private LivrosReposiotry livrosReposiotry;
    private AutorRepository autorRepository;

    public LivroService(LivrosReposiotry livrosReposiotry, AutorRepository autorRepository) {
        this.livrosReposiotry = livrosReposiotry;
        this.autorRepository = autorRepository;
    }

    @Transactional
    public Livro salvarLivro(LivrosDTO dto) {

        return livrosReposiotry.findByTitle(dto.title()).orElseGet(() -> {

            Livro livro = new Livro(dto);

            for (DadosAutorDTO autorDTO : dto.authors()) {

                DadosAutor autor = autorRepository
                        .findByName(autorDTO.name())
                        .orElseGet(() ->
                                autorRepository.save(
                                        new DadosAutor(autorDTO)
                                )
                        );
                livro.getAutores().add(autor);
            }
            Livro livroSalvo = livrosReposiotry.save(livro);
            System.out.println(dto.title() + " salvo com sucesso");
            return livroSalvo;
        });

    }
    public List<Livro> listarLivrosRegistrados() {
        return livrosReposiotry.findByLivro();
    }

    public List<DadosAutor> ListarAutorResgistrados() {
        return autorRepository.findByAutor();
    }
}







