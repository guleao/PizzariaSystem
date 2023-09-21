package br.com.uniamerica.pizzaria.pizarria;

import br.com.uniamerica.pizzaria.pizarria.controller.EnderecoController;
import br.com.uniamerica.pizzaria.pizarria.controller.LoginController;
import br.com.uniamerica.pizzaria.pizarria.controller.UsuarioController;
import br.com.uniamerica.pizzaria.pizarria.entity.UsuarioEntity;
import br.com.uniamerica.pizzaria.pizarria.repository.EnderecoRepository;
import br.com.uniamerica.pizzaria.pizarria.repository.LoginRepository;
import br.com.uniamerica.pizzaria.pizarria.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
class PizarriaApplicationTests {



    @Autowired
    private final EnderecoController enderecoController = new EnderecoController();

    @MockBean
    EnderecoRepository enderecoRepository;

//    @BeforeEach
//    void inserirDados () {
//        Optional < UsuarioEntity> usuario =Optional.of(new UsuarioEntity(1L, "TesteNome "))
//    }



}