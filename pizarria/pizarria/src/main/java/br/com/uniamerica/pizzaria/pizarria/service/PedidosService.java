package br.com.uniamerica.pizzaria.pizarria.service;

import br.com.uniamerica.pizzaria.pizarria.dto.PedidoDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.*;
import br.com.uniamerica.pizzaria.pizarria.repository.PedidoRepository;
import br.com.uniamerica.pizzaria.pizarria.repository.PizzaRepository;
import br.com.uniamerica.pizzaria.pizarria.repository.ProdutosRepository;
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

        if (pedido.isDelivery()){
            pedido.setDelivery(true);
        }else
            pedido.setDelivery(false);


        pedido.setStatus(Status.ANDAMENTO);

        pedido.setPedidoPreco(total + totalProdutos);

        this.pedidoRepository.save(pedido);


    }

    @Transactional(rollbackFor = Exception.class)
    public void finalizaPedido (final PedidoEntity pedido){
        Assert.isTrue(!pedido.getFuncionario().equals(""), "Funcionário não pode ser nulo");
        Assert.isTrue(!pedido.getUsuario().equals(""), "Usuário não pode ser nulo");
    }

    @Transactional(rollbackFor = Exception.class)
    public void salvarPedidoEncerrado(PedidoEntity pedido) {
        String pasta = "C:\\Users\\55459\\OneDrive\\Área de Trabalho\\arquivo-txt-java\\";
        String arquivo = pasta + "pedido_" + pedido.getId() + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            writer.write("Cliente: " + pedido.getUsuario().getNomeUsuario() + "\n");
            writer.write("Telefone: " + pedido.getUsuario().getTelefone() + "\n");
            if (pedido.isDelivery()) {
                for (Endereco endereco : pedido.getUsuario().getEnderecos()) {
                    writer.write("Rua: " + endereco.getRua() + "\n");
                    writer.write("Bairro: " + endereco.getBairro() + "\n");
                    writer.write("Nº da casa: " + endereco.getNumCasa() + "\n");
                }
            }else {
                writer.write("Pedido para retirada no balcão" + "\n");
            }

            for (PizzaEntity pizza : pedido.getPizzas()){
                writer.write("ID da pizza: " + pizza.getId() + "\n");
                writer.write("Tamanho da pizza: " + pizza.getTamanho() + "\n");
                writer.write("Quantidade: " + pizza.getQuantPizza() + "\n");
                writer.write("Obs:" + pedido.getObservacao()+ "\n");
                for (SaboresEntity sabores : pizza.getSabores()){
                    writer.write("Sabor da pizza: " + sabores.getNomeSabor() + "\n");
                }
                writer.write("Status do pedido: " + pedido.getStatus() + "\n");
            }

        }catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage() + "\n");
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
    public void deletarPedido (final Long id){

        final PedidoEntity pedido1 = this.pedidoRepository.findById(id).orElse(null);

        if (pedido1 == null || !pedido1.getId().equals(id)){
            throw new RuntimeException("Não foi possivel encontrar o pedido.");
        }

        this.pedidoRepository.delete(pedido1);
    }

    public Long TotalPedidosPorData(LocalDate data) {
        return pedidoRepository.PedidosPorData(data);
    }

    public Long TotalPagamentoCartao(LocalDate data) {
        return pedidoRepository.TotalPedidosCartao(data);
    }

    public Long TotalPagamentoDinheiro(LocalDate data) {
        return pedidoRepository.TotalPedidosDinheiro(data);
    }

    public Long TotalPedidosDelivery(LocalDate data) {
        return pedidoRepository.PedidosDelivery(data);
    }

    public Long TotalPedidosBalcao(LocalDate data) {
        return pedidoRepository.TotalPedidosBalcao(data);
    }
    public Long TotalPagos(LocalDate data) {
        return pedidoRepository.TotalPagos(data);
    }

    public Long TotalCancelados(LocalDate data) {
        return pedidoRepository.TotalCancelados(data);
    }

}
