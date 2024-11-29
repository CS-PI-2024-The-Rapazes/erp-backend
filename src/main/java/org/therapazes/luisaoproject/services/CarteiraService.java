package org.therapazes.luisaoproject.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.therapazes.luisaoproject.entities.Carteira;
import org.therapazes.luisaoproject.entities.Produto;
import org.therapazes.luisaoproject.repositories.CarteiraRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarteiraService {

    private final CarteiraRepository carteiraRepository;
    public Carteira getCarteiraById(Integer id) {
        return carteiraRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Carteira com id " + id + " não encontrado"));
    }

    public Page<Carteira> getAllCarteira(Pageable pageable) {
        return carteiraRepository.findAll(pageable);
    }

    public Carteira save(Carteira carteira) {
        return carteiraRepository.save(carteira);
    }

    public void deleteById(Integer id) {
        var carteira = carteiraRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Carteira com id " + id + " não encontrado")).getIdCarteira();
        carteiraRepository.deleteById(carteira);
    }
}
