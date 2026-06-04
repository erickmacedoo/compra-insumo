package feira.packages.controller;

import feira.packages.domain.Produto;
import feira.packages.service.ProdutoService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        List<Produto> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<Produto> salvar(@Valid @RequestBody Produto produto) {
        Produto produtoSalvo = produtoService.salvar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produtoAtualizado) {
        return produtoService.findById(id)
            .map(produto -> {
                produto.setNome(produtoAtualizado.getNome());
                produto.setCategoria(produtoAtualizado.getCategoria());
                Produto salvo = produtoService.atualizar(produto);
                return ResponseEntity.ok(salvo);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        produtoService.remover(id);

        return ResponseEntity.noContent().build();
    }

}
