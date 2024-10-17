package org.therapazes.luisaoproject.entities;


import jakarta.persistence.*;
import lombok.Data;
import org.therapazes.luisaoproject.enums.ECarteiraStatus;

@Data
@Entity
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carteira")
    private Integer idCarteira;
    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private ECarteiraStatus tipo;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "numero_conta")
    private String numeroConta;
    @Column(name = "status")
    private Boolean status;
}
