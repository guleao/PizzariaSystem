package br.com.uniamerica.pizzaria.pizarria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table (name = "estoque_produtos", schema = "public")
@Getter @Setter
public class EstoqueProdutos {
    @Id

    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id" , nullable = false, unique = true)
    private Long id;

    @Column (name = "preco_produto")
    private float precoProd;

    @Column (name = "nome_produto")
    private String nomeProd;
}
