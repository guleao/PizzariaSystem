package br.com.uniamerica.pizzaria.pizarria.repository;

import br.com.uniamerica.pizzaria.pizarria.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository <Login, Long> {
    Login findByLogin (String loginNome);
}
