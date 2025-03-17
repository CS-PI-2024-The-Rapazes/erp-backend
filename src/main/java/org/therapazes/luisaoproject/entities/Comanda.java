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
    private String descricao;

    @Column(name = "nome")
    private String nome;

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

    /**
     * Adiciona produtos à comanda.
     * Se o produto já existe na comanda, a quantidade é aumentada.
     * Se o produto não existe, ele é adicionado à lista de produtos da comanda.
     * @param produtos Produtos a serem adicionados
     */
    public void adicionarProdutos(Set<Produto> produtos) {
        for (Produto produto : produtos) {
            ProdutosComanda pcExistente = encontrarProdutoComanda(produto);

            if (pcExistente != null) {
                atualizarQuantidadeProduto(pcExistente);
            } else {
                adicionarNovoProduto(produto);
            }

            atualizarStatusComanda();
            recalcularValorTotalComanda();
        }
    }

    /**
     * Encontra um produto existente na comanda.
     * @param produto Produto a ser buscado
     * @return ProdutosComanda ou null se não encontrado
     */
    private ProdutosComanda encontrarProdutoComanda(Produto produto) {
        return this.produtosComanda.stream()
                .filter(pc -> pc.getProduto().equals(produto))
                .findFirst()
                .orElse(null);
    }

    /**
     * Atualiza a quantidade do produto existente na comanda.
     * Se a quantidade atingir o limite, lança uma exceção.
     * @param produtoComanda Produto da comanda a ter sua quantidade atualizada
     */
    private void atualizarQuantidadeProduto(ProdutosComanda produtoComanda) {
        if (produtoComanda.getQuantidade() >= 1000) {
            throw new ProdutoComandaException("Quantidade máxima de produtos atingida");
        }
        produtoComanda.setQuantidade(produtoComanda.getQuantidade() + 1);
        produtoComanda.calcularTotal();
    }

    /**
     * Adiciona um novo produto à comanda.
     * @param produto Produto a ser adicionado
     */
    private void adicionarNovoProduto(Produto produto) {
        ProdutosComanda pc = new ProdutosComanda();
        pc.setComanda(this);
        pc.setProduto(produto);
        pc.setQuantidade(1);
        pc.setPrecoUnitario(produto.getPreco());
        pc.calcularTotal();

        this.produtosComanda.add(pc);
    }

    /**
     * Atualiza o status da comanda para 'OCUPADA'.
     */
    private void atualizarStatusComanda() {
        this.setStatus(EComandaStatus.OCUPADA);
    }

    /**
     * Recalcula o valor total da comanda com base nos produtos.
     */
    private void recalcularValorTotalComanda() {
        this.valorTotal = this.produtosComanda.stream()
                .mapToDouble(ProdutosComanda::getTotal)
                .sum();
    }
}

/**
 * Exceção personalizada para quando a quantidade máxima de um produto na comanda for atingida.
 */
class ProdutoComandaException extends RuntimeException {
    public ProdutoComandaException(String message) {
        super(message);
    }
}
