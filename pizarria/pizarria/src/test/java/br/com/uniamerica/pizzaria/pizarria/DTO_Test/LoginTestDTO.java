package br.com.uniamerica.pizzaria.pizarria.DTO_Test;

import br.com.uniamerica.pizzaria.pizarria.dto.LoginDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.Login;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTestDTO {

    LoginDTO login = new LoginDTO();
    LoginDTO login2 = new LoginDTO(1L,"Jorginho","password");

    @Test
    void testLoginUsuario(){
        login.setLoginUsuario("Danielzao");
        Assertions.assertEquals("Danielzao", login.getLoginUsuario());
    }

    @Test
    void testSenha(){
        login.setSenha("senha123");
        Assertions.assertEquals("senha123", login.getSenha());
    }

    @Test
    void testId(){
        login.setId(2L);
        Assertions.assertEquals(2L, login.getId());
    }

    @Test
    void testComparacaoLogin(){
        LoginDTO login3 = new LoginDTO(1L,"Jorginho","password");
        Assertions.assertEquals(login2, login3);
    }

    @Test
    void testConstrutorVazio(){
        LoginDTO login4 = new LoginDTO();
        Assertions.assertEquals(login, login4);
    }
}
