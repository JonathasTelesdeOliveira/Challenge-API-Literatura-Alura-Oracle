package com.jonathasTelesDeOliviera.ChallengeLiteratura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivrosDTO(
        @Id
        @JsonProperty("id") long id,
        @JsonProperty("title") String title,
        @JsonProperty("languages") List<String> languages,
        @JsonProperty("authors") List<DadosAutorDTO> authors,
        @JsonProperty("download_count") Integer download_count) {
}



