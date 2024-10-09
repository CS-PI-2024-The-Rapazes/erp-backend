package org.therapazes.luisaoproject.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.therapazes.luisaoproject.entities.Produto;
import org.therapazes.luisaoproject.services.ProdutoService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("v1/produto")
public class ProdutoController {
    private final ProdutoService produtoService;

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProduto(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(new Produto());
    }
    @GetMapping("/all")
    public ResponseEntity<List<Produto>> getAllProduto() {
        return ResponseEntity.ok(List.of(new Produto()));
    }
    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody Produto produto) {
        return ResponseEntity.ok(new Produto());
    }
    @PutMapping("/edit")
    public ResponseEntity<Produto> update(@RequestBody Produto produto) {
        return ResponseEntity.ok(new Produto());
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
    @DeleteMapping
    public ResponseEntity<Produto> delete(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(new Produto());
    }

}
