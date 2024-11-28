package org.therapazes.luisaoproject.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.therapazes.luisaoproject.entities.Pedido;
import org.therapazes.luisaoproject.repositories.PedidoRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public Pedido findById(Integer id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido update(Pedido pedido) {
        Optional<Pedido> existPedido = pedidoRepository.findById(pedido.getIdPedido());
        if (existPedido.isEmpty()) {
            throw new EntityNotFoundException("Pedido não encontrado");
        }
        Pedido updatedPedido = existPedido.get();

        updatedPedido.setIdPedido(pedido.getIdPedido());
        updatedPedido.setStatus(pedido.getStatus());
        updatedPedido.setProduto(pedido.getProduto());
        updatedPedido.setDescricao(pedido.getDescricao());
        updatedPedido.setItens(pedido.getItens());

        return pedidoRepository.save(updatedPedido);
    }

    public void deleteById(Integer id) {
        if (!pedidoRepository.existsById(id)) {
            throw new EntityNotFoundException("Pedido não encontrado");
        }
        pedidoRepository.deleteById(id);
    }

}
