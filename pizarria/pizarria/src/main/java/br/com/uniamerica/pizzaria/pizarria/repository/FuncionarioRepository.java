package br.com.uniamerica.pizzaria.pizarria.repository;

import br.com.uniamerica.pizzaria.pizarria.entity.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository <FuncionarioEntity, Long>{
}
