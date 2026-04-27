package apoioaosdesastres.apoioaosdesastres.backend.apresentacao;

import java.util.*;
import org.springframework.web.bind.annotation.*;
import apoioaosdesastres.apoioaosdesastres.*;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Atendimento;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Evento;
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
    public ExemploController(IAtendimentoRepository atendimento, IEventoRepository evento, IEquipeRepository equipe) {
        this.atendimento = atendimento;
        this.evento = evento;
        this.equipamento = equipamento;
        this.equipe = equipe;
    }

       
    @GetMapping("")
    public String getMensagemInicial() {
        return "Aplicacao acmerescue funcionando!";
    }

    @GetMapping("/cadastro/listaatendimentos")
    public List<Atendimento> getAtendimentos() {
        return atendimento.getAtendimentos();
    }

    @GetMapping("/cadastro/listaeventos")
    public List<Evento> getEventos() {
        return evento.getEventos();
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

      //como ver no postman /acmerescue/atendimento/Em andamento
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

    /* Testar quando colocar as equipes e os equipamentos */
    /*
     * Descrição: Retorna o custo de um atendimento de um evento
     * Parâmetros de entrada codigo: código do evento
     * JSON resposta : [{código do evento, código do atendimento, número equipe,
     * custo}, ...]
     * 
     * /* @GetMapping("/atendimento/custo/{codigo}")
     * public Atendimento getCustoAtendimento(@PathVariable("evento_codigo") Long
     * codigo) {
     * return atendimento.getCustoAtendimento(codigo);
     * }
     */

    /*
     * Endpoint: GET /acmerescue/equipe/atendimento/:numero
     * Descrição : Retorna a lista de atendimentos da equipe informada
     * Parâmetros de entrada numero: número da equipe
     * JSON resposta : [{código do atendimento, ...}, ... ]
     */

    

  
}

