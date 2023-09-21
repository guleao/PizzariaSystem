package br.com.uniamerica.pizzaria.pizarria.service;

import br.com.uniamerica.pizzaria.pizarria.dto.PedidoDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.*;
import br.com.uniamerica.pizzaria.pizarria.repository.PedidoRepository;
import br.com.uniamerica.pizzaria.pizarria.repository.PizzaRepository;
import br.com.uniamerica.pizzaria.pizarria.repository.ProdutosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class PedidosService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private ProdutosRepository produtosRepository;

    private static final Logger logger = LoggerFactory.getLogger(PedidosService.class);

    @Transactional(rollbackFor = Exception.class)
    public void validaPedido (final PedidoDTO pedidoDTO) {

        var pedido = new PedidoEntity();
        BeanUtils.copyProperties(pedidoDTO, pedido);

        Assert.isTrue(!pedido.getFuncionario().equals(""), "Funcionário não pode ser nulo");
        Assert.isTrue(!pedido.getUsuario().equals(""), "Usuário não pode ser nulo");

        if (pedido.isPagamentoCartao()){
            pedido.setPagamentoDinheiro(false);
        }else if (pedido.isPagamentoDinheiro()){
            pedido.setPagamentoCartao(false);
        }

        float total = 0;
        float totalProdutos = 0;

        if (pedido.getPizzas() != null && !pedido.getPizzas().isEmpty()){
            for (PizzaEntity pizzas : pedido.getPizzas()){
                Optional <PizzaEntity> pizzaTemp = pizzaRepository.findById(pizzas.getId());
                total += pizzaTemp.get().getPrecoPizza();
            }
        }

        if (pedido.getProdutos() != null && !pedido.getProdutos().isEmpty()){
            for (ProdutosEntity produtos : pedido.getProdutos()){
                Optional <ProdutosEntity> produtosTemp = produtosRepository.findById(produtos.getId());
                totalProdutos += produtosTemp.get().getTotalProduto();
            }
        }

        pedido.setDelivery(pedido.isDelivery());


        pedido.setStatus(Status.ANDAMENTO);

        pedido.setPedidoPreco(total + totalProdutos);

        this.pedidoRepository.save(pedido);


    }


    @Transactional(rollbackFor = Exception.class)
    public void gerarComandaCozinha(PedidoEntity pedido) {
        String pasta = "C:\\Users\\55459\\OneDrive\\Área de Trabalho\\arquivo-txt-java\\";
        String arquivo = pasta + "pedido_cozinha" + pedido.getId() + ".txt";

        try (BufferedWriter txt = new BufferedWriter(new FileWriter(arquivo))) {
            txt.write("Cliente: " + pedido.getUsuario().getNomeUsuario() + "\n");
            if (pedido.isDelivery()) {
                txt.write("Pedido para entrega" + "\n");
            }else {
                txt.write("Pedido para retirada no balcão" + "\n");
            }

            for (PizzaEntity pizza : pedido.getPizzas()){
                txt.write("ID da pizza: " + pizza.getId() + "\n");
                txt.write("Tamanho da pizza: " + pizza.getTamanho() + "\n");
                txt.write("Quantidade: " + pizza.getQuantPizza() + "\n");
                txt.write("Obs:" + pedido.getObservacao()+ "\n");
                for (SaboresEntity sabores : pizza.getSabores()){
                    txt.write("Sabor da pizza: " + sabores.getNomeSabor() + "\n");
                }
            }
            if (pedido.getProdutos() != null && !pedido.getProdutos().isEmpty()){
                for (ProdutosEntity produtos : pedido.getProdutos()){
                    txt.write("Adicionais: " + produtos.getEstoque().getNomeProd() + produtos.getQuantProd() + " Unidades" + "\n");
                }
            }
            txt.write("Status do pedido: " + pedido.getStatus() + "\n");


        }catch (IOException e) {
            logger.error("Erro ao salvar o arquivo: " + e.getMessage(), e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void gerarComandaFinalizado (PedidoEntity pedido){
        String pasta = "C:\\Users\\55459\\OneDrive\\Área de Trabalho\\arquivo-txt-java\\";
        String arquivo = pasta + "pedido_pronto" + pedido.getId() + ".txt";

        try (BufferedWriter txt = new BufferedWriter(new FileWriter(arquivo))) {
            txt.write("Cliente: " + pedido.getUsuario().getNomeUsuario() + "\n");
            txt.write("Telefone: " + pedido.getUsuario().getTelefone() + "\n");
            if (pedido.isDelivery()) {
                for (Endereco endereco : pedido.getUsuario().getEnderecos()) {
                    txt.write("Rua: " + endereco.getRua() + "\n");
                    txt.write("Bairro: " + endereco.getBairro() + "\n");
                    txt.write("Nº da casa: " + endereco.getNumCasa() + "\n");
                }
            }else {
                txt.write("Pedido para retirada no balcão" + "\n");
            }

            for (PizzaEntity pizza : pedido.getPizzas()){
                txt.write("ID da pizza: " + pizza.getId() + "\n");
                txt.write("Tamanho da pizza: " + pizza.getTamanho() + "\n");
                txt.write("Quantidade: " + pizza.getQuantPizza() + "\n");
                txt.write("Obs:" + pedido.getObservacao()+ "\n");
                for (SaboresEntity sabores : pizza.getSabores()){
                    txt.write("Sabor da pizza: " + sabores.getNomeSabor() + "\n");
                    txt.write("Valor da pizza: " + pizza.getPrecoPizza());
                }
            }
            if (pedido.getProdutos() != null && !pedido.getProdutos().isEmpty()){
                for (ProdutosEntity produtos : pedido.getProdutos()){
                    txt.write("Adicionais: " + produtos.getEstoque().getNomeProd() + produtos.getQuantProd() + "Unidades" + "\n");
                    txt.write("Valor dos adicionais: " + produtos.getTotalProduto() + "\n");
                }
            }
            txt.write("Status do pedido: " + pedido.getStatus() + "\n");
            txt.write("Total do pedido: " + pedido.getPedidoPreco() +  "\n");

        }catch (IOException e) {
            logger.error("Erro ao salvar o arquivo: " + e.getMessage(), e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void editaPedido (final PedidoEntity pedido){

        Assert.isTrue(!pedido.getFuncionario().equals(""), "Funcionário não pode ser nulo");
        Assert.isTrue(!pedido.getUsuario().equals(""), "Usuário não pode ser nulo");

        if (pedido.isDelivery()) {
            pedido.setStatus(Status.ACAMINHO);
        }else {
            pedido.setDelivery(false);
            pedido.setStatus(Status.BALCAO);
        }

        this.pedidoRepository.save(pedido);

    }

    @Transactional(rollbackFor = Exception.class)
    public void finalizaPedido (final PedidoEntity pedido){

        PedidoEntity pedidoFinal = this.pedidoRepository.findById(pedido.getId()).orElse(null);

        if (pedido.isEntregue()){
            pedidoFinal.setStatus(Status.ENTREGUE);
        }else {
            pedidoFinal.setStatus(Status.CANCELADO);
        }

    }


    @Transactional(rollbackFor = Exception.class)
    public void deletarPedido (final Long id){

        final PedidoEntity pedido1 = this.pedidoRepository.findById(id).orElse(null);

        if (pedido1 == null || !pedido1.getId().equals(id)){
            throw new RegistroNaoEncontradoException("Não foi possivel encontrar o pedido.");
        }

        this.pedidoRepository.delete(pedido1);
    }

    public Long totalPedidosPorData(LocalDate data) {
        return pedidoRepository.pedidosPorData(data);
    }

    public Long totalPagamentoCartao(LocalDate data) {
        return pedidoRepository.totalPedidosCartao(data);
    }

    public Long totalPagamentoDinheiro(LocalDate data) {
        return pedidoRepository.totalPedidosDinheiro(data);
    }

    public Long totalPedidosDelivery(LocalDate data) {
        return pedidoRepository.pedidosDelivery(data);
    }

    public Long totalPedidosBalcao(LocalDate data) {
        return pedidoRepository.totalPedidosBalcao(data);
    }
    public Long totalPagos(LocalDate data) {
        return pedidoRepository.totalPagos(data);
    }

    public Long totalCancelados(LocalDate data) {
        return pedidoRepository.totalCancelados(data);
    }

    public static class RegistroNaoEncontradoException extends RuntimeException {
        public RegistroNaoEncontradoException(String message) {
            super(message);
        }
    }

    public PedidoEntity findPedidoById(Long pedidoId) {
        Optional<PedidoEntity> optionalPedido = pedidoRepository.findById(pedidoId);

        if (optionalPedido.isPresent()) {
            return optionalPedido.get();
        } else {
            throw new RegistroNaoEncontradoException("Pedido não encontrado"); // Trate o caso em que o pedido não é encontrado
        }
    }
}