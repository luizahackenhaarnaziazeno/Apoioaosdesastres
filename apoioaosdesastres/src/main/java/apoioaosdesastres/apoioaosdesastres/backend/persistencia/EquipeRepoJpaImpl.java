package apoioaosdesastres.apoioaosdesastres.backend.persistencia;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Equipamento;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Equipe;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IEquipeJpaItfRep;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IEquipeRepository;

@Primary
@Repository
public class EquipeRepoJpaImpl implements IEquipeRepository {
   
    private IEquipeJpaItfRep repository;

    @Autowired
    public void EventoRepoJpaIpl(IEquipeJpaItfRep repository) {
        this.repository = repository;

    }

    @Override
    public List<Equipe> getEquipes() {
        List<Equipe> equipe = repository.findAll();
        if (equipe.size() == 0) {
            return new LinkedList<Equipe>();
        } else {
            return equipe.stream()
                    .toList();
        }
    }

    @Override
    public Equipe getNumero(Long numero) {
        return repository.findById(numero).orElse(null);
    }

    @Override
    public Equipe cadastro(Equipe equipe) {
        return repository.save(equipe);
    }

    @Override
    public Equipe vincularEquipamento(Long numeroEquipe, Equipamento equipamento) {
        Equipe equipe = repository.findById(numeroEquipe)
                .orElseThrow(() -> new IllegalArgumentException("Equipe não encontrada."));
        return repository.save(equipe);
    }

}
