package br.com.uniamerica.pizzaria.pizarria.service;

import br.com.uniamerica.pizzaria.pizarria.dto.PizzaDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.PizzaEntity;
import br.com.uniamerica.pizzaria.pizarria.entity.Tamanho;
import br.com.uniamerica.pizzaria.pizarria.repository.PizzaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validaPizza (final PizzaDTO pizzaDTO){
        var pizza = new PizzaEntity();
        BeanUtils.copyProperties(pizzaDTO,pizza);

        Assert.isTrue(pizza.getSabores()!= null, "Sabor da pizza não pode ser nulo");

        if(pizza.getTamanho() == Tamanho.P){
            Assert.isTrue(pizza.getSabores().size() == 1, "Pizzas do tamanho P não podem conter mais de um sabor");
            pizza.setPrecoPizza(15);
        }else if (pizza.getTamanho() == Tamanho.M) {
            Assert.isTrue(pizza.getSabores().size() >= 1 && pizza.getSabores().size() <=2, "Pizzas do tamanho M não podem conter mais de 02 sabores");
            pizza.setPrecoPizza(25);
        }else if (pizza.getTamanho() == Tamanho.G)
        {
            Assert.isTrue(pizza.getSabores().size() >= 1 && pizza.getSabores().size() <=3, "Pizzas do tamanho G não podem conter mais de 03 sabores");
            pizza.setPrecoPizza(30);
        }else {
            Assert.isTrue(pizza.getSabores().size() >= 1 && pizza.getSabores().size() <=4, "Pizzas do tamanho GG não podem conter mais de 04 sabores");
            pizza.setPrecoPizza(45);
        }

        Assert.isTrue(pizza.getQuantPizza() != 0, "Quantidade não pode ser nula");

        float total;

        total = pizza.getPrecoPizza() * pizza.getQuantPizza();
        pizza.setPrecoPizza(total);

        this.pizzaRepository.save(pizza);
    }


    @Transactional(rollbackFor = Exception.class)
    public void editaPizza (final Long id, PizzaEntity pizza) {

        Assert.isTrue(pizza.getSabores()!= null, "Sabor da pizza não pode ser nulo");

        if(pizza.getTamanho() == Tamanho.P){
            Assert.isTrue(pizza.getSabores().size() == 1, "Pizzas do tamanho P não podem conter mais de um sabor");
            pizza.setPrecoPizza(15);
        }else if (pizza.getTamanho() == Tamanho.M) {
            Assert.isTrue(pizza.getSabores().size() >= 1 && pizza.getSabores().size() <=2, "Pizzas do tamanho M não podem conter mais de 02 sabores");
            pizza.setPrecoPizza(25);
        }else if (pizza.getTamanho() == Tamanho.G)
        {
            Assert.isTrue(pizza.getSabores().size() >= 1 && pizza.getSabores().size() <=3, "Pizzas do tamanho G não podem conter mais de 03 sabores");
            pizza.setPrecoPizza(30);
        }else {
            Assert.isTrue(pizza.getSabores().size() >= 1 && pizza.getSabores().size() <=4, "Pizzas do tamanho GG não podem conter mais de 04 sabores");
            pizza.setPrecoPizza(45);
        }

        Assert.isTrue(pizza.getQuantPizza() != 0, "Quantidade não pode ser nula");

        final PizzaEntity pizza1 = this.pizzaRepository.findById(id).orElse(null);

        if (pizza1 == null || !pizza1.getId().equals(pizza.getId())){
            throw new RuntimeException("Não foi possivel identificar o registro informado.");
        }

        float total;

        total = pizza.getPrecoPizza() * pizza.getQuantPizza();
        pizza.setPrecoPizza(total);

        this.pizzaRepository.save(pizza);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletaPizza (final Long id){

        final PizzaEntity pizza1 = this.pizzaRepository.findById(id).orElse(null);

        if (pizza1 == null || !pizza1.getId().equals(id)){
            throw new RuntimeException("Não foi possivel encontrar a pizza.");
        }
        this.pizzaRepository.delete(pizza1);
    }
}
