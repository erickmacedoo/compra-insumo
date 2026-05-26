package feira.packages.service;

import feira.packages.domain.CompraInsumo;
import feira.packages.domain.Insumo;
import feira.packages.domain.StatusCompra;
import feira.packages.dto.CompraInsumoRequest;
import feira.packages.dto.CompraInsumoResponse;
import feira.packages.exception.*;
import feira.packages.repository.CompraInsumoRepository;
import feira.packages.repository.InsumoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CompraInsumoService {

    private final CompraInsumoRepository compraInsumoRepository;
    private final InsumoRepository insumoRepository;

    public CompraInsumoService(CompraInsumoRepository compraInsumoRepository, InsumoRepository insumoRepository) {
        this.compraInsumoRepository = compraInsumoRepository;
        this.insumoRepository = insumoRepository;
    }

    public CompraInsumoResponse cadastrar(CompraInsumoRequest request) {

        Insumo insumo = insumoRepository.findById(request.getInsumoId())
                .orElseThrow(() -> new RegraNegocioException("Insumo não encontrado."));

        CompraInsumo compra = new CompraInsumo(
                request.getDescricao(),
                request.getValorTotal(),
                insumo
        );

        compra = compraInsumoRepository.save(compra);
        return new CompraInsumoResponse(compra);
    }

    public List<CompraInsumoResponse> listarTodas() {
        return compraInsumoRepository.findAll().stream()
                .map(CompraInsumoResponse::new)
                .collect(Collectors.toList());
    }

    public CompraInsumo buscarEntidadePorId(Long id) {
        return compraInsumoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("CompraInsumo com id '" + id + "' não encontrada."));
    }

    public CompraInsumoResponse buscarPorId(Long id) {
        CompraInsumo compra = buscarEntidadePorId(id);
        return new CompraInsumoResponse(compra);
    }

    public CompraInsumoResponse atualizar(Long id, CompraInsumoRequest request) {
        CompraInsumo existente = buscarEntidadePorId(id);

        if (existente.getStatus() != StatusCompra.SOLICITADO) {
            throw new RegraNegocioException("Somente compras com status SOLICITADO podem ser editadas.");
        }

        existente.setDescricao(request.getDescricao());
        existente.setValorTotal(request.getValorTotal());

        existente = compraInsumoRepository.save(existente);
        return new CompraInsumoResponse(existente);
    }

    public void remover(Long id) {
        CompraInsumo compra = buscarEntidadePorId(id);
        if (compra.getStatus() != StatusCompra.SOLICITADO) {
            throw new RegraNegocioException("Somente compras com status SOLICITADO podem ser removidas.");
        }
        compraInsumoRepository.delete(compra);
    }
    
}