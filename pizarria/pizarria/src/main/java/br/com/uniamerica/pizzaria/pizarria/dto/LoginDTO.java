package br.com.uniamerica.pizzaria.pizarria.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private Long id;

    private String login;
    private String senha;
}
