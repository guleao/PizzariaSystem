package br.com.uniamerica.pizzaria.pizarria.dto;

import lombok.Data;


@Data
public class EstoqueProdutoDTO {
    private Long id;

    private float precoProd;

    private String nomeProd;

    public EstoqueProdutoDTO(){

    }
    public EstoqueProdutoDTO(Long id, float precoProd, String nomeProd) {
        this.id = id;
        this.precoProd = precoProd;
        this.nomeProd = nomeProd;
    }
}
