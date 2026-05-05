package apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces;



import java.util.List;

import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Atendimento;

public interface IAtendimentoRepository {
    List<Atendimento> getAtendimentos();
    Atendimento  getAtendimentoCod(long cod);
    Atendimento getAtendimentoByStatus(String status);
    boolean alocaAtendimento(long cod);
    Atendimento getAtualizacaoByStatus(String status);
    boolean cadastraAtendimento(Atendimento atendimento);
    Atendimento getCustoAtendimento(long cod);
    boolean alterarSituacaoDeAtendimento(long cod, String novaSituacao);
    boolean alocaEquipe(long cod);
    boolean finalizaAtendimento(long cod);
    boolean cancelaAtendimento(long cod);
    boolean confirmaAtendimento(long cod);
    boolean eventoTemAtendimento(long cod);
   
}