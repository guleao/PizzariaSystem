package br.com.uniamerica.pizzaria.pizarria.repository;

import br.com.uniamerica.pizzaria.pizarria.entity.EstoqueProdutos;
import br.com.uniamerica.pizzaria.pizarria.entity.ProdutosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueProdutoRepository extends JpaRepository <EstoqueProdutos, Long> {
}
