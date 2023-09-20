package br.com.uniamerica.pizzaria.pizarria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Entity
@Table (name = "tb_usuario", schema = "public")
@Getter @Setter
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id" , nullable = false, unique = true)
    private Long id;


    @Column (name = "nome_usuario")
    private String nomeUsuario;

    @OneToOne (fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_login",
            uniqueConstraints =@UniqueConstraint(
                    columnNames = {
                            "usuario_id",
                            "login_id"
                    }
            ),
            joinColumns = @JoinColumn(
                    name = "usuario_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "login_id"
            )
    )
    private Login login;

    @Column (name = "telefone_usuario")
    private String telefone;


//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinTable(name = "usuario_endereco",
//            uniqueConstraints =@UniqueConstraint(
//                    columnNames = {
//                            "endereco_id",
//                            "usuario_id"
//                    }
//            ),
//            joinColumns = @JoinColumn(
//                    name = "usuario_id"
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "endereco_id"
//            )
//    )
//    private Endereco endereco;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "endereco_id")
    private List<Endereco> enderecos;

}


