package org.therapazes.luisaoproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.therapazes.luisaoproject.enums.EComandaStatus;

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
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EComandaStatus status;

}
