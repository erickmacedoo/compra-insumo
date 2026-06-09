package feira.packages.controller;

import feira.packages.domain.Categoria;
import feira.packages.dto.ProdutoRequest;
import feira.packages.repository.CategoriaRepository;

import feira.packages.domain.Produto;
import feira.packages.service.ProdutoService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {
    
    private final ProdutoService produtoService;
    private final CategoriaRepository categoriaRepository;

    public ProdutoController(ProdutoService produtoService, CategoriaRepository categoriaRepository) {
        this.produtoService = produtoService;
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        List<Produto> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<Produto> salvar(@Valid @RequestBody ProdutoRequest request) {
        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
        Produto produto = new Produto(request.getNome(), categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvar(produto));
}

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoRequest request) {
        return produtoService.findById(id)
            .map(produto -> {
                Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                        .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
                produto.setNome(request.getNome());
                produto.setCategoria(categoria);
                return ResponseEntity.ok(produtoService.atualizar(produto));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        produtoService.remover(id);

        return ResponseEntity.noContent().build();
    }

}
