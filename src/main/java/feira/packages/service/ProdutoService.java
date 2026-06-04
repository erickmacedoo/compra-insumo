package feira.packages.service;

import org.springframework.stereotype.Service;

import feira.packages.domain.Produto;
import feira.packages.exception.RegraNegocioException;
import feira.packages.repository.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void remover(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RegraNegocioException("Produto com id '" + id + "' não encontrado.");
        }
        produtoRepository.deleteById(id);
    }

}
