package apoioaosdesastres.apoioaosdesastres.backend.apresentacao;

import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IEquipamentoJpaItfRep;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Atendimento;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Evento;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Equipe;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Equipamento;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IAtendimentoRepository;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IEquipamentoRepository;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IEquipeRepository;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IEventoRepository;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IEventoJpaItfRep;
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
    private IEquipamentoJpaItfRep equipamentoJpaRepository;
    private IEventoJpaItfRep eventoJpaRepository;




    
    @Autowired
    public ExemploController(IAtendimentoRepository atendimento, IEventoRepository evento, IEquipeRepository equipe, IEquipamentoRepository equipamento, IAtendimentoJpaItfRep atendimentoJpaRepository, IEquipamentoJpaItfRep equipamentoJpaRepository, IEventoJpaItfRep eventoJpaRepository) {
        this.atendimento = atendimento;
        this.evento = evento;
        this.equipamento = equipamento;
        this.equipe = equipe;
        this.atendimentoJpaRepository = atendimentoJpaRepository;
        this.equipamentoJpaRepository = equipamentoJpaRepository;
        this.eventoJpaRepository = eventoJpaRepository;
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

        //Busca TODOS os atendimentos usando o método que já existe na interface
        List<Atendimento> todosAtendimentos = atendimento.getAtendimentos();

        //Filtra a lista pelo status e já mapeia para o formato JSON esperado
        List<Map<String, Object>> resposta = todosAtendimentos.stream()
                // Filtra para manter apenas os atendimentos com o status procurado
                .filter(a -> a.getStatus() != null && a.getStatus().equalsIgnoreCase(statusUpper))
                // Transforma a entidade no Map (JSON) 
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

    // ver no postman /acmerescue/equipe/atendimento/{numero}
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

    // ver no postman /acmerescue/equipeequipamento/{numero}
   @GetMapping("/equipe/equipamento/{numero}")
    public ResponseEntity<List<Map<String, Object>>> getEquipamentoPorEquipe(@PathVariable long numero) {
        List<Equipamento> todosEquipamentos = equipamento.getEquipamentos();

        List<Map<String, Object>> resposta = todosEquipamentos.stream()
                .filter(e -> e.getEquipe() != null && e.getEquipe().getNumero() == numero)
                .map(e -> {
                    Map<String, Object> jsonMap = new LinkedHashMap<>();
                    jsonMap.put("nome", e.getNome());
                    jsonMap.put("custoDiario", e.getCustoDiario());
                    jsonMap.put("numeroEquipe", e.getEquipe().getNumero());
                    return jsonMap;
                }).collect(Collectors.toList());

        if (resposta.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(resposta);
    }

    // ver no postman /acmerescue/cadastro/cadvinculo
    /*como vincular um equipamento a uma equipe
    {
    "idEquipamento": 6,
    "numeroEquipe": 2
    }
   */
    @PostMapping("/cadastro/cadvinculo")
    public ResponseEntity<Boolean> cadVinculo(@RequestBody Map<String, Object> body) {
        if (body == null) return ResponseEntity.badRequest().body(false);

        Object idObj = body.getOrDefault("id", body.get("idEquipamento"));
        if (idObj == null) idObj = body.get("idequipamento");
        Object numObj = body.getOrDefault("numero", body.get("numeroEquipe"));
        if (numObj == null) numObj = body.get("numero_equipe");

        if (idObj == null || numObj == null) return ResponseEntity.badRequest().body(false);

        long id;
        long numero;
        try {
            id = ((Number) idObj).longValue();
            numero = ((Number) numObj).longValue();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(false);
        }

        Optional<Equipamento> optEquip = equipamento.getEquipamentos().stream()
                .filter(e -> e.getId() == id)
                .findFirst();

        Optional<Equipe> optEquipe = equipe.getEquipes().stream()
                .filter(eq -> eq.getNumero() == numero)
                .findFirst();

        if (optEquip.isEmpty() || optEquipe.isEmpty()) {
            return ResponseEntity.ok(false);
        }

        Equipamento eq = optEquip.get();
        eq.setEquipe(optEquipe.get());

        return ResponseEntity.ok(true);
    }


// ver no postman /acmerescue/cadastro/cadevento
    /*como cadastrar um evento
    {
    "codigo": 3,
    "descricao": "Inundação na cidade X"
    "data": "2024-06-01",
    "latitude": -23.5505,
    "longitude": -46.6333
    }
    */
    @PostMapping("/cadastro/cadevento")
    public ResponseEntity<Boolean> cadEvento(@RequestBody Map<String, Object> body) {
        if (body == null || body.isEmpty()) return ResponseEntity.badRequest().body(false);

        Object codObj = primeiroValor(body, "codigo", "cod");
        Object descObj = primeiroValor(body, "descricao");
        Object dataObj = primeiroValor(body, "data", "datainicio");
        Object latObj = primeiroValor(body, "latitude", "lat");
        Object lonObj = primeiroValor(body, "longitude", "lon");

        if (codObj == null || descObj == null || dataObj == null || latObj == null || lonObj == null) {
            return ResponseEntity.badRequest().body(false);
        }

        long codigo;
        String descricao = descObj.toString();
        LocalDate data;
        double latitude;
        double longitude;

        try {
            codigo = codObj instanceof Number ? ((Number) codObj).longValue() : Long.parseLong(codObj.toString());
            data = LocalDate.parse(dataObj.toString());
            latitude = latObj instanceof Number ? ((Number) latObj).doubleValue() : Double.parseDouble(latObj.toString());
            longitude = lonObj instanceof Number ? ((Number) lonObj).doubleValue() : Double.parseDouble(lonObj.toString());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(false);
        }

        // evita duplicidade
        List<Evento> listaEventos = evento.getEventos();
        if (listaEventos == null) {
            listaEventos = Collections.emptyList();
        }
        boolean existe = listaEventos.stream().anyMatch(e -> {
            try {
                Long codigoEvento = e.getCodigo();
                return codigoEvento != null && codigoEvento.longValue() == codigo;
            } catch (Exception ex) {
                return false;
            }
        });
        if (existe) {
            return ResponseEntity.ok(false);
        }

        Evento novo = criarEvento(codigo, descricao, data, latitude, longitude);
        if (novo == null) {
            return ResponseEntity.status(500).body(false);
        }

        if (!adicionarEvento(novo)) {
            return ResponseEntity.status(500).body(false);
        }

        return ResponseEntity.ok(true);
    }

    private Object primeiroValor(Map<String, Object> body, String... chaves) {
        for (String chave : chaves) {
            if (body.containsKey(chave) && body.get(chave) != null) {
                return body.get(chave);
            }
        }
        return null;
    }

    private Evento criarEvento(long codigo, String descricao, LocalDate data, double latitude, double longitude) {
        try {
            Constructor<Evento> ctorPadrao = Evento.class.getDeclaredConstructor();
            ctorPadrao.setAccessible(true);
            Evento novo = ctorPadrao.newInstance();
            novo.setCodigo(codigo);
            novo.setDescricao(descricao);
            setEventoData(novo, data);
            setEventoLatitude(novo, latitude);
            setEventoLongitude(novo, longitude);
            return novo;
        } catch (Exception ignored) {
        }

        try {
            for (Constructor<?> ctor : Evento.class.getDeclaredConstructors()) {
                Object[] argumentos = montarArgumentos(ctor.getParameterTypes(), codigo, descricao, data, latitude, longitude);
                if (argumentos == null) {
                    continue;
                }

                ctor.setAccessible(true);
                Object instancia = ctor.newInstance(argumentos);
                if (instancia instanceof Evento) {
                    Evento novo = (Evento) instancia;
                    novo.setCodigo(codigo);
                    novo.setDescricao(descricao);
                    setEventoData(novo, data);
                    setEventoLatitude(novo, latitude);
                    setEventoLongitude(novo, longitude);
                    return novo;
                }
            }
        } catch (Exception ignored) {
        }

        return null;
    }

    private Object[] montarArgumentos(Class<?>[] tipos, long codigo, String descricao, LocalDate data, double latitude, double longitude) {
        Object[] argumentos = new Object[tipos.length];
        int indiceDouble = 0;

        for (int i = 0; i < tipos.length; i++) {
            Class<?> tipo = tipos[i];

            if (tipo == long.class || tipo == Long.class) {
                argumentos[i] = codigo;
            } else if (tipo == int.class || tipo == Integer.class) {
                argumentos[i] = (int) codigo;
            } else if (tipo == short.class || tipo == Short.class) {
                argumentos[i] = (short) codigo;
            } else if (tipo == String.class) {
                argumentos[i] = descricao;
            } else if (tipo == LocalDate.class) {
                argumentos[i] = data;
            } else if (tipo == double.class || tipo == Double.class) {
                argumentos[i] = indiceDouble == 0 ? latitude : longitude;
                indiceDouble++;
            } else if (tipo == float.class || tipo == Float.class) {
                argumentos[i] = (float) (indiceDouble == 0 ? latitude : longitude);
                indiceDouble++;
            } else {
                return null;
            }
        }

        return argumentos;
    }

    private boolean adicionarEvento(Evento novo) {
        try {
            if (eventoJpaRepository != null) {
                Evento salvo = eventoJpaRepository.save(novo);
                return salvo != null;
            }
        } catch (Exception ignored) {
        }

        try {
            for (Method method : evento.getClass().getMethods()) {
                if ("save".equals(method.getName()) && method.getParameterCount() == 1) {
                    Object resultado = method.invoke(evento, novo);
                    return resultado != null;
                }
            }

           
            List<Evento> eventos = evento.getEventos();
            if (eventos == null) {
                eventos = new ArrayList<>();
            } else {
                eventos = new ArrayList<>(eventos);
            }

            eventos.add(novo);

            for (String nomeMetodo : List.of("setEventos", "setListaEventos")) {
                try {
                    Method metodo = evento.getClass().getMethod(nomeMetodo, List.class);
                    metodo.invoke(evento, eventos);
                    return true;
                } catch (NoSuchMethodException ignored) {
                }
            }

            for (java.lang.reflect.Field campo : evento.getClass().getDeclaredFields()) {
                if (List.class.isAssignableFrom(campo.getType())) {
                    campo.setAccessible(true);
                    campo.set(evento, eventos);
                    return true;
                }
            }
        } catch (Exception ignored) {
        }

        return false;
    }

    private void setEventoData(Evento evento, LocalDate data) {
        try {
            for (String metodoNome : List.of("setData", "setDataEvento", "setDataevento", "setDatainicio", "setDataInicio")) {
                try {
                    Method metodo = Evento.class.getMethod(metodoNome, LocalDate.class);
                    metodo.invoke(evento, data);
                    return;
                } catch (NoSuchMethodException ignored) {
                }
            }

            for (String campoNome : List.of("data", "dataEvento", "datainicio", "dataInicio")) {
                try {
                    java.lang.reflect.Field campo = Evento.class.getDeclaredField(campoNome);
                    campo.setAccessible(true);
                    campo.set(evento, data);
                    return;
                } catch (NoSuchFieldException ignored) {
                }
            }
        } catch (Exception ignored) {
        }
    }

    private void setEventoLatitude(Evento evento, double latitude) {
        try {
            for (String metodoNome : List.of("setLatitude", "setLat", "setCoordLatitude", "setCoordenadaLatitude")) {
                try {
                    Method metodo = Evento.class.getMethod(metodoNome, double.class);
                    metodo.invoke(evento, latitude);
                    return;
                } catch (NoSuchMethodException ignored) {
                }
            }

            for (String campoNome : List.of("latitude", "lat", "coordLatitude", "coordenadaLatitude")) {
                try {
                    java.lang.reflect.Field campo = Evento.class.getDeclaredField(campoNome);
                    campo.setAccessible(true);
                    campo.set(evento, latitude);
                    return;
                } catch (NoSuchFieldException ignored) {
                }
            }
        } catch (Exception ignored) {
        }
    }

    private void setEventoLongitude(Evento evento, double longitude) {
        try {
            for (String metodoNome : List.of("setLongitude", "setLon", "setLng", "setCoordLongitude", "setCoordenadaLongitude")) {
                try {
                    Method metodo = Evento.class.getMethod(metodoNome, double.class);
                    metodo.invoke(evento, longitude);
                    return;
                } catch (NoSuchMethodException ignored) {
                }
            }

            for (String campoNome : List.of("longitude", "lon", "lng", "coordLongitude", "coordenadaLongitude")) {
                try {
                    java.lang.reflect.Field campo = Evento.class.getDeclaredField(campoNome);
                    campo.setAccessible(true);
                    campo.set(evento, longitude);
                    return;
                } catch (NoSuchFieldException ignored) {
                }
            }
        } catch (Exception ignored) {
        }
    }

    //como ver no postman /acmerescue/cadastro/cadatendimento
    /*como cadastrar um atendimento
    {
     "codigo": 3,
     "dataInicio": "2024-06-01",
     "duracao": 10,
     "status": "PENDENTE",
     "codigodoevetno": 7
     }
*/
    @PostMapping("/cadastro/cadatendimento")
    public ResponseEntity<Boolean> cadAtendimento(@RequestBody Map<String, Object> body) {
        if (body == null || body.isEmpty()) return ResponseEntity.badRequest().body(false);

        Object codObj = primeiroValor(body, "cod", "codigo", "id");
        Object dataObj = primeiroValor(body, "dataInicio", "datainicio", "data");
        Object duracaoObj = primeiroValor(body, "duracao", "duracaoHoras", "duracao_horas");
        Object statusObj = primeiroValor(body, "status");
        Object eventoCodigoObj = primeiroValor(body, "codigoEvento", "eventoCodigo", "codigo_do_evento", "codigo");

        if (codObj == null || dataObj == null || duracaoObj == null || statusObj == null || eventoCodigoObj == null) {
            return ResponseEntity.badRequest().body(false);
        }

        long cod;
        LocalDate dataInicio;
        Double duracao;
        String status;
        long eventoCodigo;

        try {
            cod = codObj instanceof Number ? ((Number) codObj).longValue() : Long.parseLong(codObj.toString());
            dataInicio = LocalDate.parse(dataObj.toString());
            duracao = duracaoObj instanceof Number ? ((Number) duracaoObj).doubleValue() : Double.parseDouble(duracaoObj.toString());
            status = statusObj.toString();
            eventoCodigo = eventoCodigoObj instanceof Number ? ((Number) eventoCodigoObj).longValue() : Long.parseLong(eventoCodigoObj.toString());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(false);
        }

        // evita duplicidade pelo codigo do atendimento
        List<Atendimento> lista = atendimento.getAtendimentos();
        if (lista != null && lista.stream().anyMatch(a -> {
            try { return a.getCod() == cod; } catch (Exception e) { return false; }
        })) {
            return ResponseEntity.ok(false);
        }

        Atendimento novo = criarAtendimento(cod, dataInicio, duracao, status, eventoCodigo);
        if (novo == null) return ResponseEntity.status(500).body(false);

        if (!adicionarAtendimento(novo)) return ResponseEntity.status(500).body(false);

        return ResponseEntity.ok(true);
    }

    private Atendimento criarAtendimento(long cod, LocalDate dataInicio, Double duracao, String status, long eventoCodigo) {
        try {
            Constructor<Atendimento> ctorPadrao = Atendimento.class.getDeclaredConstructor();
            ctorPadrao.setAccessible(true);
            Atendimento novo = ctorPadrao.newInstance();
            setAtendimentoCod(novo, cod);
            setAtendimentoDataInicio(novo, dataInicio);
            setAtendimentoDuracao(novo, duracao);
            setAtendimentoStatus(novo, status);
            setAtendimentoEventoCodigo(novo, eventoCodigo);
            return novo;
        } catch (Exception ignored) {}

        try {
            for (Constructor<?> ctor : Atendimento.class.getDeclaredConstructors()) {
                Object[] argumentos = montarArgumentos(ctor.getParameterTypes(), cod, status, dataInicio, duracao, eventoCodigo);
                if (argumentos == null) continue;
                ctor.setAccessible(true);
                Object instancia = ctor.newInstance(argumentos);
                if (instancia instanceof Atendimento) {
                    Atendimento novo = (Atendimento) instancia;
                    setAtendimentoCod(novo, cod);
                    setAtendimentoDataInicio(novo, dataInicio);
                    setAtendimentoDuracao(novo, duracao);
                    setAtendimentoStatus(novo, status);
                    setAtendimentoEventoCodigo(novo, eventoCodigo);
                    return novo;
                }
            }
        } catch (Exception ignored) {}

        return null;
    }

    private void setAtendimentoCod(Atendimento atendimentoObj, long cod) {
        try {
            for (String nome : List.of("setCod", "setCodigo", "setId")) {
                try {
                    Method m = Atendimento.class.getMethod(nome, long.class);
                    m.invoke(atendimentoObj, cod);
                    return;
                } catch (NoSuchMethodException ignored) {}
            }
            for (String campo : List.of("cod", "codigo", "id")) {
                try {
                    java.lang.reflect.Field f = Atendimento.class.getDeclaredField(campo);
                    f.setAccessible(true);
                    f.set(atendimentoObj, cod);
                    return;
                } catch (NoSuchFieldException ignored) {}
            }
        } catch (Exception ignored) {}
    }

    private void setAtendimentoDataInicio(Atendimento atendimentoObj, LocalDate data) {
        try {
            for (String nome : List.of("setDataInicio", "setDatainicio", "setData")) {
                try {
                    Method m = Atendimento.class.getMethod(nome, LocalDate.class);
                    m.invoke(atendimentoObj, data);
                    return;
                } catch (NoSuchMethodException ignored) {}
            }
            for (String campo : List.of("dataInicio", "datainicio", "data")) {
                try {
                    java.lang.reflect.Field f = Atendimento.class.getDeclaredField(campo);
                    f.setAccessible(true);
                    f.set(atendimentoObj, data);
                    return;
                } catch (NoSuchFieldException ignored) {}
            }
        } catch (Exception ignored) {}
    }

    private void setAtendimentoDuracao(Atendimento atendimentoObj, Double duracao) {
        try {
            for (String nome : List.of("setDuracao", "setDuracaoHoras", "setDuracao_horas")) {
                try {
                    Method m = Atendimento.class.getMethod(nome, double.class);
                    m.invoke(atendimentoObj, duracao);
                    return;
                } catch (NoSuchMethodException ignored) {}
            }
            for (String campo : List.of("duracao", "duracaoHoras", "duracao_horas")) {
                try {
                    java.lang.reflect.Field f = Atendimento.class.getDeclaredField(campo);
                    f.setAccessible(true);
                    Class<?> t = f.getType();
                    if (t == double.class || t == Double.class) f.set(atendimentoObj, duracao);
                    else if (t == int.class || t == Integer.class) f.set(atendimentoObj, duracao.intValue());
                    else if (t == long.class || t == Long.class) f.set(atendimentoObj, duracao.longValue());
                    return;
                } catch (NoSuchFieldException ignored) {}
            }
        } catch (Exception ignored) {}
    }

    private void setAtendimentoStatus(Atendimento atendimentoObj, String status) {
        try {
            for (String nome : List.of("setStatus")) {
                try {
                    Method m = Atendimento.class.getMethod(nome, String.class);
                    m.invoke(atendimentoObj, status);
                    return;
                } catch (NoSuchMethodException ignored) {}
            }
            for (String campo : List.of("status")) {
                try {
                    java.lang.reflect.Field f = Atendimento.class.getDeclaredField(campo);
                    f.setAccessible(true);
                    f.set(atendimentoObj, status);
                    return;
                } catch (NoSuchFieldException ignored) {}
            }
        } catch (Exception ignored) {}
    }

    private void setAtendimentoEventoCodigo(Atendimento atendimentoObj, long eventoCodigo) {
        try {
            for (String nome : List.of("setEventoCodigo", "setCodigoEvento", "setEventoId", "setEvento")) {
                try {
                    Method[] methods = Atendimento.class.getMethods();
                    for (Method m : methods) {
                        if (m.getName().equals(nome) && m.getParameterCount() == 1) {
                            Class<?> p = m.getParameterTypes()[0];
                            if (p == long.class || p == Long.class) { m.invoke(atendimentoObj, eventoCodigo); return; }
                            if (p == String.class) { m.invoke(atendimentoObj, String.valueOf(eventoCodigo)); return; }
                        }
                    }
                } catch (Exception ignored) {}
            }
            for (String campo : List.of("eventoCodigo", "codigoEvento", "evento_id", "evento")) {
                try {
                    java.lang.reflect.Field f = Atendimento.class.getDeclaredField(campo);
                    f.setAccessible(true);
                    Class<?> t = f.getType();
                    if (t == long.class || t == Long.class) f.set(atendimentoObj, eventoCodigo);
                    else if (t == String.class) f.set(atendimentoObj, String.valueOf(eventoCodigo));
                    else f.set(atendimentoObj, eventoCodigo);
                    return;
                } catch (NoSuchFieldException ignored) {}
            }
        } catch (Exception ignored) {}
    }

    private boolean adicionarAtendimento(Atendimento novo) {
        try {
            if (atendimentoJpaRepository != null) {
                Atendimento salvo = atendimentoJpaRepository.save(novo);
                return salvo != null;
            }
        } catch (Exception ignored) {}

        try {
            for (Method method : atendimento.getClass().getMethods()) {
                if ("save".equals(method.getName()) && method.getParameterCount() == 1) {
                    Object resultado = method.invoke(atendimento, novo);
                    return resultado != null;
                }
            }

            List<Atendimento> lista = atendimento.getAtendimentos();
            if (lista == null) lista = new ArrayList<>(); else lista = new ArrayList<>(lista);
            lista.add(novo);

            for (String nomeMetodo : List.of("setAtendimentos", "setListaAtendimentos")) {
                try {
                    Method metodo = atendimento.getClass().getMethod(nomeMetodo, List.class);
                    metodo.invoke(atendimento, lista);
                    return true;
                } catch (NoSuchMethodException ignored) {}
            }

            for (java.lang.reflect.Field campo : atendimento.getClass().getDeclaredFields()) {
                if (List.class.isAssignableFrom(campo.getType())) {
                    campo.setAccessible(true);
                    campo.set(atendimento, lista);
                    return true;
                }
            }
        } catch (Exception ignored) {}

        return false;
    }

    // como ver no postman /acmerescue/cadastro/alocaatendimento
    /* {
        "codigo": 3
       }
    */ 
    @PostMapping("/processo/alocaatendimento")
    public ResponseEntity<Boolean> alocaatendimento(@RequestBody Map<String, Object> body) {
        if (body == null || body.isEmpty()) {
            return ResponseEntity.badRequest().body(false);
        }

        Object codObj = primeiroValor(body, "cod", "codigo", "id");
        if (codObj == null) {
            return ResponseEntity.badRequest().body(false);
        }

        long cod;
        try {
            cod = codObj instanceof Number ? ((Number) codObj).longValue() : Long.parseLong(codObj.toString());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(false);
        }

        Optional<Atendimento> optAtendimento = atendimento.getAtendimentos().stream()
                .filter(a -> a.getCod() == cod)
                .findFirst();

        if (optAtendimento.isEmpty()) {
            return ResponseEntity.ok(false);
        }

        Atendimento atendimentoObj = optAtendimento.get();
        
        try {
            if (atendimentoJpaRepository != null) {
                atendimentoJpaRepository.save(atendimentoObj);
                return ResponseEntity.ok(true);
            }
            return ResponseEntity.ok(true);
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(false);
        }
    }

    // como ver no postman /acmerescue/atendimento/:codigo
    // Atualizar o status de um atendimento
    /*
    {
        "status": "EXECUTANDO"
    }
    */
    @PostMapping("/atendimento/{codigo}")
    public ResponseEntity<Map<String, Object>> atualizaStatusAtendimento(
            @PathVariable long codigo,
            @RequestBody Map<String, Object> body) {
        
        if (body == null || body.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Object statusObj = body.get("status");
        if (statusObj == null) {
            return ResponseEntity.badRequest().build();
        }

        String novoStatus = statusObj.toString().toUpperCase();

        Optional<Atendimento> optAtendimento = atendimento.getAtendimentos().stream()
                .filter(a -> a.getCod() == codigo)
                .findFirst();

        if (optAtendimento.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Atendimento atendimentoObj = optAtendimento.get();
        setAtendimentoStatus(atendimentoObj, novoStatus);

        try {
            if (atendimentoJpaRepository != null) {
                atendimentoJpaRepository.save(atendimentoObj);
            }
        } catch (Exception ignored) {}

        Map<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("codigoDoAtendimento", atendimentoObj.getCod());
        resposta.put("dataInicio", atendimentoObj.getDatainicio());
        resposta.put("duracao", atendimentoObj.getDuracao());
        resposta.put("status", atendimentoObj.getStatus());
        resposta.put("codigoDoEvento", atendimentoObj.getEventoCodigo());

        return ResponseEntity.ok(resposta);
    }
}
