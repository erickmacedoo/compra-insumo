package feira.packages.repository;

import feira.packages.domain.CompraInsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraInsumoRepository extends JpaRepository<CompraInsumo, Long> {
}