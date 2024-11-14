package org.therapazes.luisaoproject.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_pedido")
    private Integer idItemPedido;
    private Integer quantidade;
    @Column(name = "valor_total")
    private double valorTotal;
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto idProduto;
    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido idPedido;


}


