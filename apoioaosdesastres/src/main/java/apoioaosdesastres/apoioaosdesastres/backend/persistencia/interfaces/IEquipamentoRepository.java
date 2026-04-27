package apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces;

import java.util.List;

import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Equipamento;

public interface IEquipamentoRepository {
    List<Equipamento> getEquipamentos();

    Equipamento getId(long id);

    Equipamento cadastro(Equipamento equipamento);

    Equipamento atualizarEquipamento(long id, Equipamento novoEquipamento);



}
