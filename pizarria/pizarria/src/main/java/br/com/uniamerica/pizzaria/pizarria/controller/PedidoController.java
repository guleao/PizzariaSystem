package br.com.uniamerica.pizzaria.pizarria.controller;

import br.com.uniamerica.pizzaria.pizarria.dto.PedidoDTO;
import br.com.uniamerica.pizzaria.pizarria.dto.RelatorioDiaDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.PedidoEntity;
import br.com.uniamerica.pizzaria.pizarria.repository.PedidoRepository;
import br.com.uniamerica.pizzaria.pizarria.service.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidosService pedidoService;

    @GetMapping("/{id}")
    public ResponseEntity<PedidoEntity> findByIdPath(@PathVariable("id") final Long id) {
        final PedidoEntity pedido = this.pedidoRepository.findById(id).orElse(null);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<PedidoEntity>> listaCompleta() {
        return ResponseEntity.ok(this.pedidoRepository.findAll());
    }



    //    localhost:8080/api/pedido/totaldia?data=2023-09-18
    @GetMapping("/totaldia")
    public RelatorioDiaDTO getTotalPedidosPorData(@RequestParam("data") LocalDate data) {
        Long totalPedidos = pedidoService.totalPedidosPorData(data);
        Long totalPedidosCartao = pedidoService.totalPagamentoCartao(data);
        Long totalPedidosDinheiro = pedidoService.totalPagamentoDinheiro(data);
        Long totalPedidosDelivery = pedidoService.totalPedidosDelivery(data);
        Long totalPedidosBalcao = pedidoService.totalPedidosBalcao(data);
        Long totalPedidosPagos = pedidoService.totalPagos(data);
        Long totalPedidosCancelados = pedidoService.totalCancelados(data);

        RelatorioDiaDTO relatorioDiaDTO = new RelatorioDiaDTO();
        relatorioDiaDTO.setTotalPedidos(totalPedidos);
        relatorioDiaDTO.setTotalPedidosCartao(totalPedidosCartao);
        relatorioDiaDTO.setTotalPedidosDinheiro(totalPedidosDinheiro);
        relatorioDiaDTO.setTotalPedidosDelivery(totalPedidosDelivery);
        relatorioDiaDTO.setTotalPedidosBalcao(totalPedidosBalcao);
        relatorioDiaDTO.setTotalPedidosPagos(totalPedidosPagos);
        relatorioDiaDTO.setTotalPedidosCancelados(totalPedidosCancelados);


        return relatorioDiaDTO;
    }

    @GetMapping ("/comandacozinha/{id}")
    public ResponseEntity <String> comandaCozinha (@PathVariable ("id") Long id){
        try {
            PedidoEntity pedido = pedidoService.findPedidoById(id);
            pedidoService.gerarComandaCozinha(pedido);
            return ResponseEntity.ok("comanda gerada com sucesso");
        }catch (Exception e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @GetMapping ("/comandaentregue/{id}")
    public ResponseEntity <String> comandaEntrega (@PathVariable ("id") Long id){
        try {
            PedidoEntity pedido = pedidoService.findPedidoById(id);
            pedidoService.gerarComandaFinalizado(pedido);
            return ResponseEntity.ok("comanda gerada com sucesso");
        }catch (Exception e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PostMapping
    public ResponseEntity<String> cadastrarPedido (@RequestBody final PedidoDTO pedido, final Long id) {
        try {
            this.pedidoService.validaPedido(pedido);
            return ResponseEntity.ok("Pedido realizado com sucesso.");
        } catch (DataIntegrityViolationException e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarPedido (@PathVariable("id") final Long id, @RequestBody final PedidoEntity pedido) {
        try {
            this.pedidoService.editaPedido(pedido);
            return ResponseEntity.ok("Pedido atualizado com sucesso. ");
        } catch (DataIntegrityViolationException e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PutMapping("/finalizapedido/{id}")
    public ResponseEntity<String> finalizaPedido(@PathVariable ("id") final Long id, @RequestBody final PedidoEntity pedido){
        try {
            final PedidoEntity pedido1 = this.pedidoRepository.findById(id).orElse(null);

            if (pedido1 == null || !pedido1.getId().equals(pedido.getId())){
                return ResponseEntity.internalServerError().body("Nao foi posivel identificar o pedido informado");
            }
            this.pedidoService.finalizaPedido(pedido);
            return ResponseEntity.ok("Pedido finalizado");
        }
        catch (DataIntegrityViolationException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPedido (@PathVariable("id") final Long id) {
        try {
            this.pedidoService.deletarPedido(id);
            return ResponseEntity.ok("Pedido excluido com sucesso.");
        } catch (Exception e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    private String getErrorMessage(Exception e) {
        return "Error: " + e.getMessage();
    }
}