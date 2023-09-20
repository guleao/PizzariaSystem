package br.com.uniamerica.pizzaria.pizarria.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "pedidos", schema = "public")
@Getter @Setter
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id" , nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "funcionario")
    private FuncionarioEntity funcionario;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @Column (name = "observacao")
    private String observacao;


    @Column (name = "pedido_preco")
    private float pedidoPreco;

    @Enumerated(EnumType.STRING)
    @Column (name = "status")
    private Status status;

    private boolean delivery;

//    @OneToMany(fetch = FetchType.LAZY)
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    private List<PizzaEntity> pizzas;

    @Column (name = "pagameto_cartao")
    private boolean pagamentoCartao;

    private boolean pagamentoDinheiro;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "produtos")
    private List<ProdutosEntity> produtos;

    @Column (name = "dataPedido")
    private LocalDate dataPedido;



    @PrePersist
    private void prePersist(){
        this.dataPedido = LocalDate.now();
    }
}
