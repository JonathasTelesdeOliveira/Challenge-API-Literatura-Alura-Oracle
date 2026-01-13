package com.jonathasTelesDeOliviera.ChallengeLiteratura.principal;

import com.jonathasTelesDeOliviera.ChallengeLiteratura.dto.LivrosDTO;
import com.jonathasTelesDeOliviera.ChallengeLiteratura.model.Result;
import com.jonathasTelesDeOliviera.ChallengeLiteratura.service.ConsumoApiService;
import com.jonathasTelesDeOliviera.ChallengeLiteratura.service.ConvertDados;
import com.jonathasTelesDeOliviera.ChallengeLiteratura.service.LivroService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {

    private final ConsumoApiService consumoApiService;
    private final ConvertDados convertDados;
    public String endereco = "https://gutendex.com/books/";
    private String json;
    Scanner entrada = new Scanner(System.in);
    private LivroService livroService;

    public Principal(ConsumoApiService consumoApiService,
                     ConvertDados convertDados,
                     LivroService livroService) {
        this.consumoApiService = consumoApiService;
        this.convertDados = convertDados;
        this.livroService = livroService;
    }

    public void exibirMenu() {
        var menu = """
                Escolha o número de sua opção
                
                1 - Buscar livro pelo título
                
                2 - listar livros registrados
                
                3 - Listar autores registrados
                
                4 - Listar autores vivos em um determinado ano
                
                0 - SAIR
                
                Insira o idioma para realizar a busca:
                
                es: espanhol
                en: inglês
                fr: francês
                pt: português
                """;

        var opcao = -1;
        while (opcao != 0) {
            System.out.println(menu);
            System.out.println("Digite uma opção válida: ");
            var op = entrada.nextInt();
            entrada.nextLine();
            opcao = op;
            switch (opcao) {
                case 1:
                    buscarLivrosWeb();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    public void buscarLivrosWeb() {
        System.out.println("Digite o nome do livro: ");
        var nomeLivro = entrada.nextLine();
        json = consumoApiService.obterDados(
                endereco + "?search=" + nomeLivro.replace(" ", "+"));
        System.out.println("Dados do livro: " + json);
        LivrosDTO dtos = getLivro(nomeLivro);
        System.out.println(dtos);
        livroService.salvarLivro(dtos);
    }

    public LivrosDTO getLivro(String nomeLivro) {
        Result dados = convertDados.obterDados(json, Result.class);
        LivrosDTO dto = dados.resultsList()
                .stream()
                .filter(livrosDTO -> livrosDTO
                        .title()
                        .toUpperCase()
                        .contains(nomeLivro.toUpperCase()))
                .findFirst().orElse(null);
        return dto;
    }

//    public Livro salvarLivro(LivrosDTO dto) {
//        Livro livro = new Livro(dto);
//
//        for (DadosAutorDTO autorDTO : dto.authors()) {
//            DadosAutor autor = new DadosAutor(autorDTO);
//            livro.getAutores().add(autor);
//        }
//        return livrosReposiotry.save(livro);
//    }
}



