package br.com.uniamerica.pizzaria.pizarria.dto;

import lombok.Data;


@Data
public class EstoqueProdutoDTO {
    private Long id;

    private float precoProd;

    private String nomeProd;
}
