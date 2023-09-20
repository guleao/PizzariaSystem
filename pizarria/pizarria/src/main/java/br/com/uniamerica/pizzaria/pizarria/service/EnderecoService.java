package br.com.uniamerica.pizzaria.pizarria.service;

import br.com.uniamerica.pizzaria.pizarria.dto.EnderecoDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.Endereco;
import br.com.uniamerica.pizzaria.pizarria.repository.EnderecoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validaEndereco (EnderecoDTO enderecoDTO){

       var endereco = new Endereco();

        BeanUtils.copyProperties(enderecoDTO,endereco);

        Assert.isTrue(!endereco.getBairro().equals(""), "Bairro não pode ser nulo");
        Assert.isTrue(!endereco.getRua().equals(""), "Rua não pode ser nula");
        Assert.isTrue(!endereco.getCep().equals(""), "CEP não pode ser nulo");

        Assert.isTrue(endereco.getNumCasa() > 0, "Número da casa não pode ser nulo");

        this.enderecoRepository.save(endereco);
    }

    @Transactional(rollbackFor = Exception.class)

    public void editaEndereco (final Long id, final Endereco endereco){

        Assert.isTrue(!endereco.getBairro().equals(""), "Bairro não pode ser nulo");
        Assert.isTrue(!endereco.getRua().equals(""), "Rua não pode ser nula");
        Assert.isTrue(!endereco.getCep().equals(""), "CEP não pode ser nulo");

        Assert.isTrue(endereco.getNumCasa() > 0, "Número da casa não pode ser nulo");

        final Endereco endereco1 = this.enderecoRepository.findById(id).orElse(null);

        if (endereco1 == null || !endereco1.getId().equals(endereco.getId())){
            throw new RuntimeException("Não foi possivel identificar o registro informado.");
        }

        this.enderecoRepository.save(endereco);
    }
    @Transactional(rollbackFor = Exception.class)

    public void deletaEndereco (final Long id){

        final Endereco endereco1 = this.enderecoRepository.findById(id).orElse(null);

        if (endereco1 == null || !endereco1.getId().equals(id)){
            throw new RuntimeException("Não foi possivel encontrar o endereco informado.");
        }

        this.enderecoRepository.delete(endereco1);
    }
}
