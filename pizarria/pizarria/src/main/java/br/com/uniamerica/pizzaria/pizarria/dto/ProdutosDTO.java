package br.com.uniamerica.pizzaria.pizarria.dto;

import br.com.uniamerica.pizzaria.pizarria.entity.EstoqueProdutos;
import br.com.uniamerica.pizzaria.pizarria.entity.ProdutosEntity;
import lombok.Data;

@Data
public class ProdutosDTO {


    private Long id;

    private int quantProd;

    private EstoqueProdutos estoque;

    private float totalProduto;
}
