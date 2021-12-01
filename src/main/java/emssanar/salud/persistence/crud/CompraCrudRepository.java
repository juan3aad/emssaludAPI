package emssanar.salud.persistence.crud;

import emssanar.salud.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {
     Optional<List<Compra>> findByIdCliente(String idCliente);
}
