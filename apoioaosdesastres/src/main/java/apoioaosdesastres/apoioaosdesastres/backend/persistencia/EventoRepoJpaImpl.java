package apoioaosdesastres.apoioaosdesastres.backend.persistencia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;


import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidade.Evento;
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

    /*@Override
    public boolean cadastraEvento(Evento evento) {
       repository.cadastraevento(evento.getCodigo(), evento.getDescricao(), evento.getdata(), evento.getlatitude(), evento.getlongitude());
        return true;
}*/

}