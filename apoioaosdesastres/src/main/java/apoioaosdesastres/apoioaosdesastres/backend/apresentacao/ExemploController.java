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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

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

   
@PostMapping("/atendimento/{codigo}")
    public ResponseEntity<Map<String, Object>> atualizarStatusAtendimento(
            @PathVariable long codigo,
            @RequestBody Map<String, String> corpoRequisicao) {

        // 1. Extrai o status do corpo do JSON
        String novoStatus = corpoRequisicao.get("status");

        // Validação básica se o status foi enviado
        if (novoStatus == null || novoStatus.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        novoStatus = novoStatus.toUpperCase();
        
        // (Opcional) Validação de segurança dos status permitidos
        List<String> statusValidos = List.of("PENDENTE", "EXECUTANDO", "FINALIZADO", "CANCELADO");
        if (!statusValidos.contains(novoStatus)) {
            return ResponseEntity.badRequest().build();
        }

        // 2. Usa o método da sua interface para alterar a situação
        boolean atualizou = atendimento.alterarSituacaoDeAtendimento(codigo, novoStatus);

        if (!atualizou) {
            // Se retornou false (ex: código não existe), retorna Erro 400 ou 404
            return ResponseEntity.badRequest().build(); 
        }

        // 3. Busca o atendimento atualizado no banco
        Atendimento atendimentoAtualizado = atendimento.getAtendimentoCod(codigo);

        if (atendimentoAtualizado == null) {
            return ResponseEntity.notFound().build();
        }

        // 4. Mapeia a resposta completa dinamicamente (sem DTO)
        Map<String, Object> jsonMap = new LinkedHashMap<>();
        jsonMap.put("codigoDoAtendimento", atendimentoAtualizado.getCod());
        jsonMap.put("dataInicio", atendimentoAtualizado.getDatainicio());
        jsonMap.put("duracao", atendimentoAtualizado.getDuracao());
        jsonMap.put("status", atendimentoAtualizado.getStatus());
        jsonMap.put("codigoDoEvento", atendimentoAtualizado.getEventoCodigo());
        
        // Adicionando a equipe, já que pede o cadastro "completo"
        jsonMap.put("numeroDaEquipe", atendimentoAtualizado.getEquipes()); 

        // Retorna 200 OK com o JSON populado
        return ResponseEntity.ok(jsonMap);
    }

  
}

