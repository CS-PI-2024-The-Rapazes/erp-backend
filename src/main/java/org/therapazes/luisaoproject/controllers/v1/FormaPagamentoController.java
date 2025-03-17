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

    @Operation(summary = "Busca uma forma de pagamento por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Forma de pagamento encontrada"),
            @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamento> getFormaPagamento(@PathVariable("id") Integer id) {
        try {
            FormaPagamento formaPagamento = formaPagamentoService.getFormaPagamentoById(id);
            return ResponseEntity.ok(formaPagamento);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Lista todas as formas de pagamento paginadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de formas de pagamento"),
    })
    @GetMapping("/all")
    public ResponseEntity<Page<FormaPagamento>> getAllFormaPagamento(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        Page<FormaPagamento> formasPagamento = formaPagamentoService.getAllFormaPagamento(PageRequest.of(page, size));
        return ResponseEntity.ok(formasPagamento);
    }

    @Operation(summary = "Cria uma nova forma de pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Forma de pagamento criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao criar forma de pagamento")
    })
    @PostMapping
    public ResponseEntity<FormaPagamento> save(@RequestBody FormaPagamento formaPagamento) {
        FormaPagamento savedFormaPagamento = formaPagamentoService.save(formaPagamento);
        return ResponseEntity.ok(savedFormaPagamento);
    }

    @Operation(summary = "Deleta uma forma de pagamento pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Forma de pagamento deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        try {
            formaPagamentoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
