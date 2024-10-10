package org.therapazes.luisaoproject.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.therapazes.luisaoproject.entities.Produto;
import org.therapazes.luisaoproject.services.ProdutoService;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProduto(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(produtoService.getProdutoById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Produto>> getAllProduto(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(produtoService.getAllProduto(PageRequest.of(page, size)));
    }

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

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.save(produto));
    }
}

