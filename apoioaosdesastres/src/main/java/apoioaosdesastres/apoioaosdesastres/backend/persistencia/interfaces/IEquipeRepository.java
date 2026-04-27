package apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces;

import java.util.List;

import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Equipamento;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Equipe;

public interface IEquipeRepository {
    List<Equipe> getEquipes();
    Equipe getNumero(Long numero);
    Equipe cadastro(Equipe equipe);
    Equipe vincularEquipamento(Long numeroEquipe, Equipamento equipamento);
}
