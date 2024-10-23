package org.therapazes.luisaoproject.controllers.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.therapazes.luisaoproject.entities.Produto;
import org.therapazes.luisaoproject.services.ProdutoService;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
@RequestMapping("v1/produto")
@CrossOrigin
public class ProdutoController {

    private final ProdutoService produtoService;


    @Operation(summary = "Busca informações de um produto pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "400", description = "Erro ao buscar produto"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProduto(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(produtoService.getProdutoById(id));
    }

    @Operation(summary = "Busca informações de todos os produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos encontrados"),
            @ApiResponse(responseCode = "400", description = "Erro ao buscar produtos"),
            @ApiResponse(responseCode = "404", description = "Produtos não encontrados")
    })
    @GetMapping("/all")
    public ResponseEntity<Page<Produto>> getAllProduto(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(produtoService.getAllProduto(PageRequest.of(page, size)));
    }

    @Operation(summary = "Salva um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto salvo com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao salvar produto"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.save(produto));
    }

    @Operation(summary = "Altera informações de um produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto editado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao editar produto"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PutMapping("/edit")
    public ResponseEntity<Produto> update(@RequestBody Produto produto) {
        Produto updatedProduto = produtoService.updateProduto(produto);
        return updatedProduto != null ? ResponseEntity.ok(updatedProduto) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Altera o status de um produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status alterado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao alterar status"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PatchMapping("/status")
    public ResponseEntity<?> update(@RequestParam("id") Integer id) {
        try {
            return ResponseEntity.ok(produtoService.updateStatus(id));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Remove um produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto removido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao remover produto"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            produtoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

