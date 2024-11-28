package org.therapazes.luisaoproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.therapazes.luisaoproject.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
