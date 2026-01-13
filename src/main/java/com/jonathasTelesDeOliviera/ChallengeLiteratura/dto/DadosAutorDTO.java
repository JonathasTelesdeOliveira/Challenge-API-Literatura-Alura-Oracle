package com.jonathasTelesDeOliviera.ChallengeLiteratura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutorDTO(
        @JsonProperty("name") String name,
        @JsonProperty("birth_year") Integer birth_year,
        @JsonProperty("death_year") Integer death_year) {
}
