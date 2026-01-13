# 📚 Challenge Literatura – Catálogo de Livros com Spring Boot

## 📌 Visão Geral

O **Challenge Literatura** é uma aplicação Java desenvolvida com **Spring Boot** que consome dados da API pública **Gutendex** (Projeto Gutenberg), permitindo buscar, persistir e consultar informações sobre livros e autores em um banco de dados relacional.

A aplicação opera via **menu interativo no console**, seguindo boas práticas de arquitetura em camadas (Controller/Service/Repository), uso de **JPA/Hibernate**, **DTOs** e **JPQL** para consultas avançadas.

---

## 🚀 Funcionalidades

A aplicação disponibiliza as seguintes operações:

### 1️⃣ Buscar livro pelo título (API Externa)
- Consulta a API Gutendex pelo título informado
- Converte o JSON retornado em DTO
- Persiste o livro e seus autores no banco de dados
- Evita duplicidade de registros
- Exibe os dados do livro recém-cadastrado

### 2️⃣ Listar livros registrados
- Lista todos os livros salvos no banco
- Exibe:
    - Título
    - Autores
    - Idiomas
    - Número de downloads

### 3️⃣ Listar autores registrados
- Lista todos os autores cadastrados
- Exibe:
    - Nome
    - Ano de nascimento
    - Ano de falecimento
    - Livros associados

### 4️⃣ Listar autores vivos em um determinado ano
- Filtra autores que estavam vivos no ano informado
- Utiliza **JPQL parametrizado**
- Exibe os livros relacionados a cada autor

### 5️⃣ Listar livros por idioma
- Permite buscar livros por idioma (ex: `en`, `pt`, `es`, `fr`)
- Considera idiomas armazenados como `@ElementCollection`
- Exibe os detalhes completos dos livros encontrados

---

## 🧠 Arquitetura do Projeto

O projeto segue uma **arquitetura em camadas**, garantindo organização, manutenção e escalabilidade:

├── principal

│ └── Principal.java # Menu interativo (console)


│
├── service

│ ├── LivroService.java # Regras de negócio

│ ├── ConsumoApiService.java # Consumo da API Gutendex

│ └── ConvertDados.java # Conversão JSON → DTO

│
├── repository

│ ├── LivrosRepository.java

│ └── AutorRepository.java

│
├── model

│ ├── Livro.java # Entidade Livro

│ ├── DadosAutor.java # Entidade Autor


│ └── Result.java # Mapeamento da resposta da API

│
├── dto

│ ├── LivrosDTO.java

│ └── DadosAutorDTO.java        



---

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **JPQL**
- **PostgreSQL / MySQL** (compatível)
- **API Gutendex**
- **Maven**

---

## 🗄️ Modelo de Dados (Resumo)

### Livro
- `id`
- `title`
- `languages` (ElementCollection)
- `download_count`
- Relacionamento **Many-to-Many** com Autor

### Autor
- `id`
- `name`
- `birth_year`
- `death_year`
- Relacionamento **Many-to-Many** com Livro

---

## 📡 API Externa Utilizada

- **Gutendex API**
- Endpoint base:




---

## ▶️ Como Executar o Projeto

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/challenge-literatura.git


mvn spring-boot:run








