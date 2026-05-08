package apoioaosdesastres.apoioaosdesastres.backend.apresentacao;

import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IEquipamentoJpaItfRep;
import java.util.*;
import java.util.stream.Collectors;

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
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IAtendimentoJpaItfRep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/acmerescue")
public class ExemploController {
    private IAtendimentoRepository atendimento;
    private IEventoRepository evento;
    private IEquipamentoRepository equipamento;
    private IEquipeRepository equipe;  
    private IAtendimentoJpaItfRep atendimentoJpaRepository;
    



    @Autowired
    public ExemploController(IAtendimentoRepository atendimento, IEventoRepository evento, IEquipeRepository equipe, IEquipamentoRepository equipamento, IAtendimentoJpaItfRep atendimentoJpaRepository) {
        this.atendimento = atendimento;
        this.evento = evento;
        this.equipamento = equipamento;
        this.equipe = equipe;
        this.atendimentoJpaRepository = atendimentoJpaRepository;
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

    
    // como ver no postman /acmerescue/validaequipamento?odigo=1
    @PostMapping("/validaequipamento")
    public boolean validaequipamento(long codigo) {
        for (Equipamento equipamento : equipamento.getEquipamentos()) {
            if (equipamento.getId() == codigo) {
                return true;
            }
        }
        return false;
    }

    // como ver no postman /acmerescue/atendiment/PENDENTE,ou EXECUTADO, ou FINALIZADO, ou CANCELADO
     @GetMapping("/atendimento/{status}")
    public ResponseEntity<List<Map<String, Object>>> getAtendimentosPorStatus(@PathVariable String status) {
        
        String statusUpper = status.toUpperCase();

        // Validação dos status permitidos
        List<String> statusValidos = List.of("PENDENTE", "EXECUTANDO", "FINALIZADO", "CANCELADO");
        if (!statusValidos.contains(statusUpper)) {
            return ResponseEntity.badRequest().build();
        }

        // 1. Busca TODOS os atendimentos usando o método que já existe na sua interface
        List<Atendimento> todosAtendimentos = atendimento.getAtendimentos();

        // 2. Filtra a lista pelo status e já mapeia para o formato JSON esperado
        List<Map<String, Object>> resposta = todosAtendimentos.stream()
                // Filtra para manter apenas os atendimentos com o status procurado
                .filter(a -> a.getStatus() != null && a.getStatus().equalsIgnoreCase(statusUpper))
                // Transforma a entidade no Map (JSON) para não usar DTO
                .map(atendimento -> {
                    Map<String, Object> jsonMap = new LinkedHashMap<>();
                    jsonMap.put("codigoDoAtendimento", atendimento.getCod());
                    jsonMap.put("dataInicio", atendimento.getDatainicio());
                    jsonMap.put("duracao", atendimento.getDuracao());
                    jsonMap.put("status", atendimento.getStatus());
                    jsonMap.put("codigoDoEvento", atendimento.getEventoCodigo()); 
                    
                    return jsonMap;
                }).collect(Collectors.toList());

        // Se a lista filtrada estiver vazia, retorna 204
        if (resposta.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        // Retorna a lista JSON
        return ResponseEntity.ok(resposta);
    }

    // GET /acmerescue/atendimento/custo/{codigo}
    @GetMapping("/atendimento/custo/{codigo}")
    public ResponseEntity<List<Map<String, Object>>> getCustoAtendimentosPorEvento(@PathVariable long codigo) {
        List<Atendimento> todosAtendimentos = atendimento.getAtendimentos();

        List<Map<String, Object>> resposta = todosAtendimentos.stream()
                .filter(a -> a.getEventoCodigo() != null && a.getEventoCodigo() == codigo)
                .map(a -> {
                    Map<String, Object> jsonMap = new LinkedHashMap<>();
                    jsonMap.put("codigoEvento", a.getEventoCodigo());
                    jsonMap.put("codigoAtendimento", a.getCod());
                    jsonMap.put("numeroEquipe", a.getEquipes());
                    jsonMap.put("custo", a.calculaCusto());
                    return jsonMap;
                }).collect(Collectors.toList());

        if (resposta.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(resposta);
    }

    // GET /acmerescue/equipe/atendimento/{numero}
    // retornando vazio
    @GetMapping("/equipe/atendimento/{numero}")
    public ResponseEntity<List<Map<String, Object>>> getAtendimentosPorEquipe(@PathVariable long numero) {
        List<Atendimento> todosAtendimentos = atendimento.getAtendimentos();

        List<Map<String, Object>> resposta = todosAtendimentos.stream()
                .filter(a -> a.getEquipes() != null && a.getEquipes() == numero)
                .map(a -> {
                    Map<String, Object> jsonMap = new LinkedHashMap<>();
                    jsonMap.put("codigoAtendimento", a.getCod());
                    jsonMap.put("dataInicio", a.getDatainicio());
                    jsonMap.put("duracao", a.getDuracao());
                    jsonMap.put("status", a.getStatus());
                    jsonMap.put("codigoEvento", a.getEventoCodigo());
                    jsonMap.put("numeroEquipe", a.getEquipes());
                    return jsonMap;
                }).collect(Collectors.toList());

        if (resposta.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(resposta);
    }

   

  
}

