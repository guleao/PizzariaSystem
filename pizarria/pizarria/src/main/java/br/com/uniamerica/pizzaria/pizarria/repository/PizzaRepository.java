package br.com.uniamerica.pizzaria.pizarria.repository;

import br.com.uniamerica.pizzaria.pizarria.entity.PizzaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository <PizzaEntity, Long> {
}
