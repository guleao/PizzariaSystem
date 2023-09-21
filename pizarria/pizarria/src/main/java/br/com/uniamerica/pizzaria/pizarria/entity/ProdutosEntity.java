package br.com.uniamerica.pizzaria.pizarria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "produtos", schema = "public")
@Getter @Setter
public class ProdutosEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id" , nullable = false, unique = true)
    private Long id;

    @Column (name = "quant_prod")
    private int quantProd;

    @ManyToOne
    @JoinColumn (name = "produto_id")
    private EstoqueProdutos estoque;

    @Column (name = "total_produto")
    private float totalProduto;

    public ProdutosEntity(){

    }
    public ProdutosEntity(Long id, int quantProd, EstoqueProdutos estoque, float totalProduto) {
        this.id = id;
        this.quantProd = quantProd;
        this.estoque = estoque;
        this.totalProduto = totalProduto;
    }
}
