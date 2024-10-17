package org.therapazes.luisaoproject.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.therapazes.luisaoproject.entities.Produto;
import org.therapazes.luisaoproject.repositories.ProdutoRepository;

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

    public void delete(Integer id) {
        Produto produtoSaved = produtoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto com id " + id + " não encontrado"));
        if(produtoSaved.getStatus().equals(false)){
            produtoRepository.delete(produtoSaved);
        }else {
            throw new IllegalArgumentException("Produto com status diferente de false não pode ser deletado");
        }
    }
}
