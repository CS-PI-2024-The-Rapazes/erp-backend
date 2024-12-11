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

    @OneToMany(mappedBy = "comanda", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProdutosComanda> produtosComanda;

    public void adicionarProdutos(Set<Produto> produtos) {
        for (Produto produto : produtos) {
            ProdutosComanda existente = this.produtosComanda.stream()
                    .filter(pc -> pc.getProduto().equals(produto))
                    .findFirst()
                    .orElse(null);

            if (existente != null) {
                existente.setQuantidade(existente.getQuantidade() + 1);
                existente.calcularTotal();
            } else {
                ProdutosComanda pc = new ProdutosComanda();
                pc.setComanda(this);
                pc.setProduto(produto);
                pc.setQuantidade(1);
                pc.setPrecoUnitario(produto.getPreco());
                pc.calcularTotal();

                this.produtosComanda.add(pc);
            }

            this.valorTotal = this.produtosComanda.stream()
                    .mapToDouble(ProdutosComanda::getTotal)
                    .sum();
        }
    }
}
