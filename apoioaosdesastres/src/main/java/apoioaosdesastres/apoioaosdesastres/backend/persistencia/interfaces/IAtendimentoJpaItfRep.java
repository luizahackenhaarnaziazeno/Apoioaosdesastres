package apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Atendimento;

public interface IAtendimentoJpaItfRep extends CrudRepository<Atendimento,Long>{
    List<Atendimento> findAll(); 
    Atendimento findById(long id); 
}




