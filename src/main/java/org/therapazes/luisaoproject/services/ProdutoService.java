package org.therapazes.luisaoproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.therapazes.luisaoproject.entities.Produto;
import org.therapazes.luisaoproject.repositories.ProdutoRepository;

@Service

@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public Produto updateStatus(Integer id) {
        var produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto com ID não encontrado"));
        produto.setIsAtivo(!produto.getIsAtivo());
        return produtoRepository.save(produto);
    }
}
