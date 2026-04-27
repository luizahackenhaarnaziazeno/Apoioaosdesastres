package apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IAtendimentoRepository;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IEquipamentoRepository;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IEquipeRepository;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IEventoRepository;


@Service
public class Cadastros {
    private IEventoRepository eventoRepository;
    private IEquipamentoRepository equipamentoRepository;
    private IAtendimentoRepository atendimentoRepository;
    private IEquipeRepository equipeRepository;

    @Autowired
    public Cadastros(IEventoRepository eventoRepository, IEquipamentoRepository equipamentoRepository,
            IAtendimentoRepository atendimentoRepository, IEquipeRepository equipeRepository) {
        this.eventoRepository = eventoRepository;
        this.equipamentoRepository = equipamentoRepository;
        this.atendimentoRepository = atendimentoRepository;
        this.equipeRepository = equipeRepository;
    }

    /*public boolean cadastraEvento(Evento evento) {
        return eventoRepository.cadastraEvento(evento);
    }*/

    public boolean cadastraEquipamento(Equipamento equipamento) {
        return equipamentoRepository.cadastro(equipamento) != null;
    }

    public boolean cadastraAtendimento(Atendimento atendimento) {
        return atendimentoRepository.cadastraAtendimento(atendimento);
    }

    public boolean cadastraEquipe(Equipe equipe) {
        return equipeRepository.cadastro(equipe) != null;
    }
}

