package feira.packages.controller;

import feira.packages.dto.CompraInsumoRequest;
import feira.packages.dto.CompraInsumoResponse;
import feira.packages.service.CompraInsumoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras") 
public class CompraInsumoController {
    
    private final CompraInsumoService compraInsumoService;

    public CompraInsumoController(CompraInsumoService compraInsumoService) {
        this.compraInsumoService = compraInsumoService;
    }

    @GetMapping
    public ResponseEntity<List<CompraInsumoResponse>> listarTodas() {
        List<CompraInsumoResponse> compras = compraInsumoService.listarTodas();
        return ResponseEntity.ok(compras);
    }

    @PostMapping
    public ResponseEntity<CompraInsumoResponse> salvar(@RequestBody CompraInsumoRequest request) {
        CompraInsumoResponse response = compraInsumoService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        compraInsumoService.remover(id);
        return ResponseEntity.noContent().build(); // Retorna Status 204 (Sucesso sem conteúdo)
    }
}