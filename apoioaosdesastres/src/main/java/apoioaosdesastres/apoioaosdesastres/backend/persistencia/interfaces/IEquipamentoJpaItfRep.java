package apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Equipamento;

public interface IEquipamentoJpaItfRep extends CrudRepository<Equipamento, Long> {
    List<Equipamento> findAll();
}