package apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import  apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Evento;

public interface IEventoJpaItfRep extends CrudRepository<Evento,Long>{
    List<Evento> findAll(); 
    Evento findById(long codigo);

}