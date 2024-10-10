package org.therapazes.luisaoproject.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.therapazes.luisaoproject.entities.Produto;
import org.therapazes.luisaoproject.repositories.ProdutoRepository;

@Service
@Data
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public Produto updateStatus(Integer id) {
        var produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto com ID não encontrado"));
        produto.setStatus(!produto.getStatus());
        return produtoRepository.save(produto);
    }
}
