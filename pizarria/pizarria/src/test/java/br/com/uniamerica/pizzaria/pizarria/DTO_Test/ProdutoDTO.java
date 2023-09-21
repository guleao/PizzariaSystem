package br.com.uniamerica.pizzaria.pizarria.DTO_Test;

import br.com.uniamerica.pizzaria.pizarria.dto.ProdutosDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.EstoqueProdutos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProdutoDTO {


    EstoqueProdutos estoque = new EstoqueProdutos(1L, 15, "Sprite");
    ProdutosDTO produto = new ProdutosDTO(1L, 40,estoque,50);
    ProdutosDTO produtoVazio = new ProdutosDTO();

    @Test
    void testId(){
        produto.setId(1L);
        Assertions.assertEquals(1L, produto.getId());
    }

    @Test
    void testPreco(){
        produto.setQuantProd(40);
        Assertions.assertEquals(40, produto.getQuantProd());
    }

    @Test
    void testTotalProduto(){
        produto.setTotalProduto(100);
        Assertions.assertEquals(100,produto.getTotalProduto());
    }

    @Test
    void testConstrutorVazio(){
        ProdutosDTO produtoVazio2 = new ProdutosDTO();
        Assertions.assertEquals(produtoVazio, produtoVazio2);
    }

    @Test
    void testComparacao(){
        ProdutosDTO produto2 = new ProdutosDTO(1L, 40,estoque,50);
        Assertions.assertEquals(produto, produto2);
    }
}
