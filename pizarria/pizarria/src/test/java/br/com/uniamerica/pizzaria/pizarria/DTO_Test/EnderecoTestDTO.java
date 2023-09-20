package br.com.uniamerica.pizzaria.pizarria.DTO_Test;

import br.com.uniamerica.pizzaria.pizarria.dto.EnderecoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EnderecoTestDTO {

    EnderecoDTO endereco2 = new EnderecoDTO(1L,"Rua Das Pedras","Porto Meira",1,"85854000","Perto da Technos");
    EnderecoDTO endereco = new EnderecoDTO();
    @Test
    void testNomeRua(){
        endereco.setRua("Rua das Dálias");
        Assertions.assertEquals("Rua das Dálias", endereco.getRua());
    }

    @Test
    void testNomeBairro(){
        endereco.setBairro("Vila Portes");
        Assertions.assertEquals("Vila Portes", endereco.getBairro());
    }

    @Test
    void testNumCasa(){
        endereco.setNumCasa(123);
        Assertions.assertEquals(123, endereco.getNumCasa());
    }

    @Test
    void testEnderecoId(){
        endereco.setId(1L);
        Assertions.assertEquals(1L, endereco.getId());
    }

    @Test
    void testCep(){
        endereco.setCep("908090");
        Assertions.assertEquals("908090", endereco.getCep());
    }

    @Test
    void testComparacaoDosEnderecos(){
        EnderecoDTO endereco3 = new EnderecoDTO(1L,"Rua Das Pedras","Porto Meira",1,"85854000","Perto da Technos");
        Assertions.assertEquals(endereco2, endereco3);
    }

    @Test
    void testConstrutorVazio(){
        EnderecoDTO endereco3 = new EnderecoDTO();
        Assertions.assertEquals(endereco, endereco3);
    }

    @Test
    void testObservacao(){
        endereco.setObserv("Perto do Poste");
        Assertions.assertEquals("Perto do Poste", endereco.getObserv());
    }
}
