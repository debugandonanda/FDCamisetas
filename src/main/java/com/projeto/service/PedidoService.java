package com.projeto.service;

import com.projeto.model.Pedido;
import com.projeto.repository.PedidoRepository;

public class PedidoService {
    private PedidoRepository pedidoRepository;

    public PedidoService() {
        this.pedidoRepository = new PedidoRepository();
    }

    public void criarPedido(Pedido pedido) {
        // Lógica de negócio relacionada à criação de pedido
        // ...
        pedidoRepository.save(pedido);
    }

    public Pedido consultarPedido(int id) {
        // Lógica de negócio relacionada à consulta de pedido
        // ...
        return pedidoRepository.findById(id);
    }

    public double calcularValorTotal(int id) {
        // Lógica de negócio relacionada ao cálculo do valor total de um pedido
        // ...
        return pedidoRepository.calcularValorTotal(id);
    }
}
