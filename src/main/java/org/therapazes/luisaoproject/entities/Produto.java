package org.therapazes.luisaoproject.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduto")
    private Integer idProduto;

    @Column(name = "nome")
    private String nome;

    @Column(name = "codigo_listagem")
    private Integer codigoListagem;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "detalhes")
    private String detalhes;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "dataCadastro")
    private Date dataCadastro;

    @Column(name = "isAtivo")
    private Boolean status;
}
