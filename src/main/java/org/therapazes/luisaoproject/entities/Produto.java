package org.therapazes.luisaoproject.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

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

    @Column(name = "data_cadastro")
    private Date dataCadastro;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "preco")
    private Double preco;

    @Lob
    @Column(name = "imagem", columnDefinition = "LONGBLOB")
    private byte[] imagem;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ProdutosComanda> produtosComanda;
}
