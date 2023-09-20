package br.com.uniamerica.pizzaria.pizarria.service;

import br.com.uniamerica.pizzaria.pizarria.dto.SaboresDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.SaboresEntity;
import br.com.uniamerica.pizzaria.pizarria.repository.SaboresRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class SaboresService {

    @Autowired
    private SaboresRepository saboresRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validaSabor (final SaboresDTO saboresDTO){
        var sabores = new SaboresEntity();
        BeanUtils.copyProperties(saboresDTO,sabores);


        Assert.isTrue(!sabores.getNomeSabor().equals(""), "Nome do sabor não pode ser nulo");
        Assert.isTrue(sabores.getNomeSabor().length() <= 100, "Nome excede o limite de caracteres");

        SaboresEntity sabores1 = saboresRepository.findByNomeSabor(sabores.getNomeSabor());

        Assert.isTrue(sabores1 == null || sabores1.equals(sabores.getNomeSabor()), "Sabor já existente");

        this.saboresRepository.save(sabores);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editaSabor (final Long id, final SaboresEntity sabores){

        Assert.isTrue(!sabores.getNomeSabor().equals(""), "Nome do sabor não pode ser nulo");
        Assert.isTrue(sabores.getNomeSabor().length() <= 100, "Nome excede o limite de caracteres");

        final SaboresEntity sabores1 = this.saboresRepository.findById(id).orElse(null);

        if (sabores1 == null || !sabores1.getId().equals(sabores.getId())){
            throw new RuntimeException("Não foi possivel identificar o registro informado.");
        }

        this.saboresRepository.save(sabores);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletaSabor (final Long id){
        final SaboresEntity sabores1 = this.saboresRepository.findById(id).orElse(null);

        if (sabores1 == null || !sabores1.getId().equals(id)){
            throw new RuntimeException("Não foi possivel encontrar o sabor selecionado.");
        }

        this.saboresRepository.delete(sabores1);
    }
}
