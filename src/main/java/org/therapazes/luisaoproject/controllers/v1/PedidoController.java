package org.therapazes.luisaoproject.controllers.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.therapazes.luisaoproject.entities.Pedido;
import org.therapazes.luisaoproject.services.PedidoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/pedido")
@CrossOrigin
public class PedidoController {

    private final PedidoService pedidoService;


    @Operation(summary = "Retorna um pedido pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @GetMapping("/{id}")
    public Pedido getPedido(@PathVariable Integer id) {
        return pedidoService.findById(id);
    }


    @Operation(summary = "Cria um novo pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao criar pedido")
    })
    @PostMapping
    public Pedido createPedido(@RequestBody Pedido pedido) {
        return pedidoService.save(pedido);
    }

    @Operation(summary = "Atualiza um pedido existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @PutMapping
    public Pedido updatePedido(@RequestBody Pedido pedido) {
        return pedidoService.update(pedido);
    }

    @Operation(summary = "Deleta um pedido pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable Integer id) {
        pedidoService.deleteById(id);
    }
}
