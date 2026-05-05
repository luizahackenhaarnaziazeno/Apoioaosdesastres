package apoioaosdesastres.apoioaosdesastres.backend.apresentacao;

import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IEquipamentoJpaItfRep;
import java.util.*;
import org.springframework.web.bind.annotation.*;
import apoioaosdesastres.apoioaosdesastres.*;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Atendimento;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Evento;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Equipe;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Equipamento;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IAtendimentoRepository;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IEquipamentoRepository;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IEquipeRepository;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IEventoRepository;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/acmerescue")
public class ExemploController {
    private IAtendimentoRepository atendimento;
    private IEventoRepository evento;
    private IEquipamentoRepository equipamento;
    private IEquipeRepository equipe;

    @Autowired
    public ExemploController(IAtendimentoRepository atendimento, IEventoRepository evento, IEquipeRepository equipe, IEquipamentoRepository equipamento) {
        this.atendimento = atendimento;
        this.evento = evento;
        this.equipamento = equipamento;
        this.equipe = equipe;
    }

    @GetMapping("")
    public String getMensagemInicial() {
        return "Aplicacao acmerescue funcionando!";
    }
    
    /*funcionando */

    // como ver no postman /acmerescue/cadastro/listaatendimentos
    @GetMapping("/cadastro/listaatendimentos")
    public List<Atendimento> getAtendimentos() {
        return atendimento.getAtendimentos();
    }

    // como ver no postman /acmerescue/cadastro/listaeventos
    @GetMapping("/cadastro/listaeventos")
    public List<Evento> getEventos() {
        return evento.getEventos();
    }

    // como ver no postman /acmerescue/cadastro/listaequipes
    @GetMapping("/cadastro/listaequipes")
    public List<Equipe> getEquipes() {
        return equipe.getEquipes();
    }                                                                  

    // como ver no postman /acmerescue/cadastro/listaequipamentos
    @GetMapping("/cadastro/listaequipamentos")
    public List<Equipamento> getEquipamentos() {
        return equipamento.getEquipamentos();
    }

    
    // como ver no postman /acmerescue/validaatendimento?cod=1
    @PostMapping("/validaatendimento")
    public boolean validaatendimento(long cod) {
        for (Atendimento atendimento : atendimento.getAtendimentos()) {
            if (atendimento.getCod() == cod) {
                return true;
            }
        }
        return false;

    }
    

    // como ver no postman /acmerescue/validaevento?codigo=1
    @PostMapping("/validaevento")
    public boolean validaevento(long codigo) {
        for (Evento evento : evento.getEventos()) {
            if (evento.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    
    // como ver no postman /acmerescue/validaequipe?codigo=1
    @PostMapping("/validaequipe")
    public boolean validaequipe(long codigo) {
        for (Equipe equipe : equipe.getEquipes()) {
            if (equipe.getNumero() == codigo) {
                return true;
            }
        }
        return false;
    }

    
    // como ver no postman /acmerescue/validaequipamento?codigo=1
    @PostMapping("/validaequipamento")
    public boolean validaequipamento(long codigo) {
        for (Equipamento equipamento : equipamento.getEquipamentos()) {
            if (equipamento.getId() == codigo) {
                return true;
            }
        }
        return false;
    }

    /*nao testei ainda */
    
    /* */
      @GetMapping("/atendimento/{status}")
      public Atendimento getAtendimentoByStatus(@PathVariable("status") String status) {
          return atendimento.getAtendimentoByStatus(status);
      }
  

    //ARRUMAR:

    // Aloca um atendimento a alguma equipe e Retorna se o cadastro teve sucesso e
    // Booleano: true ou false
    /*@PostMapping("/processo/alocaatendimento")
    public boolean alocaatendimento(long cod) {
        for (Atendimento atendimento : atendimento.getAtendimentos()) {
            if (atendimento.getCod() == cod) {
              this.equipe.adicionaEquipe(equipe.getEquipes());
                return true;
            }
        }
        return false;
    }*/


 

    /*
     * Descrição : Atualizar o status de um atendimento
     * JSON resposta : Retorna o cadastro completo do atendimento {código do
     * atendimento, ...}
     */   
     //como ver no postman /acmerescue/atendimento/3
  @PostMapping("/atendimento/{codigo}")
  public Atendimento Atualizastatus(@PathVariable("status") String status) {
    return atendimento.getAtualizacaoByStatus(status);
}


    //Testar quando colocar as equipes e os equipamentos 
    

    /*
     * Endpoint: GET /acmerescue/equipe/atendimento/:numero
     * Descrição : Retorna a lista de atendimentos da equipe informada
     * Parâmetros de entrada numero: número da equipe
     * JSON resposta : [{código do atendimento, ...}, ... ]
     */

    

  
}

