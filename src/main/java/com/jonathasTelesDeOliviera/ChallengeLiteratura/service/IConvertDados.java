package com.jonathasTelesDeOliviera.ChallengeLiteratura.service;

public interface IConvertDados {
    <T> T  obterDados(String json, Class<T> classe);
}
