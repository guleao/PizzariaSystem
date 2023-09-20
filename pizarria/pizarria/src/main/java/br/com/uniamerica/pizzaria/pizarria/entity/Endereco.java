package br.com.uniamerica.pizzaria.pizarria.entity;

import br.com.uniamerica.pizzaria.pizarria.dto.UsuarioDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "endereco", schema = "public")
@Getter @Setter
public class Endereco {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id" , nullable = false, unique = true)
    private Long id;

    @Column (name = "rua")
    private String rua;

    @Column (name = "bairro")
    private String bairro;

    @Column (name = "n_casa")
    @NotNull(message = "Número da casa não pode ser nulo")
    private int numCasa;

    @Column (name = "cep")
    private String cep;

//    @ManyToOne (fetch = FetchType.EAGER)
//    @JoinColumn (name = "usuario_id")
//    private UsuarioEntity usuario;

}
