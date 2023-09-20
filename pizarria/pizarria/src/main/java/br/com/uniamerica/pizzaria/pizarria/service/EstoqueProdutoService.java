package br.com.uniamerica.pizzaria.pizarria.service;

import br.com.uniamerica.pizzaria.pizarria.dto.EstoqueProdutoDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.EstoqueProdutos;
import br.com.uniamerica.pizzaria.pizarria.entity.ProdutosEntity;
import br.com.uniamerica.pizzaria.pizarria.repository.EstoqueProdutoRepository;
import br.com.uniamerica.pizzaria.pizarria.repository.ProdutosRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class EstoqueProdutoService {
    @Autowired
    private EstoqueProdutoRepository estoqueProdutoRepository;

    @Autowired
    private ProdutosRepository produtosRepository;


    @Transactional(rollbackFor = Exception.class)
    public void validaEstoque (final EstoqueProdutoDTO estoqueProdutoDTO){

        var estoqueProduto = new EstoqueProdutos();
        BeanUtils.copyProperties(estoqueProdutoDTO,estoqueProduto);

        Assert.isTrue(!estoqueProduto.getNomeProd().equals(""), "Nome do produto não pode ser nulo");
        Assert.isTrue(estoqueProduto.getNomeProd().length() <= 100, "Nome excede o limite de caracteres");
        Assert.isTrue(estoqueProduto.getPrecoProd()!= 0, "Preço do produto não pode ser nulo");


        float totalProdutos = 0;



        this.estoqueProdutoRepository.save(estoqueProduto);

    }

    public void editaEstoque (final Long id, EstoqueProdutos estoqueProduto){

        final EstoqueProdutos estoque1 = this.estoqueProdutoRepository.findById(id).orElse(null);

        if (estoque1 == null || !estoque1.getId().equals(id)){
            throw new RuntimeException("Não foi possivel encontrar o ID do estoque informado");
        }

        Assert.isTrue(!estoqueProduto.getNomeProd().equals(""), "Nome do produto não pode ser nulo");
        Assert.isTrue(estoqueProduto.getNomeProd().length() <= 100, "Nome excede o limite de caracteres");
        Assert.isTrue(estoqueProduto.getPrecoProd()!= 0, "Preço do produto não pode ser nulo");

        this.estoqueProdutoRepository.save(estoqueProduto);

    }

    @Transactional(rollbackFor = Exception.class)
    public void deletarProduto (final Long id){

        final EstoqueProdutos estoque1 = this.estoqueProdutoRepository.findById(id).orElse(null);

        if (estoque1 == null || !estoque1.getId().equals(id)){
            throw new RuntimeException("Não foi possivel encontrar o ID do estoque informado");
        }
        this.estoqueProdutoRepository.delete(estoque1);

    }
}
