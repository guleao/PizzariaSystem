package br.com.uniamerica.pizzaria.pizarria.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RelatorioDiaDTO {
    private Long totalPedidos;
    private Long totalPedidosCartao;
    private Long totalPedidosDinheiro;
    private Long totalPedidosCancelados;
    private Long totalPedidosDelivery;
    private Long totalPedidosBalcao;
    private Long totalPedidosPagos;

    public String getTipoPagamento() {
        if (totalPedidos > 0) {
            return "Total do dia: ";
        } else if (totalPedidosCartao > 0) {
            return "Cartão: ";
        } else if (totalPedidosDinheiro > 0) {
            return "Dinheiro: ";
        } else if (totalPedidosDelivery > 0) {
            return "Delivery: ";
        } else if (totalPedidosBalcao > 0) {
            return "Balcão: ";
        } else if (totalPedidosPagos > 0) {
            return "Pagos: ";
        } else if (totalPedidosCancelados > 0) {
            return "Cancelados: ";
        } else {
            return "Sem dados";
        }
    }
}