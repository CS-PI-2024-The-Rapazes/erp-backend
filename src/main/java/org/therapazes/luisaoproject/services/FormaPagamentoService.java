package org.therapazes.luisaoproject.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.therapazes.luisaoproject.entities.Carteira;
import org.therapazes.luisaoproject.entities.FormaPagamento;
import org.therapazes.luisaoproject.repositories.FormaPagamentoRepository;

@Service
@RequiredArgsConstructor
public class FormaPagamentoService {

    private final FormaPagamentoRepository formaPagamentoRepository;

    public FormaPagamento getFormaPagamentoById(Integer id) {
        return formaPagamentoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("FormaPagamento com id " + id + " não encontrado"));
    }

    public Page<FormaPagamento> getAllFormaPagamento(Pageable pageable) {
        return formaPagamentoRepository.findAll(pageable);
    }

    public FormaPagamento save(FormaPagamento FormaPagamento) {
        return formaPagamentoRepository.save(FormaPagamento);
    }

    public void deleteById(Integer id) {
        var FormaPagamento = formaPagamentoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("FormaPagamento com id " + id + " não encontrado")).getIdFormaPagamento();
        formaPagamentoRepository.deleteById(FormaPagamento);
    }

}
