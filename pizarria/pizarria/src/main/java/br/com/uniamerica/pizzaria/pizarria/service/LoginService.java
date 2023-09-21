package br.com.uniamerica.pizzaria.pizarria.service;

import br.com.uniamerica.pizzaria.pizarria.dto.LoginDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.Login;
import br.com.uniamerica.pizzaria.pizarria.repository.LoginRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;


    @Transactional(rollbackFor = Exception.class)
    public void validaLogin (LoginDTO loginDTO){
        var login = new Login();
        BeanUtils.copyProperties(loginDTO,login);

        Assert.isTrue(!login.getLoginUsuario().equals(""), "Login não pode ser nulo");
        Assert.isTrue(!login.getSenha().equals(""), "Senha não pode ser nula");

        Assert.isTrue(login.getLoginUsuario().length() <= 50, "Login excede o limite de caracteres");

        Login login1 = loginRepository.findByLoginUsuario(login.getLoginUsuario());

        Assert.isTrue(login1 == null || login1.equals(login.getLoginUsuario()), "Login Já existente");

        this.loginRepository.save(login);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editaLogin (final Long id,final Login login){

        Assert.isTrue(!login.getLoginUsuario().equals(""), "Login não pode ser nulo");
        Assert.isTrue(!login.getSenha().equals(""), "Senha não pode ser nula");

        Assert.isTrue(login.getLoginUsuario().length() <= 50, "Login excede o limite de caracteres");

        final Login login1 = this.loginRepository.findById(id).orElse(null);

        if (login1 == null || !login1.getId().equals(login.getId())){
            throw new RegistroNaoEncontradoException("Não foi possivel identificar o registro informado.");
        }

        this.loginRepository.save(login);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletaLogin (final Long id){
        final Login login1 = this.loginRepository.findById(id).orElse(null);

        if (login1 == null || !login1.getId().equals(id)){
            throw new RegistroNaoEncontradoException ("Não foi possivel encontrar o login.");
        }

        this.loginRepository.delete(login1);
    }

    public static class RegistroNaoEncontradoException extends RuntimeException {
        public RegistroNaoEncontradoException(String message) {
            super(message);
        }
    }


}