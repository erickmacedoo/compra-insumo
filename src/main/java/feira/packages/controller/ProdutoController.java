package feira.packages.controller;

import feira.packages.domain.Produto;
import feira.packages.repository.ProdutoRepository;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    
    private final ProdutoRepository produtoService;

    public ProdutoController(ProdutoRepository produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        List<Produto> produtos = produtoService.findAll();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<Produto> salvar(@jakarta.validation.Valid @RequestBody Produto produto) { @Valid
        Produto produtoSalvo = produtoService.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!produtoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produtoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
