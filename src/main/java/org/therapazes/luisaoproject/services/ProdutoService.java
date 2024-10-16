package org.therapazes.luisaoproject.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.therapazes.luisaoproject.entities.Produto;
import org.therapazes.luisaoproject.repositories.ProdutoRepository;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public Produto getProdutoById(Integer id) {
        return produtoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto com id " + id + " não encontrado"));
    }

    public Page<Produto> getAllProduto(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public Produto save(Produto produto) {
        produto.setDataCadastro(new Date());
        produto.setStatus(true);
        return produtoRepository.save(produto);
    }

    public void deleteById(Integer id) {
        produtoRepository.deleteById(id);
    }

    public Produto updateProduto(Produto produto) {
        Optional<Produto> existingProduto = produtoRepository.findById(produto.getIdProduto());
        if (existingProduto.isPresent()) {
            return produtoRepository.save(produto);
        }
        return null;
    }

    public Produto updateStatus(Integer id) {
        var produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto com ID não encontrado"));
        produto.setStatus(!produto.getStatus());
        return produtoRepository.save(produto);
    }
}
