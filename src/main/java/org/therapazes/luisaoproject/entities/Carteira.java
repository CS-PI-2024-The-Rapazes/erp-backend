package org.therapazes.luisaoproject.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carteira")
    private Integer idCarteira;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "numero_conta")
    private String numeroConta;
    @Column(name = "status")
    private Boolean status;
}
