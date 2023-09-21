package br.com.uniamerica.pizzaria.pizarria.DTO_Test;

import br.com.uniamerica.pizzaria.pizarria.dto.EstoqueProdutoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EstoqueProdutoDTOTest {

    EstoqueProdutoDTO estoqueProduto = new EstoqueProdutoDTO(4L,40,"Pizza Grande");
    EstoqueProdutoDTO estoqueProduto2 = new EstoqueProdutoDTO();

    @Test
    void testColocarPreco(){
        estoqueProduto.setPrecoProd(50);
        Assertions.assertEquals(50,estoqueProduto.getPrecoProd());
    }

    @Test
    void testColocarNome(){
        estoqueProduto.setNomeProd("Pizza Pequena");
        Assertions.assertEquals("Pizza Pequena", estoqueProduto.getNomeProd());
    }

    @Test
    void testIdEstoqueProduto(){
        estoqueProduto.setId(1L);
        Assertions.assertEquals(1L, estoqueProduto.getId());
    }

    @Test
    void testComparacao(){
        EstoqueProdutoDTO estoqueProduto3 = new EstoqueProdutoDTO(4L,40,"Pizza Grande");
        Assertions.assertEquals(estoqueProduto, estoqueProduto3);
    }

    @Test
    void testConstrutorVazio(){
        EstoqueProdutoDTO estoqueProduto4 = new EstoqueProdutoDTO();
        Assertions.assertEquals(estoqueProduto2, estoqueProduto4);
    }


}
