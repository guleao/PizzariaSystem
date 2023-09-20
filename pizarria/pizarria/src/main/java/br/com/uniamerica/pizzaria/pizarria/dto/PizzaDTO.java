package br.com.uniamerica.pizzaria.pizarria.dto;

import br.com.uniamerica.pizzaria.pizarria.entity.SaboresEntity;
import br.com.uniamerica.pizzaria.pizarria.entity.Tamanho;
import lombok.Data;

import java.util.List;

@Data
public class PizzaDTO {
    private Long id;

    private List <SaboresEntity> sabores;

    private float precoPizza;

    private int quantPizza;

    private Tamanho tamanho;
}
