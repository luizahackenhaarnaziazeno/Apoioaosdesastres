package apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Equipe;


public interface IEquipeJpaItfRep extends CrudRepository<Equipe, Long> {
    List<Equipe> findAll();
}