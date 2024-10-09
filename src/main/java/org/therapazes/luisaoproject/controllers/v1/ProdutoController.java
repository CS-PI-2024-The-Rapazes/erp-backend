package org.therapazes.luisaoproject.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.therapazes.luisaoproject.entities.Produto;
import org.therapazes.luisaoproject.services.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("v1/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

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
        Produto updatedProduto = produtoService.updateProduto(produto);
        return updatedProduto != null ? ResponseEntity.ok(updatedProduto) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/status")
    public ResponseEntity<Produto> updateStatus(@RequestParam("id") Integer id, @RequestParam("status") Boolean status) {
        Produto updatedProduto = produtoService.updateStatus(id, status);
        return updatedProduto != null ? ResponseEntity.ok(updatedProduto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Produto> delete(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(new Produto());
    }

}
