package org.therapazes.luisaoproject.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.therapazes.luisaoproject.entities.FormaPagamento;
import org.therapazes.luisaoproject.services.FormaPagamentoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/forma-pagamento")
public class FormaPagamentoController {

    private final FormaPagamentoService formaPagamentoService;

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamento> getFormaPagamento(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(formaPagamentoService.getFormaPagamentoById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<FormaPagamento>> getAllFormaPagamento(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(formaPagamentoService.getAllFormaPagamento(PageRequest.of(page, size)));
    }

    @PostMapping
    public ResponseEntity<FormaPagamento> save(@RequestBody FormaPagamento FormaPagamento) {
        return ResponseEntity.ok(formaPagamentoService.save(FormaPagamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        formaPagamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
