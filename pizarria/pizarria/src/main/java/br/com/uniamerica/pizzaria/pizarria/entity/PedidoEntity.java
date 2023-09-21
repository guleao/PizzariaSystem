package br.com.uniamerica.pizzaria.pizarria.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Getter @Setter
    @Column(name = "cancelado")
    private boolean cancelado;

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
    private LocalDateTime dataPedido;

    @PrePersist
    private void prePersist(){
        this.dataPedido = LocalDateTime.now();
    }

    public PedidoEntity(){

    }
    public PedidoEntity(Long id, FuncionarioEntity funcionario, UsuarioEntity usuario, String observacao, float pedidoPreco, Status status, boolean delivery, List<PizzaEntity> pizzas, boolean pagamentoCartao, boolean pagamentoDinheiro, List<ProdutosEntity> produtos, LocalDateTime dataPedido) {
        this.id = id;
        this.funcionario = funcionario;
        this.usuario = usuario;
        this.observacao = observacao;
        this.pedidoPreco = pedidoPreco;
        this.status = status;
        this.delivery = delivery;
        this.pizzas = pizzas;
        this.pagamentoCartao = pagamentoCartao;
        this.pagamentoDinheiro = pagamentoDinheiro;
        this.produtos = produtos;
        this.dataPedido = dataPedido;
    }

    public boolean isEntregue() {
        return false;
    }
}
