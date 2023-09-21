package br.com.uniamerica.pizzaria.pizarria.dto;

import br.com.uniamerica.pizzaria.pizarria.entity.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoDTO {
    private Long id;

    private FuncionarioEntity funcionario;

    private UsuarioEntity usuario;

    private String observacao;

    private float pedidoPreco;

    private Status status;

    private boolean delivery;

    private List<PizzaEntity> pizzas;
    
    private boolean cancelado;

    private boolean pagamentoCartao;

    private boolean pagamentoDinheiro;

    private boolean entrega;

    private List <ProdutosEntity> produtos;

    private LocalDate dataPedido;

    public PedidoDTO(){

    }

    public PedidoDTO(Long id, FuncionarioEntity funcionario, UsuarioEntity usuario, String observacao, float pedidoPreco, Status status, boolean delivery, List<PizzaEntity> pizzas, boolean cancelado, boolean pagamentoCartao, boolean pagamentoDinheiro, boolean entrega, List<ProdutosEntity> produtos, LocalDate dataPedido) {
        this.id = id;
        this.funcionario = funcionario;
        this.usuario = usuario;
        this.observacao = observacao;
        this.pedidoPreco = pedidoPreco;
        this.status = status;
        this.delivery = delivery;
        this.pizzas = pizzas;
        this.cancelado = cancelado;
        this.pagamentoCartao = pagamentoCartao;
        this.pagamentoDinheiro = pagamentoDinheiro;
        this.entrega = entrega;
        this.produtos = produtos;
        this.dataPedido = dataPedido;
    }

    public PedidoDTO(long id, FuncionarioEntity funcionario, UsuarioEntity usuario, String semCebola, int pedidoPreco, Status status, boolean delivery, List<PizzaEntity> pizzaList, boolean cancelado, boolean pagamentoCartao, boolean pagamentoDinheiro, boolean entrega, List<ProdutosEntity> produtoList, LocalDateTime dataManual) {
    }
}
