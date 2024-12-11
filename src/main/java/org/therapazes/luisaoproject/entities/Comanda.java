package org.therapazes.luisaoproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.therapazes.luisaoproject.enums.EComandaStatus;

import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comanda")
    private Integer idComanda;

    @Column(name = "descricao")
    private String description;

    @Column(name = "nome")
    private String name;

    @Column(name = "valor_total")
    private double valorTotal;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EComandaStatus status;

    @Column(name = "data_agendamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAgendamento;

    @OneToMany(mappedBy = "comanda")
    private Set<Produto> produtos;

    public void adicionarProdutos(Set<Produto> produtos) {
        // Implemente este método
    }
}
