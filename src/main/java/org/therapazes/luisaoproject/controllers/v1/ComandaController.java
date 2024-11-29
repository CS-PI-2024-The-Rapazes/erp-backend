package org.therapazes.luisaoproject.controllers.v1;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.therapazes.luisaoproject.entities.Comanda;
import org.therapazes.luisaoproject.enums.EComandaStatus;
import org.therapazes.luisaoproject.services.ComandaService;

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
}
