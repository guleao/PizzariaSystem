package br.com.uniamerica.pizzaria.pizarria.DTO_Test;
import br.com.uniamerica.pizzaria.pizarria.dto.PedidoDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.*;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PedidoTestDTO {

    private UsuarioEntity usuario;

    private FuncionarioEntity funcionario;

    private List<PizzaEntity> pizzaList = new ArrayList<>();
    private List<ProdutosEntity> produtoList = new ArrayList<>();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
    String dataString = "2023-09-21T02:03:38.724796100";
    LocalDateTime dataManual = LocalDateTime.parse(dataString, formatter);
    PedidoDTO pedido = new PedidoDTO(1L, funcionario, usuario,"Sem cebola",40, Status.ANDAMENTO,false,pizzaList,true,false,true,true, produtoList, dataManual);
    PedidoDTO pedidoVazio = new PedidoDTO();


    @Test
    void testPreco(){
        pedido.setPedidoPreco(50);
        Assertions.assertEquals(50,pedido.getPedidoPreco());
    }

    /*@Test
    void testData(){
        String dataString2 = "2024-09-21T02:03:38.724796100";
        LocalDateTime dataManual2= LocalDateTime.parse(dataString2, formatter);
        pedido.setDataPedido(LocalDate.from(dataManual2));
        Assertions.assertEquals(dataManual2,pedido.getUsuario());
    }*/

    @Test
    void testCancelamento(){
        pedido.setCancelado(true);
        Assertions.assertTrue(pedido.isCancelado());
    }

    @Test
    void testEntrega(){
        pedido.setEntrega(true);
        Assertions.assertTrue(pedido.isEntrega());
    }

    @Test
    void testDelivery(){
        pedido.setDelivery(true);
        Assertions.assertTrue(pedido.isDelivery());
    }

    @Test
    void testObservacao(){
        pedido.setObservacao("Sem picles");
        Assertions.assertEquals("Sem picles", pedido.getObservacao());
    }

    @Test
    void testStatus(){
        pedido.setStatus(Status.ANDAMENTO);
        Assertions.assertEquals(Status.ANDAMENTO,pedido.getStatus());
    }

    @Test
    void testConstrutorVazio(){
        PedidoDTO pedidoVazio2 = new PedidoDTO();
        Assertions.assertEquals(pedidoVazio, pedidoVazio2);
    }

    @Test
    void testId(){
        pedido.setId(1L);
        Assertions.assertEquals(1L, pedido.getId());
    }

    @Test
    void testComparacao(){
        PedidoDTO pedido2 = new PedidoDTO(1L, funcionario, usuario,"Sem cebola",40, Status.ANDAMENTO,false,pizzaList,true,false,true,true, produtoList, dataManual);
        Assertions.assertEquals(pedido, pedido2);
    }
}
