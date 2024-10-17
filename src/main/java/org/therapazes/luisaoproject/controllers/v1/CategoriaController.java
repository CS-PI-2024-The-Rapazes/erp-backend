package org.therapazes.luisaoproject.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.therapazes.luisaoproject.entities.Categoria;
import org.therapazes.luisaoproject.services.CategoriaService;
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/categoria")
@CrossOrigin
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoria(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(categoriaService.getCategoriaById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Categoria>> getAllCategoria(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(categoriaService.getAllCategoria(PageRequest.of(page, size)));
    }

    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.save(categoria));
    }

    @PutMapping
    public Categoria update(@RequestBody Categoria categoria) {
        return categoriaService.update(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
