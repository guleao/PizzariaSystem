package br.com.uniamerica.pizzaria.pizarria.DTO_Test;

import br.com.uniamerica.pizzaria.pizarria.dto.FuncionarioDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FuncionarioDTOTest {

    FuncionarioDTO funcionario = new FuncionarioDTO(2L,"Joao");
    FuncionarioDTO funcionario4 = new FuncionarioDTO();

    @Test
    void testNomeFuncionario(){
        funcionario.setNomeFuncionario("Roberto");
        Assertions.assertEquals("Roberto", funcionario.getNomeFuncionario());
    }

    @Test
    void testComparacaoFuncionario(){
        FuncionarioDTO funcionario2 = new FuncionarioDTO(2L, "Joao");
        Assertions.assertEquals(funcionario, funcionario2);
    }

    @Test
    void testConstrutorVazio(){
        FuncionarioDTO funcionario3 = new FuncionarioDTO();
        Assertions.assertEquals(funcionario3, funcionario4);
    }

}
