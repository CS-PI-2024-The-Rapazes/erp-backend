package org.therapazes.luisaoproject.dto.requests;

import lombok.Getter;

import java.util.Set;

@Getter
public class AdicionarProdutoRequest {
    private int idComanda;
    private Set<Integer> idProdutos;
}
