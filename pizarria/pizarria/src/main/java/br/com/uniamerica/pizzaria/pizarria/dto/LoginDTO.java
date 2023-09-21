package br.com.uniamerica.pizzaria.pizarria.dto;

import lombok.Data;

@Data
public class LoginDTO {

    private Long id;
    private String loginUsuario;
    private String senha;

    public LoginDTO(){

    }
    public LoginDTO(Long id, String loginUsuario, String senha) {
        this.id = id;
        this.loginUsuario = loginUsuario;
        this.senha = senha;
    }
}
