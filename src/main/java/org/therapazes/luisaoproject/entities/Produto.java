package org.therapazes.luisaoproject.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
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
    @Column(name = "data_cadastro")
    private Date dataCadastro;
    @Column(name = "status")
    private Boolean status;
}
