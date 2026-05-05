package apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces;

import java.util.List;

import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Evento;

public interface IEventoRepository {
    List<Evento> getEventos();
   Evento getEventoCodigo(long codigo);
   boolean cadastraEvento(Evento evento);
}

