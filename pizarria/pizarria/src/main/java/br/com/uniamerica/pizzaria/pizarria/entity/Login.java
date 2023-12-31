package br.com.uniamerica.pizzaria.pizarria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "login", schema = "public")
@Getter @Setter
public class Login {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id" , nullable = false, unique = true)
    private Long id;

    @Column (name = "login")
    private String loginUsuario;

    @Column (name = "senha")
    private String senha;

    public Login(){

    }
    public Login(Long id, String loginUsuario, String senha) {
        this.id = id;
        this.loginUsuario = loginUsuario;
        this.senha = senha;
    }
}
