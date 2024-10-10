package org.therapazes.luisaoproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.therapazes.luisaoproject.repositories.ProdutoRepository;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    public void remove(Integer id) {
        var produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto com ID não encontrado"));
        produtoRepository.delete(produto);
    }
}
