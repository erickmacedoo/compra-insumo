package feira.packages.service;

import feira.packages.domain.Categoria;
import feira.packages.exception.RegraNegocioException;
import feira.packages.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizar(Long id, Categoria categoriaAtualizada) {
        Categoria existente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Categoria com id '" + id + "' não encontrada."));
        existente.setNome(categoriaAtualizada.getNome());
        return categoriaRepository.save(existente);
    }

    public void remover(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RegraNegocioException("Categoria com id '" + id + "' não encontrada.");
        }
        categoriaRepository.deleteById(id);
    }
}