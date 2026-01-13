package com.jonathasTelesDeOliviera.ChallengeLiteratura.principal;

import com.jonathasTelesDeOliviera.ChallengeLiteratura.dto.LivrosDTO;
import com.jonathasTelesDeOliviera.ChallengeLiteratura.model.Livro;
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
                
                5 - Listar livros em um determinado idioma
                
                0 - SAIR
                
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
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    ListarAutorResgistrados();
                    break;
                case 4:
                    ListarAutorVivosDeterminadoAno();
                    break;
                case 5:
                    listarLivrosDeterminadoIdioma();
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
        LivrosDTO dtos = getLivro(nomeLivro);
        var livroSalvo = livroService.salvarLivro(dtos);
        imprimirLivroSalvo(livroSalvo);
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

    private void imprimirLivroSalvo(Livro livro) {
        System.out.println("\n----------------------------------------------------------------------------------");
        System.out.println(" Título: " + livro.getTitle());
        System.out.print(" Autores: ");
        livro.getAutores()
                .forEach(dadosAutor ->
                        System.out.println("  " + dadosAutor.getName())
                );
        System.out.println(" Idiomas: " + livro.getLanguages());
        System.out.println(" Download: " + livro.getDownload_count());
        System.out.println("----------------------------------------------------------------------------------\n");
    }

    private void listarLivrosRegistrados() {
        var livros = livroService.listarLivrosRegistrados();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado! ");
            return;
        }
        livros.forEach(livro -> {
            System.out.println("\n----------------------------------------------------------------------------------");
            System.out.println(" Título: " + livro.getTitle());
            System.out.print(" Autores: ");
            livro.getAutores()
                    .forEach(dadosAutor ->
                            System.out.println("  " + dadosAutor.getName())
                    );
            System.out.println(" Idiomas: " + livro.getLanguages());
            System.out.println(" Download: " + livro.getDownload_count());
            System.out.println("----------------------------------------------------------------------------------\n");
        });
    }

    private void ListarAutorResgistrados() {
        var autor = livroService.ListarAutorResgistrados();
        if (autor.isEmpty()) {
            System.out.println("Nenhum autor encontrado! ");
            return;
        }
        autor.forEach(a -> {
            System.out.println("\n----------------------------------------------------------------------------------");
            System.out.println(" Autores: " + a.getName());
            System.out.println(" Ano de nascimento: " + a.getBirth_year());
            System.out.println(" Ano de falecimento: " + a.getDeath_year());
            System.out.print(" Livros: ");
            a.getLivros()
                    .forEach(l -> {
                        System.out.println(l.getTitle());
                    });
            System.out.println("----------------------------------------------------------------------------------\n");
        });
    }

    public void ListarAutorVivosDeterminadoAno() {
        System.out.println("Insira o ano que deseja pesquisar: ");
        var deathYear = entrada.nextInt();
        entrada.nextLine();

        var autor = livroService.ListarAutorVivosDeterminadoAno(deathYear);
        if (autor.isEmpty()) {
            System.out.println(" [] ...vazio;\n  Nenhum autor encontrado! ");
            return;
        }

        autor.stream()
                .forEach(a -> {
                    System.out.println("\n----------------------------------------------------------------------------------");
                    System.out.println(" Autores: " + a.getName());
                    System.out.println(" Ano de nascimento: " + a.getBirth_year());
                    System.out.println(" Ano de falecimento: " + a.getDeath_year());
                    System.out.print(" Livros: ");
                    a.getLivros()
                            .forEach(l -> {
                                System.out.println("   " + l.getTitle());
                            });
                    System.out.println("----------------------------------------------------------------------------------\n");
                });
    }
    public void listarLivrosDeterminadoIdioma() {
        var exemplo = """
                Insira o idioma para realizar a busca:
                
                es: espanhol
                en: inglês
                fr: francês
                pt: português
                """;
        System.out.println(exemplo);
        System.out.print("Idioma: ");
        var idioma = entrada.nextLine();
        var livros = livroService.listarLivrosDeterminadoIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado! ");
            return;
        }
       livros.forEach(livro -> {
           System.out.println("\n----------------------------------------------------------------------------------");
           System.out.println(" Título: " + livro.getTitle());
           System.out.print(" Autores: ");
           livro.getAutores()
                   .forEach(dadosAutor ->
                           System.out.println("  " + dadosAutor.getName())
                   );
           System.out.println(" Idiomas: " + livro.getLanguages());
           System.out.println(" Download: " + livro.getDownload_count());
           System.out.println("----------------------------------------------------------------------------------\n");
       });
    }
}



