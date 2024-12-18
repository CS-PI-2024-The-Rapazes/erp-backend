package org.therapazes.luisaoproject.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.therapazes.luisaoproject.enums.ETipoFormasPagamento;

@Data
@Entity
public class FormaPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_forma_pagamento")
    private Integer idFormaPagamento;
    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private ETipoFormasPagamento tipo;
    @ManyToOne
    @JoinColumn(name = "id_carteira")
    private Carteira carteira;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "parcelado")
    private Boolean parcelado;
    @Column(name = "min_parcelas")
    private Integer minParcelas;
    @Column(name = "max_parcelas")
    private Integer maxParcelas;
    @Column(name = "parcelas_sem_juros")
    private Integer parcelasSemJuros;
    @Column(name = "juros")
    private Double juros;
    @Column(name = "chave")
    private String chave;
    @Column(name = "status")
    private Boolean status;
}
