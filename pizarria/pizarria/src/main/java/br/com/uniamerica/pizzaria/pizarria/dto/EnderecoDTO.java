package br.com.uniamerica.pizzaria.pizarria.dto;

import br.com.uniamerica.pizzaria.pizarria.entity.Endereco;
import lombok.Data;

@Data
public class EnderecoDTO {
    private Long id;

    private String rua;

    private String bairro;

    private int numCasa;

    private String cep;

    private String observ;

    public EnderecoDTO(){

    }

    public EnderecoDTO(Long id, String rua, String bairro, int numCasa, String cep, String observ) {
        this.id = id;
        this.rua = rua;
        this.bairro = bairro;
        this.numCasa = numCasa;
        this.cep = cep;
        this.observ = observ;
    }
}
