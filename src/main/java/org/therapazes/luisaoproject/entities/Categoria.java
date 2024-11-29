package org.therapazes.luisaoproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;
    @Column(name = "descricao")
    private String description;
    @Column(name = "ordem")
    private Integer ordem;
    @Column(name = "produto")
    private Integer produto;
    @Column(name = "data_atualizacao")
    private Date dataAtualizacao;

}
