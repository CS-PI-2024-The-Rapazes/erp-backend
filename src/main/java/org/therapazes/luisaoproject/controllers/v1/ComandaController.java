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
import org.therapazes.luisaoproject.services.ComandaService;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/comanda")
@CrossOrigin
public class ComandaController {

    private final ComandaService comandaService;

    /**
     * Retorna uma comanda específica baseado no ID.
     * @param id ID da comanda
     * @return Comanda encontrada
     */
    @GetMapping("/{id}")
    public ResponseEntity<Comanda> getComanda(@PathVariable("id") Integer id) {
        Comanda comanda = comandaService.getComandaById(id);
        return comanda != null ? ResponseEntity.ok(comanda) : ResponseEntity.notFound().build();
    }

    /**
     * Retorna todas as comandas com paginação.
     * @param page Número da página (default 0)
     * @param size Tamanho da página (default 10)
     * @return Página com as comandas
     */
    @Operation(summary = "Busca informações de todas as comandas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comandas encontradas"),
            @ApiResponse(responseCode = "400", description = "Erro ao buscar comandas"),
            @ApiResponse(responseCode = "404", description = "Comandas não encontradas")
    })
    @GetMapping("/all")
    public ResponseEntity<Page<Comanda>> getAllComanda(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Comanda> comandas = comandaService.getAllComanda(pageRequest);
        return ResponseEntity.ok(comandas);
    }

    /**
     * Salva uma nova comanda.
     * @param comanda Dados da comanda a ser salva
     * @return Comanda salva
     */
    @PostMapping
    public ResponseEntity<Comanda> save(@RequestBody Comanda comanda) {
        Comanda savedComanda = comandaService.save(comanda);
        return ResponseEntity.ok(savedComanda);
    }

    /**
     * Deleta uma comanda baseada no ID.
     * @param id ID da comanda a ser deletada
     * @return Status da operação
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        comandaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Atualiza uma comanda existente.
     * @param comanda Dados atualizados da comanda
     * @return Comanda atualizada
     */
    @PutMapping
    public ResponseEntity<Comanda> update(@RequestBody Comanda comanda) {
        Comanda updatedComanda = comandaService.update(comanda);
        return ResponseEntity.ok(updatedComanda);
    }

    /**
     * Adiciona produtos a uma comanda específica.
     * @param request DTO com ID da comanda e lista de IDs dos produtos
     * @return Comanda atualizada com produtos adicionados
     */
    @Operation(summary = "Adiciona os produtos informados na comanda")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comanda atualizada"),
            @ApiResponse(responseCode = "400", description = "Erro ao adicionar produtos"),
            @ApiResponse(responseCode = "404", description = "Comanda ou produto não encontrado")
    })
    @PutMapping("/adicionar-produtos")
    public ResponseEntity<Comanda> adicionarProdutos(@RequestBody AdicionarProdutoRequest request) {
        Comanda updatedComanda = comandaService.adicionarProdutos(request.getIdComanda(), request.getIdProdutos());
        return ResponseEntity.ok(updatedComanda);
    }
}
