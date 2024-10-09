package org.therapazes.luisaoproject.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.therapazes.luisaoproject.entities.Produto;
import org.therapazes.luisaoproject.repositories.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public Produto getProdutoById(Integer id) {
        return produtoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto com id " + id + " não encontrado"));
    }

    public List<Produto> getAllProduto() {
        return produtoRepository.findAll();
    }

    public Produto save(Produto produto) {
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

    public Produto updateStatus(Integer id, Boolean status) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            produto.setStatus(status);
            return produtoRepository.save(produto);
        }
        return null;
    }
}
