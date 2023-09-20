package br.com.uniamerica.pizzaria.pizarria.repository;

import br.com.uniamerica.pizzaria.pizarria.entity.SaboresEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaboresRepository extends JpaRepository <SaboresEntity, Long> {
    SaboresEntity findByNomeSabor(String nomeSabor);
}
