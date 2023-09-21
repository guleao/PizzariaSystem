package br.com.uniamerica.pizzaria.pizarria.DTO_Test;

import br.com.uniamerica.pizzaria.pizarria.dto.PizzaDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.SaboresEntity;
import br.com.uniamerica.pizzaria.pizarria.entity.Tamanho;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PizzaDTOTest {

    private List<SaboresEntity> saborList = new ArrayList<>();
    PizzaDTO pizza = new PizzaDTO(1L, saborList,40, 3, Tamanho.P);
    PizzaDTO pizzaVazia = new PizzaDTO();

    @Test
    void testId(){
        pizza.setId(1L);
        Assertions.assertEquals(1L, pizza.getId());
    }

    @Test
    void testQuantidade(){
        pizza.setQuantPizza(5);
        Assertions.assertEquals(5, pizza.getQuantPizza());
    }

    @Test
    void testPreco(){
        pizza.setPrecoPizza(70);
        Assertions.assertEquals(70, pizza.getPrecoPizza());
    }

    @Test
    void testTamanho(){
        pizza.setTamanho(Tamanho.G);
        Assertions.assertEquals(Tamanho.G, pizza.getTamanho());
    }

    @Test
    void testConstrutorVazio(){
        PizzaDTO pizzaVazia2 = new PizzaDTO();
        Assertions.assertEquals(pizzaVazia, pizzaVazia2);
    }

    @Test
    void testComparacao(){
        PizzaDTO pizza2 = new PizzaDTO(1L, saborList,40, 3, Tamanho.P);
        Assertions.assertEquals(pizza, pizza2);
    }

}
