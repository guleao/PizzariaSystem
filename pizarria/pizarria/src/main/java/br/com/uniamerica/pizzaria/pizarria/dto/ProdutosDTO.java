package br.com.uniamerica.pizzaria.pizarria.dto;
import br.com.uniamerica.pizzaria.pizarria.entity.EstoqueProdutos;
import lombok.Data;

@Data
public class ProdutosDTO {


    private Long id;

    private int quantProd;

    private EstoqueProdutos estoque;

    private float totalProduto;

    public ProdutosDTO(){

    }

    public ProdutosDTO(Long id, int quantProd, EstoqueProdutos estoque, float totalProduto) {
        this.id = id;
        this.quantProd = quantProd;
        this.estoque = estoque;
        this.totalProduto = totalProduto;
    }
}
