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

    private boolean pagamentoCartao;

    private boolean pagamentoDinheiro;

    private List <ProdutosEntity> produtos;

    private LocalDate dataPedido;
}
