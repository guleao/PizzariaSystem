package br.com.uniamerica.pizzaria.pizarria.dto;

import br.com.uniamerica.pizzaria.pizarria.entity.UsuarioEntity;
import lombok.Data;

@Data
public class EnderecoDTO {
    private Long id;

    private String rua;

    private String bairro;

    private int numCasa;

    private String cep;

}
