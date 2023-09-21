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

    public PizzaDTO(){

    }
    public PizzaDTO(Long id, List<SaboresEntity> sabores, float precoPizza, int quantPizza, Tamanho tamanho) {
        this.id = id;
        this.sabores = sabores;
        this.precoPizza = precoPizza;
        this.quantPizza = quantPizza;
        this.tamanho = tamanho;
    }
}
