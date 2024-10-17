package org.therapazes.luisaoproject.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.therapazes.luisaoproject.entities.Comanda;
import org.therapazes.luisaoproject.repositories.ComandaRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ComandaService {
    private final ComandaRepository comandaRepository;

    public Comanda getComandaById(Integer id) {
        return comandaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comanda com id " + id + " não encontrada"));
    }
    public Page<Comanda> getAllComanda(Pageable pageable) {
        return comandaRepository.findAll(pageable);
    }
    public Comanda save(Comanda comanda) {
        return comandaRepository.save(comanda);
    }
    public void delete(Integer id) {

        Comanda comandaSaved = comandaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Comanda com id " + id + " não encontrada"));
        comandaRepository.delete(comandaSaved);
    }
    public Comanda update(Comanda comanda) {
        Comanda comandaSaved = comandaRepository.findById(comanda.getIdComanda()).orElseThrow(() -> new NoSuchElementException("Comanda com id " + comanda.getIdComanda() + " não encontrada"));
        comandaSaved.setDescription(comanda.getDescription());
        comandaSaved.setName(comanda.getName());
        comandaSaved.setStatus(comanda.getStatus());
        return comandaRepository.save(comandaSaved);
    }
}
