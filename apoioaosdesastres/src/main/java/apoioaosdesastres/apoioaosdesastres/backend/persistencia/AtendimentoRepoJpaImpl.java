package apoioaosdesastres.apoioaosdesastres.backend.persistencia;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Atendimento;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IAtendimentoJpaItfRep;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IAtendimentoRepository;


@Repository
@Primary
public class AtendimentoRepoJpaImpl implements IAtendimentoRepository {

    private IAtendimentoJpaItfRep repository;

    @Autowired
    public AtendimentoRepoJpaImpl(IAtendimentoJpaItfRep repository) {
        this.repository = repository;
    }

    @Override
    public List<Atendimento> getAtendimentos() {
        List<Atendimento> atendimentos = repository.findAll();
        if (atendimentos.size() == 0) {
            return new LinkedList<Atendimento>();
        } else {
            return atendimentos.stream()
                    .toList();
        }
    }

    @Override
    public Atendimento getAtendimentoCod(long cod) {
        Atendimento atendimento = repository.findById(cod);
        return atendimento;
    }

    @Override
    public boolean alocaAtendimento(long cod) {
        Atendimento atendimento = repository.findById(cod);
        if (atendimento != null) {
            // preciso de equipe para alocar
            return true;
        }
        return false;
    }

    @Override
    public Atendimento getAtendimentoByStatus(String status) {
        List<Atendimento> atendimentos = repository.findAll();
        for (Atendimento atendimento : atendimentos) {
            if (atendimento.getStatus().equals(status)) {
                return atendimento;
            }
        }
        return null;
    }

    @Override
    public Atendimento getAtualizacaoByStatus(String status) {
        List<Atendimento> atendimentos = repository.findAll();
        for (Atendimento atendimento : atendimentos) {
            if (atendimento.getStatus().equals(status)) {
                return atendimento;
            }
        }
        return null;
    }


    // Mostrar o custo de um atendimento
    @Override
    public Atendimento getCustoAtendimento(long cod) {
        Atendimento atendimento = repository.findById(cod);
        if (atendimento == null) {
            return null;
        }
        // atendimento.calculaCusto();
        return atendimento;
    }

    @Override
    public boolean alterarSituacaoDeAtendimento(long cod, String novaSituacao) {
        Atendimento atendimento = repository.findById(cod);
        if (atendimento == null) {
            return false;
        }
        switch (novaSituacao.toUpperCase()) {
            case "PENDENTE":
            case "EXECUTANDO":
            case "FINALIZADO":
            case "CANCELADO":
                atendimento.setStatus(novaSituacao.toUpperCase());
                repository.save(atendimento);
                return true;
            default:
                return false;
        }
    }
 
    //quando um atendimento é criado ele fica com status pendente
    @Override
    public boolean cadastraAtendimento(Atendimento atendimento) {
        atendimento.setStatus("PENDENTE");
        return repository.save(atendimento) != null;
    }

    //quando há uma equipe é alocada fica no estado EXECUTANDO
    @Override
    public boolean alocaEquipe(long cod) {
        Atendimento atendimento = repository.findById(cod);
        if (atendimento == null) {
            return false;
        }
        // atendimento.getEquipes().forEach(equipe ->atendimento.setStatus("EXECUTANDO"));
        repository.save(atendimento);
        return true;
    }

    //quando o atendimento termina fica no estado FINALIZADO
    @Override
    public boolean finalizaAtendimento(long cod) {
        Atendimento atendimento = repository.findById(cod);
        if (atendimento == null) {
            return false;
        }
        atendimento.setStatus("FINALIZADO");
        repository.save(atendimento);
        return true;
    }

    //a qualquer momento atendimento pode ser CANCELADO
    @Override
    public boolean cancelaAtendimento(long cod) {
        Atendimento atendimento = repository.findById(cod);
        if (atendimento == null) {
            return false;
        }
        atendimento.setStatus("CANCELADO");
        repository.save(atendimento);
        return true;
    }

    @Override
    public boolean confirmaAtendimento(long cod) {
        Atendimento atendimento = repository.findById(cod);
        if (atendimento == null) {
            return false;
        }
        atendimento.setStatus("CONFIRMADO");
        repository.save(atendimento);
        return true;
    }
    
    @Override
    public boolean eventoTemAtendimento(long codigo) {
        List<Atendimento> atendimentos = repository.findAll();
        for (Atendimento atendimento : atendimentos) {
            if (atendimento.getEventoCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

}