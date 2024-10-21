package org.therapazes.luisaoproject.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
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
    private Integer categoria;

    @Column(name = "data_cadastro")
    private Date dataCadastro;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "preco")
    private Double preco;

    @Lob
    @Column(name = "imagem", columnDefinition = "LONGBLOB")
    private byte[] imagem;
}
