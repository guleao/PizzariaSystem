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

@RestController
@RequestMapping(value = "/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidosService pedidoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final PedidoEntity pedido = this.pedidoRepository.findById(id).orElse(null);
        return pedido == null
                ? ResponseEntity.badRequest().body("Nenhum pedido encontrado para o ID = " + id + ".")
                : ResponseEntity.ok(pedido);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta() {
        return ResponseEntity.ok(this.pedidoRepository.findAll());
    }


//    @GetMapping("/totaldia")
//    public Long getTotalPedidosPorData(@RequestParam("data") LocalDate data) {
//        return pedidoService.getPedidosPorData(data);
//    }

//    localhost:8080/api/pedido/totaldia?data=2023-09-18
    @GetMapping("/totaldia")
    public RelatorioDiaDTO getTotalPedidosPorData(@RequestParam("data") LocalDate data) {
        Long totalPedidos = pedidoService.TotalPedidosPorData(data);
        Long totalPedidosCartao = pedidoService.TotalPagamentoCartao(data);
        Long totalPedidosDinheiro = pedidoService.TotalPagamentoDinheiro(data);
        Long totalPedidosDelivery = pedidoService.TotalPedidosDelivery(data);
        Long totalPedidosBalcao = pedidoService.TotalPedidosBalcao(data);
        Long totalPedidosPagos = pedidoService.TotalPagos(data);
        Long totalPedidosCancelados = pedidoService.TotalCancelados(data);

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

    @GetMapping ("/comanda/{id}")
    public ResponseEntity <?> findById (@PathVariable ("id") Long id){
        try {
            PedidoEntity pedido = pedidoRepository.getById(id);
            pedidoService.salvarPedidoEncerrado(pedido);
            return ResponseEntity.ok("comanda gerada com sucesso");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrarPedido (@RequestBody final PedidoDTO pedido, final Long id) {
        try {
            this.pedidoService.validaPedido(pedido);
            return ResponseEntity.ok("Pedido realizado com sucesso.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarPedido (@PathVariable("id") final Long id, @RequestBody final PedidoEntity pedido) {
        try {
            this.pedidoService.editaPedido(pedido);
            return ResponseEntity.ok("Pedido atualizado com sucesso. ");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPedido (@PathVariable("id") final Long id) {
        try {
            this.pedidoService.deletarPedido(id);
            return ResponseEntity.ok("Pedido excluido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
