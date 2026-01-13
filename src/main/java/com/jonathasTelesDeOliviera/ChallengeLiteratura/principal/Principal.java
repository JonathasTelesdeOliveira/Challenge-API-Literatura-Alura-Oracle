package com.jonathasTelesDeOliviera.ChallengeLiteratura.principal;

import com.jonathasTelesDeOliviera.ChallengeLiteratura.dto.LivrosDTO;
import com.jonathasTelesDeOliviera.ChallengeLiteratura.model.Result;
import com.jonathasTelesDeOliviera.ChallengeLiteratura.service.ConsumoApiService;
import com.jonathasTelesDeOliviera.ChallengeLiteratura.service.ConvertDados;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {

    private ConsumoApiService consumoApiService = new ConsumoApiService();
    private ConvertDados convertDados = new ConvertDados();
    public String endereco = "https://gutendex.com/books/";
    private String json;
    Scanner entrada = new Scanner(System.in);

    public Principal(ConsumoApiService consumoApiService, ConvertDados convertDados) {
        this.consumoApiService = consumoApiService;
        this.convertDados = convertDados;
    }

    public Principal() {
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
        LivrosDTO dto = getLivro(nomeLivro);
        System.out.println(dto);
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
}



