package br.com.uniamerica.pizzaria.pizarria.dto;

import lombok.Data;
@Data
public class FuncionarioDTO {
    private Long id;

    private String nomeFuncionario;

    public FuncionarioDTO(){

    }

    public FuncionarioDTO(Long id, String nomeFuncionario) {
        this.id = id;
        this.nomeFuncionario = nomeFuncionario;
    }
}
