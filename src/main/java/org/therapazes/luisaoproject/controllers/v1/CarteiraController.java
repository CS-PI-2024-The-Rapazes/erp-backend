package org.therapazes.luisaoproject.controllers.v1;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.therapazes.luisaoproject.entities.Carteira;
import org.therapazes.luisaoproject.entities.Produto;
import org.therapazes.luisaoproject.services.CarteiraService;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/carteira")
public class CarteiraController {

    private final CarteiraService carteiraService;

    @GetMapping("/{id}")
    public ResponseEntity<Carteira> getCarteira(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(carteiraService.getCarteiraById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Carteira>> getAllCarteira(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(carteiraService.getAllCarteira(PageRequest.of(page, size)));
    }

    @PostMapping
    public ResponseEntity<Carteira> save(@RequestBody Carteira carteira) {
        return ResponseEntity.ok(carteiraService.save(carteira));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        carteiraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
