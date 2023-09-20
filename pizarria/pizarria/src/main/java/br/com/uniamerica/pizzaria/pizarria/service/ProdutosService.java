package br.com.uniamerica.pizzaria.pizarria.service;

import br.com.uniamerica.pizzaria.pizarria.dto.ProdutosDTO;
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
public class ProdutosService {
    @Autowired
    ProdutosRepository produtosRepository;

    @Autowired
    EstoqueProdutoRepository estoqueProdutoRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validarProduto (final ProdutosDTO produtosDTO){

        var produtos = new ProdutosEntity();
        BeanUtils.copyProperties(produtosDTO,produtos);


        Assert.isTrue(produtos.getQuantProd()!=0, "Quantidade não pode ser nula");

        EstoqueProdutos estoque = produtos.getEstoque();
        float total = 0;

        Optional <EstoqueProdutos> estoqueTemp = estoqueProdutoRepository.findById(estoque.getId());
        total += estoqueTemp.get().getPrecoProd() * produtos.getQuantProd();

        produtos.setTotalProduto(total);

        this.produtosRepository.save(produtos);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editarProduto(final Long id, ProdutosEntity produtos) {

        Assert.isTrue(produtos.getQuantProd()!=0, "Quantidade não pode ser nula");


        final ProdutosEntity produtos1 = this.produtosRepository.findById(id).orElse(null);

        if (produtos1 == null || !produtos1.getId().equals(produtos1.getId())){
            throw new RuntimeException("Não foi possivel identificar o registro informado.");
        }

        this.produtosRepository.save(produtos);
    }
    @Transactional(rollbackFor = Exception.class)
    public void deletarProduto(final Long id){

        final ProdutosEntity produto1 = this.produtosRepository.findById(id).orElse(null);

        if (produto1 == null || !produto1.getId().equals(id)){
            throw new RuntimeException("Não foi possivel identificar o produto informado.");
        }
        this.produtosRepository.delete(produto1);
    }
}
