package apoioaosdesastres.apoioaosdesastres.backend.persistencia;
import java.util.List;
import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;


import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Evento;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IEventoJpaItfRep;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IEventoRepository;

@Repository
@Primary
public class EventoRepoJpaImpl implements IEventoRepository {
    public IEventoJpaItfRep repository;

    @Autowired
    public EventoRepoJpaImpl(IEventoJpaItfRep repository) {
        this.repository = repository;
    }

    @Override
    public List<Evento> getEventos() {
        List<Evento> eventos = repository.findAll();
        if (eventos.size() == 0) {
            return new LinkedList<Evento>();
        } else {
            return eventos.stream()
                    .toList();
        }
    }

    @Override
    public Evento getEventoCodigo(long codigo) {
       Evento evento = repository.findById(codigo);
        return evento;
    }

   @Override
    public Evento cadastraEvento(Evento evento) {
        return repository.save(evento);
    }
}