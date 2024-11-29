package org.therapazes.luisaoproject.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.therapazes.luisaoproject.entities.Categoria;
import org.therapazes.luisaoproject.repositories.CategoriaRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public Categoria getCategoriaById(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoria com id " + id + " não encontrado"));
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Page<Categoria> getAllCategoria(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }

    public void delete(Integer id) {
        Categoria categoriaSaved = categoriaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Categoria com id " + id + " não encontrado"));
        categoriaRepository.delete(categoriaSaved);
    }

    public Categoria update(Categoria categoria) {
        Categoria categoriaSaved = categoriaRepository.findById(categoria.getIdCategoria()).orElseThrow(() -> new NoSuchElementException("Categoria com id " + categoria.getIdCategoria() + " não encontrado"));
        categoriaSaved.setDescription(categoria.getDescription());
        categoriaSaved.setOrdem(categoria.getOrdem());
        categoriaSaved.setProduto(categoria.getProduto());
        categoriaSaved.setDataAtualizacao(categoria.getDataAtualizacao());
        return categoriaRepository.save(categoriaSaved);
    }
}
