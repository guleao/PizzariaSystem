package br.com.uniamerica.pizzaria.pizarria.DTO_Test;

import br.com.uniamerica.pizzaria.pizarria.dto.SaboresDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SaborDTOTest {

    SaboresDTO sabor = new SaboresDTO(1L, "Calabresa");
    SaboresDTO saborVazio = new SaboresDTO();

    @Test
    void testId(){
        sabor.setId(1L);
        Assertions.assertEquals(1L, sabor.getId());
    }

    @Test
    void testNomeSabor(){
        sabor.setNomeSabor("Portuguesa");
        Assertions.assertEquals("Portuguesa", sabor.getNomeSabor());
    }

    @Test
    void testConstrutorVazio(){
        SaboresDTO saborVazio2 = new SaboresDTO();
        Assertions.assertEquals(saborVazio, saborVazio2);
    }

    @Test
    void testComparacao(){
        SaboresDTO sabor2 = new SaboresDTO(1L, "Calabresa");
        Assertions.assertEquals(sabor, sabor2);
    }
}
