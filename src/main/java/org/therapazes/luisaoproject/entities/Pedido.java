package org.therapazes.luisaoproject.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    private String descricao;
    @Column(name = "data_cadastro")
    private Date dataCadastro;
    private boolean status;
}
