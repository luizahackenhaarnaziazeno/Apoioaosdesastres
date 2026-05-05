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
    @Modifying
    @Query("UPDATE Evento e SET e.descricao = :descricao, e.data = :data, e.latitude = :latitude, e.longitude = :longitude WHERE e.codigo = :codigo")
    void cadastraevento(@Param("codigo") long codigo, @Param("descricao") String descricao, @Param("data") String data, @Param("latitude") Double latitude, @Param("longitude") Double longitude);
   
}