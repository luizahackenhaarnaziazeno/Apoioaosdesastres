package apoioaosdesastres.apoioaosdesastres.frontend;

import java.io.*;
import java.net.*;
import java.net.http.*;
import java.util.*;

public class AppCliente {

    private static final String URL_SERVIDOR = "https://jubilant-journey-r4p6v5p6qp5fxgrx-8081.app.github.dev/";

    // ── Endpoints de validação ──────────────────────────────────────────────
    private static final String ENDPOINT_VALIDA_EVENTO       = "/acmerescue/validaevento";
    private static final String ENDPOINT_VALIDA_ATENDIMENTO  = "/acmerescue/validaatendimento";
    private static final String ENDPOINT_VALIDA_EQUIPE       = "/acmerescue/validaequipe";
    private static final String ENDPOINT_VALIDA_EQUIPAMENTO  = "/acmerescue/validaequipamento";

    // ── Endpoints de listagem ───────────────────────────────────────────────
    private static final String ENDPOINT_LISTA_EVENTOS       = "/acmerescue/cadastro/listaeventos";
    private static final String ENDPOINT_LISTA_ATENDIMENTOS  = "/acmerescue/cadastro/listaatendimentos";
    private static final String ENDPOINT_LISTA_EQUIPES       = "/acmerescue/cadastro/listaequipes";
    private static final String ENDPOINT_LISTA_EQUIPAMENTOS  = "/acmerescue/cadastro/listaequipamentos";

    // ── Endpoints de cadastro ───────────────────────────────────────────────
    private static final String ENDPOINT_CAD_EVENTO          = "/acmerescue/cadastro/cadevento";
    private static final String ENDPOINT_CAD_ATENDIMENTO     = "/acmerescue/cadastro/cadatendimento";
    private static final String ENDPOINT_CAD_VINCULO         = "/acmerescue/cadastro/cadvinculo";

    // ── Endpoints de processo ───────────────────────────────────────────────
    private static final String ENDPOINT_ALOCA_ATENDIMENTO   = "/acmerescue/processo/alocaatendimento";

    // ── Endpoints de atendimento ────────────────────────────────────────────
    private static final String ENDPOINT_ATENDIMENTO_STATUS  = "/acmerescue/atendimento/";
    private static final String ENDPOINT_ATENDIMENTO_CUSTO   = "/acmerescue/atendimento/custo/";

    // ── Endpoints de equipe ─────────────────────────────────────────────────
    private static final String ENDPOINT_EQUIPE_ATENDIMENTOS = "/acmerescue/equipe/atendimento/";
    private static final String ENDPOINT_EQUIPE_EQUIPAMENTOS = "/acmerescue/equipe/equipamento/";

    private final Scanner entrada = new Scanner(System.in);

    // ═══════════════════════════════════════════════════════════════════════
    //  MENU PRINCIPAL
    // ═══════════════════════════════════════════════════════════════════════

    public void executar() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║       ACMERescue — Menu Principal    ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  1. Validações                       ║");
            System.out.println("║  2. Listagens                        ║");
            System.out.println("║  3. Cadastros                        ║");
            System.out.println("║  4. Operações de atendimento         ║");
            System.out.println("║  5. Consultas de equipe              ║");
            System.out.println("║  0. Sair                             ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("Opção: ");
            int opcao = lerInt();

            switch (opcao) {
                case 1 -> menuValidacoes();
                case 2 -> menuListagens();
                case 3 -> menuCadastros();
                case 4 -> menuOperacoes();
                case 5 -> menuEquipe();
                case 0 -> continuar = false;
                default -> System.out.println("[!] Opção inválida.");
            }
        }
        System.out.println("Encerrando o sistema. Até logo!");
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  SUBMENUS
    // ═══════════════════════════════════════════════════════════════════════

    private void menuValidacoes() {
        System.out.println("\n── Validações ──────────────────────────");
        System.out.println("  1. Validar evento");
        System.out.println("  2. Validar atendimento");
        System.out.println("  3. Validar equipe");
        System.out.println("  4. Validar equipamento");
        System.out.print("Opção: ");
        switch (lerInt()) {
            case 1 -> validarEvento();
            case 2 -> validarAtendimento();
            case 3 -> validarEquipe();
            case 4 -> validarEquipamento();
            default -> System.out.println("[!] Opção inválida.");
        }
    }

    private void menuListagens() {
        System.out.println("\n── Listagens ───────────────────────────");
        System.out.println("  1. Listar todos os eventos");
        System.out.println("  2. Listar todos os atendimentos");
        System.out.println("  3. Listar todas as equipes");
        System.out.println("  4. Listar todos os equipamentos");
        System.out.print("Opção: ");
        switch (lerInt()) {
            case 1 -> listarEventos();
            case 2 -> listarAtendimentos();
            case 3 -> listarEquipes();
            case 4 -> listarEquipamentos();
            default -> System.out.println("[!] Opção inválida.");
        }
    }

    private void menuCadastros() {
        System.out.println("\n── Cadastros ───────────────────────────");
        System.out.println("  1. Cadastrar evento");
        System.out.println("  2. Cadastrar atendimento");
        System.out.println("  3. Vincular equipamento a equipe");
        System.out.print("Opção: ");
        switch (lerInt()) {
            case 1 -> cadastrarEvento();
            case 2 -> cadastrarAtendimento();
            case 3 -> vincularEquipamento();
            default -> System.out.println("[!] Opção inválida.");
        }
    }

    private void menuOperacoes() {
        System.out.println("\n── Operações de Atendimento ────────────");
        System.out.println("  1. Alocar equipe em atendimento");
        System.out.println("  2. Listar atendimentos por status");
        System.out.println("  3. Atualizar status de atendimento");
        System.out.println("  4. Consultar custo de atendimento");
        System.out.print("Opção: ");
        switch (lerInt()) {
            case 1 -> alocaAtendimento();
            case 2 -> listarAtendimentosPorStatus();
            case 3 -> atualizarStatusAtendimento();
            case 4 -> consultarCustoAtendimento();
            default -> System.out.println("[!] Opção inválida.");
        }
    }

    private void menuEquipe() {
        System.out.println("\n── Consultas de Equipe ─────────────────");
        System.out.println("  1. Listar atendimentos de uma equipe");
        System.out.println("  2. Listar equipamentos de uma equipe");
        System.out.print("Opção: ");
        switch (lerInt()) {
            case 1 -> listarAtendimentosEquipe();
            case 2 -> listarEquipamentosEquipe();
            default -> System.out.println("[!] Opção inválida.");
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  1. VALIDAÇÕES  (POST → booleano)
    // ═══════════════════════════════════════════════════════════════════════

    /** POST /acmerescue/validaevento — retorna true/false */
    public void validarEvento() {
        System.out.println("\n══ Validar Evento ══════════════════════");
        System.out.print("Código do evento: ");
        long codigo = lerLong();

        String url = URL_SERVIDOR + ENDPOINT_VALIDA_EVENTO;
        boolean valido = postRetornaBooleano(url, String.valueOf(codigo));
        System.out.println("Evento válido: " + valido);
    }

    /** POST /acmerescue/validaatendimento — retorna true/false */
    public void validarAtendimento() {
        System.out.println("\n══ Validar Atendimento ═════════════════");
        System.out.print("Código do atendimento: ");
        long codigo = lerLong();

        String url = URL_SERVIDOR + ENDPOINT_VALIDA_ATENDIMENTO;
        boolean valido = postRetornaBooleano(url, String.valueOf(codigo));
        System.out.println("Atendimento válido: " + valido);
    }

    /** POST /acmerescue/validaequipe — retorna true/false */
    public void validarEquipe() {
        System.out.println("\n══ Validar Equipe ══════════════════════");
        System.out.print("Número da equipe: ");
        long numero = lerLong();

        String url = URL_SERVIDOR + ENDPOINT_VALIDA_EQUIPE;
        boolean valido = postRetornaBooleano(url, String.valueOf(numero));
        System.out.println("Equipe válida: " + valido);
    }

    /** POST /acmerescue/validaequipamento — retorna true/false */
    public void validarEquipamento() {
        System.out.println("\n══ Validar Equipamento ═════════════════");
        System.out.print("ID do equipamento: ");
        long id = lerLong();

        String url = URL_SERVIDOR + ENDPOINT_VALIDA_EQUIPAMENTO;
        boolean valido = postRetornaBooleano(url, String.valueOf(id));
        System.out.println("Equipamento válido: " + valido);
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  2. LISTAGENS  (GET → lista JSON)
    // ═══════════════════════════════════════════════════════════════════════

    /** GET /acmerescue/cadastro/listaeventos */
    public void listarEventos() {
        System.out.println("\n══ Lista de Eventos ════════════════════");
        String url = URL_SERVIDOR + ENDPOINT_LISTA_EVENTOS;
        String resposta = getRetornaString(url);
        imprimirJson(resposta);
    }

    /** GET /acmerescue/cadastro/listaatendimentos */
    public void listarAtendimentos() {
        System.out.println("\n══ Lista de Atendimentos ═══════════════");
        String url = URL_SERVIDOR + ENDPOINT_LISTA_ATENDIMENTOS;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

            System.out.println("HTTP status: " + response.statusCode());
            List<Atendimento> atendimentos = AtendimentoUtils.toList(response.body());
            if (atendimentos.isEmpty()) {
                System.out.println("Nenhum atendimento cadastrado.");
            } else {
                atendimentos.forEach(System.out::println);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("[ERRO] " + e.getMessage());
        }
    }

    /** GET /acmerescue/cadastro/listaequipes */
    public void listarEquipes() {
        System.out.println("\n══ Lista de Equipes ════════════════════");
        String url = URL_SERVIDOR + ENDPOINT_LISTA_EQUIPES;
        String resposta = getRetornaString(url);
        imprimirJson(resposta);
    }

    /** GET /acmerescue/cadastro/listaequipamentos */
    public void listarEquipamentos() {
        System.out.println("\n══ Lista de Equipamentos ═══════════════");
        String url = URL_SERVIDOR + ENDPOINT_LISTA_EQUIPAMENTOS;
        String resposta = getRetornaString(url);
        imprimirJson(resposta);
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  3. CADASTROS  (POST → booleano / objeto)
    // ═══════════════════════════════════════════════════════════════════════

    /** POST /acmerescue/cadastro/cadevento — retorna true/false */
    public void cadastrarEvento() {
        System.out.println("\n══ Cadastrar Evento ════════════════════");
        System.out.print("Código: ");       long codigo = lerLong();
        System.out.print("Descrição: ");    String descricao = lerTexto();
        System.out.print("Data (yyyy-MM-dd): "); String data = lerTexto();
        System.out.print("Latitude: ");     double lat = lerDouble();
        System.out.print("Longitude: ");    double lon = lerDouble();

        String json = String.format(
            "{\"codigo\":%d,\"descricao\":\"%s\",\"data\":\"%s\",\"latitude\":%f,\"longitude\":%f}",
            codigo, descricao, data, lat, lon
        );

        String url = URL_SERVIDOR + ENDPOINT_CAD_EVENTO;
        boolean sucesso = postRetornaBooleano(url, json);
        System.out.println("Evento cadastrado: " + sucesso);
    }

    /** POST /acmerescue/cadastro/cadatendimento — retorna true/false */
    public void cadastrarAtendimento() {
        System.out.println("\n══ Cadastrar Atendimento ═══════════════");
        System.out.print("Código do atendimento: ");  long cod = lerLong();
        System.out.print("Data de início (yyyy-MM-dd): "); String data = lerTexto();
        System.out.print("Duração (dias): ");           int dur = lerInt();
        System.out.print("Código do evento: ");         long codEvento = lerLong();

        String json = String.format(
            "{\"cod\":%d,\"dataInicio\":\"%s\",\"duracao\":%d,\"codigoEvento\":%d}",
            cod, data, dur, codEvento
        );

        String url = URL_SERVIDOR + ENDPOINT_CAD_ATENDIMENTO;
        boolean sucesso = postRetornaBooleano(url, json);
        System.out.println("Atendimento cadastrado: " + sucesso);
    }

    /** POST /acmerescue/cadastro/cadvinculo — retorna true/false */
    public void vincularEquipamento() {
        System.out.println("\n══ Vincular Equipamento à Equipe ═══════");
        System.out.print("ID do equipamento: ");  long idEquipamento = lerLong();
        System.out.print("Número da equipe: ");   long numEquipe = lerLong();

        String json = String.format(
            "{\"idEquipamento\":%d,\"numeroEquipe\":%d}",
            idEquipamento, numEquipe
        );

        String url = URL_SERVIDOR + ENDPOINT_CAD_VINCULO;
        boolean sucesso = postRetornaBooleano(url, json);
        System.out.println("Vínculo criado: " + sucesso);
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  4. OPERAÇÕES DE ATENDIMENTO
    // ═══════════════════════════════════════════════════════════════════════

    /** POST /acmerescue/processo/alocaatendimento — retorna true/false */
    public void alocaAtendimento() {
        System.out.println("\n══ Alocar Equipe em Atendimento ════════");
        System.out.print("Código do atendimento: ");
        long cod = lerLong();

        String json = String.valueOf(cod);
        String url = URL_SERVIDOR + ENDPOINT_ALOCA_ATENDIMENTO;
        boolean sucesso = postRetornaBooleano(url, json);
        System.out.println("Alocação realizada: " + sucesso);
    }

    /** GET /acmerescue/atendimento/:status */
    public void listarAtendimentosPorStatus() {
        System.out.println("\n══ Atendimentos por Status ═════════════");
        System.out.println("Status disponíveis: PENDENTE | EXECUTANDO | FINALIZADO | CANCELADO");
        System.out.print("Status: ");
        String status = lerTexto().toUpperCase();

        String url = URL_SERVIDOR + ENDPOINT_ATENDIMENTO_STATUS + status;
        String resposta = getRetornaString(url);
        imprimirJson(resposta);
    }

    /** POST /acmerescue/atendimento/:codigo — atualiza status, retorna cadastro completo */
    public void atualizarStatusAtendimento() {
        System.out.println("\n══ Atualizar Status de Atendimento ═════");
        System.out.print("Código do atendimento: ");
        long codigo = lerLong();
        System.out.println("Novo status: PENDENTE | EXECUTANDO | FINALIZADO | CANCELADO");
        System.out.print("Status: ");
        String novoStatus = lerTexto().toUpperCase();

        String json = String.format("{\"status\":\"%s\"}", novoStatus);
        String url = URL_SERVIDOR + ENDPOINT_ATENDIMENTO_STATUS + codigo;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .setHeader("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("HTTP status: " + response.statusCode());
            System.out.println("Atendimento atualizado:");
            imprimirJson(response.body());
        } catch (IOException | InterruptedException e) {
            System.out.println("[ERRO] " + e.getMessage());
        }
    }

    /** GET /acmerescue/atendimento/custo/:codigo */
    public void consultarCustoAtendimento() {
        System.out.println("\n══ Custo de Atendimento ════════════════");
        System.out.print("Código do atendimento: ");
        long codigo = lerLong();

        String url = URL_SERVIDOR + ENDPOINT_ATENDIMENTO_CUSTO + codigo;
        String resposta = getRetornaString(url);
        imprimirJson(resposta);
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  5. CONSULTAS DE EQUIPE
    // ═══════════════════════════════════════════════════════════════════════

    /** GET /acmerescue/equipe/atendimento/:numero */
    public void listarAtendimentosEquipe() {
        System.out.println("\n══ Atendimentos de uma Equipe ══════════");
        System.out.print("Número da equipe: ");
        long numero = lerLong();

        String url = URL_SERVIDOR + ENDPOINT_EQUIPE_ATENDIMENTOS + numero;
        String resposta = getRetornaString(url);
        imprimirJson(resposta);
    }

    /** GET /acmerescue/equipe/equipamento/:numero */
    public void listarEquipamentosEquipe() {
        System.out.println("\n══ Equipamentos de uma Equipe ══════════");
        System.out.print("Número da equipe: ");
        long numero = lerLong();

        String url = URL_SERVIDOR + ENDPOINT_EQUIPE_EQUIPAMENTOS + numero;
        String resposta = getRetornaString(url);
        imprimirJson(resposta);
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  MÉTODOS AUXILIARES DE HTTP
    // ═══════════════════════════════════════════════════════════════════════

    /**
     * Faz GET na URL e retorna o corpo da resposta como String.
     */
    private String getRetornaString(String url) {
        System.out.println("URL: " + url);
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("HTTP status: " + response.statusCode());
            return response.body();
        } catch (IOException | InterruptedException e) {
            System.out.println("[ERRO] " + e.getMessage());
            return "[]";
        }
    }

    /**
     * Faz POST com corpo em texto/JSON e retorna o booleano da resposta.
     * Suporta tanto corpo numérico simples quanto JSON completo.
     */
    private boolean postRetornaBooleano(String url, String corpo) {
        System.out.println("URL: " + url);
        try {
            // Detecta se o corpo é JSON estruturado ou valor simples
            String contentType = corpo.trim().startsWith("{") ? "application/json" : "text/plain";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString(corpo))
                    .setHeader("Content-Type", contentType)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("HTTP status: " + response.statusCode());

            String body = response.body().trim().toLowerCase();
            return Boolean.parseBoolean(body) || body.equals("1") || body.equals("true");
        } catch (IOException | InterruptedException e) {
            System.out.println("[ERRO] " + e.getMessage());
            return false;
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  MÉTODOS AUXILIARES DE ENTRADA E FORMATAÇÃO
    // ═══════════════════════════════════════════════════════════════════════

    /** Imprime JSON linha a linha (formatação simples sem dependências extras). */
    private void imprimirJson(String json) {
        if (json == null || json.isBlank()) {
            System.out.println("(sem dados)");
            return;
        }
        // Indentação básica para facilitar leitura no console
        int indent = 0;
        boolean inString = false;
        StringBuilder sb = new StringBuilder();
        for (char c : json.toCharArray()) {
            if (c == '"') inString = !inString;
            if (!inString && (c == '{' || c == '[')) {
                sb.append(c).append('\n').append("  ".repeat(++indent));
            } else if (!inString && (c == '}' || c == ']')) {
                sb.append('\n').append("  ".repeat(--indent)).append(c);
            } else if (!inString && c == ',') {
                sb.append(c).append('\n').append("  ".repeat(indent));
            } else {
                sb.append(c);
            }
        }
        System.out.println(sb);
    }

    private int lerInt() {
        try { return Integer.parseInt(entrada.nextLine().trim()); }
        catch (NumberFormatException e) { return -1; }
    }

    private long lerLong() {
        try { return Long.parseLong(entrada.nextLine().trim()); }
        catch (NumberFormatException e) { return -1L; }
    }

    private double lerDouble() {
        try { return Double.parseDouble(entrada.nextLine().trim()); }
        catch (NumberFormatException e) { return 0.0; }
    }

    private String lerTexto() {
        return entrada.nextLine().trim();
    }
}