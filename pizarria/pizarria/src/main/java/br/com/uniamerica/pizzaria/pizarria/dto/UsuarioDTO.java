package br.com.uniamerica.pizzaria.pizarria.dto;

import br.com.uniamerica.pizzaria.pizarria.entity.Endereco;
import br.com.uniamerica.pizzaria.pizarria.entity.Login;
import lombok.Data;

import java.util.List;

@Data
public class UsuarioDTO {
    private Long id;

    private String nomeUsuario;

    private Login login;

    private String telefone;

    private List<Endereco> enderecos;
}
