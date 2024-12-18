package org.therapazes.luisaoproject.controllers.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.therapazes.luisaoproject.dto.requests.AdicionarProdutoRequest;
import org.therapazes.luisaoproject.entities.Comanda;
import org.therapazes.luisaoproject.entities.Produto;
import org.therapazes.luisaoproject.enums.EComandaStatus;
import org.therapazes.luisaoproject.services.ComandaService;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/comanda")
@CrossOrigin
public class ComandaController {
    private final ComandaService comandaService;

    @GetMapping("/{id}")
    public ResponseEntity<Comanda> getComanda(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(comandaService.getComandaById(id));
    }

    @Operation(summary = "Busca informações de todas as comandas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comandas encontradas"),
            @ApiResponse(responseCode = "400", description = "Erro ao buscar comandas"),
            @ApiResponse(responseCode = "404", description = "Comandas não encontradas")
    })
    @GetMapping("/all")
    public ResponseEntity<Page<Comanda>> getAllComanda(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(comandaService.getAllComanda(PageRequest.of(page, size)));
    }

    @PostMapping
    public ResponseEntity<Comanda> save(@RequestBody Comanda comanda) {
        return ResponseEntity.ok(comandaService.save(comanda));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        comandaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping
    public Comanda update(@RequestBody Comanda comanda) {
        return comandaService.update(comanda);
    }

    @Operation(summary = "Adiciona os produtos informados na comanda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comanda atualizada"),
            @ApiResponse(responseCode = "400", description = "Erro ao adicionar produtos"),
            @ApiResponse(responseCode = "404", description = "Comanda ou produto não encontrado")
    })
    @PutMapping("/adicionar-produtos")
    public ResponseEntity<Comanda> adicionarProdutos(@RequestBody AdicionarProdutoRequest request) {
        return ResponseEntity.ok(
                comandaService.adicionarProdutos(
                        request.getIdComanda(),
                        request.getIdProdutos()));
    }
}
