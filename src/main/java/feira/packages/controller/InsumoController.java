package feira.packages.controller;

import feira.packages.domain.Insumo;
import feira.packages.repository.InsumoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insumos")
public class InsumoController {
    
    private final InsumoRepository insumoRepository;

    public InsumoController(InsumoRepository insumoRepository) {
        this.insumoRepository = insumoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Insumo>> listarTodos() {
        List<Insumo> insumos = insumoRepository.findAll();
        return ResponseEntity.ok(insumos);
    }

    @PostMapping
    public ResponseEntity<Insumo> salvar(@RequestBody Insumo insumo) {
        Insumo insumoSalvo = insumoRepository.save(insumo);
        return ResponseEntity.status(HttpStatus.CREATED).body(insumoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!insumoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        insumoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
