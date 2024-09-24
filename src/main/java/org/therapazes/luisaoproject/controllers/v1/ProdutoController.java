package org.therapazes.luisaoproject.controllers.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.therapazes.luisaoproject.entities.Produto;

import java.util.List;

@RestController
@RequestMapping("v1/produto")
public class ProdutoController {

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
    public ResponseEntity<Produto> update(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(new Produto());
    }
    @DeleteMapping
    public ResponseEntity<Produto> delete(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(new Produto());
    }

}
