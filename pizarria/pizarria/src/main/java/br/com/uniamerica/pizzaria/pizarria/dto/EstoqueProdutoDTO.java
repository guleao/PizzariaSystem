package br.com.uniamerica.pizzaria.pizarria.dto;

import br.com.uniamerica.pizzaria.pizarria.entity.ProdutosEntity;
import lombok.Data;

import java.util.List;

@Data
public class EstoqueProdutoDTO {
    private Long id;

    private float precoProd;

    private String nomeProd;
}
