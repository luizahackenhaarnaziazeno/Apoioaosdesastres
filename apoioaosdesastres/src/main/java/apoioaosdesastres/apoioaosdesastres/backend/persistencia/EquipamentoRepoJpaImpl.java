package apoioaosdesastres.apoioaosdesastres.backend.persistencia;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Equipamento;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IEquipamentoJpaItfRep;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IEquipamentoRepository;

@Repository
@Primary
public class EquipamentoRepoJpaImpl implements IEquipamentoRepository {
    private IEquipamentoJpaItfRep repository;

    @Autowired
    public EquipamentoRepoJpaImpl(IEquipamentoJpaItfRep repository) {
        this.repository = repository;

    }

    @Override
    public List<Equipamento> getEquipamentos() {
        List<Equipamento> equipamento = repository.findAll();
        if (equipamento.size() == 0) {
            return new LinkedList<Equipamento>();
        } else {
            return equipamento.stream()
                    .toList();
        }
    }

    @Override
    public Equipamento getId(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Equipamento cadastro(Equipamento equipamento) {
        return repository.save(equipamento);
    }

    @Override
    public Equipamento atualizarEquipamento(long id, Equipamento novoEquipamento) {
        Equipamento equipamentoExistente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Equipamento não encontrado."));
        equipamentoExistente.setNome(novoEquipamento.getNome());
        equipamentoExistente.setCustoDiario(novoEquipamento.getCustoDiario());
        return repository.save(equipamentoExistente);
    }

}
