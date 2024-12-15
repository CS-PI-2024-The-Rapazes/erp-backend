package org.therapazes.luisaoproject.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.therapazes.luisaoproject.entities.Comanda;
import org.therapazes.luisaoproject.entities.Produto;
import org.therapazes.luisaoproject.enums.EComandaStatus;
import org.therapazes.luisaoproject.repositories.ComandaRepository;
import org.therapazes.luisaoproject.repositories.ProdutoRepository;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ComandaService {
    private final ComandaRepository comandaRepository;
    private final ProdutoRepository produtoRepository;

    public Comanda getComandaById(Integer id) {
        return comandaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comanda com id " + id + " não encontrada"));
    }

    public Page<Comanda> getAllComanda(Pageable pageable) {
        return comandaRepository.findAll(pageable);
    }

    public Comanda save(Comanda comanda) {
        comanda.setStatus(EComandaStatus.DISPONIVEL);
        comanda.setValorTotal(0);
        return comandaRepository.save(comanda);
    }

    public void delete(Integer id) {

        Comanda comandaSaved = comandaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Comanda com id " + id + " não encontrada"));
        comandaRepository.delete(comandaSaved);
    }

    public Comanda update(Comanda comanda) {
        Comanda comandaSaved = comandaRepository.findById(comanda.getIdComanda()).orElseThrow(() -> new NoSuchElementException("Comanda com id " + comanda.getIdComanda() + " não encontrada"));
        comandaSaved.setDescricao(comanda.getDescricao());
        comandaSaved.setNome(comanda.getNome());
        comandaSaved.setStatus(comanda.getStatus());
        return comandaRepository.save(comandaSaved);
    }

    public Comanda adicionarProdutos(int comandaId, Set<Integer> idProdutos) {
        var comanda = getComandaById(comandaId);
        var listaProdutos = produtoRepository.findAllByIdProduto(idProdutos);
        comanda.adicionarProdutos(listaProdutos);
        return comandaRepository.save(comanda);
    }
}
