package apoioaosdesastres.apoioaosdesastres;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.util.*;

import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Atendimento;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Equipamento;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Equipe;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.entidades.Evento;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.interfaces.IEventoJpaItfRep;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.EquipeRepoJpaImpl;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.EventoRepoJpaImpl;
import apoioaosdesastres.apoioaosdesastres.backend.persistencia.EquipamentoRepoJpaImpl;
import apoioaosdesastres.apoioaosdesastres.ApoioaosdesastresApplication;


class ApoioaosdesastresApplicationTests {

    private EventoRepoJpaImpl eventoRepoJpaImpl;

    
    @Test
    void contextLoads() {
        ApoioaosdesastresApplication applicationTests = new ApoioaosdesastresApplication();
        // Removed recursive call to avoid StackOverflowError
    }



    @Test
    void TesteGetEConstrutorEvento() {
        long expectedCodigo = 12345L;
        String expectedDescricao = "Evento de Teste";

        Evento evento = new Evento(expectedCodigo, expectedDescricao);

        assertEquals(expectedCodigo, evento.getCodigo());
        assertEquals(expectedDescricao, evento.getDescricao());
    }

    @Test
    void testSetCodigoEvento() {
        Evento evento = new Evento(0, "Teste");
        long newCodigo = 67890L;

        evento.setCodigo(newCodigo);

        assertEquals(newCodigo, evento.getCodigo());
    }

    @Test
    void testSetDescricaoEVento() {
        Evento evento = new Evento(0, "Teste");
        String newDescricao = "Novo Evento";

        evento.setDescricao(newDescricao);

        assertEquals(newDescricao, evento.getDescricao());
    }

    @Test
    void testSetEGetDataEvento() {
        Evento evento = new Evento(0, "Teste");
        String expectedData = "2024-11-21";

        evento.setdata(expectedData);

        assertEquals(expectedData, evento.getdata());
    }

    @Test
    void testSetEGetLatitudeEvento() {
        Evento evento = new Evento(0, "Teste");
        double expectedLatitude = -23.55052;

        evento.setlatitude(expectedLatitude);

        assertEquals(expectedLatitude, evento.getlatitude());
    }

    @Test
    void testSetEGetLongitudeEvento() {
        Evento evento = new Evento(0, "Teste");
        double expectedLongitude = -46.633308;

        evento.setlongitude(expectedLongitude);

        assertEquals(expectedLongitude, evento.getlongitude());
    }

    @Test
    public void testConstructorInjection() {
        // Cria um mock do repositório IEventoJpaItfRep
        IEventoJpaItfRep repositoryMock = mock(IEventoJpaItfRep.class);

        // Cria a instância de EventoRepoJpaImpl usando o repositório mockado
        EventoRepoJpaImpl eventoRepoJpaImpl = new EventoRepoJpaImpl(repositoryMock);

        // Verifica se a instância do repositório foi injetada corretamente
        assertNotNull(eventoRepoJpaImpl);
        assertEquals(repositoryMock, eventoRepoJpaImpl.repository, "O repositório injetado não é o esperado");
    }

    @Test
    public void testGetEventos() {
        // Simula o repositório
        IEventoJpaItfRep repositoryMock = mock(IEventoJpaItfRep.class);
        EventoRepoJpaImpl eventoRepoJpaImpl = new EventoRepoJpaImpl(repositoryMock);

        // Caso 1: Repositório retorna uma lista vazia
        when(repositoryMock.findAll()).thenReturn(Collections.emptyList());

        List<Evento> resultEmpty = eventoRepoJpaImpl.getEventos();
        assertNotNull(resultEmpty);
        assertTrue(resultEmpty.isEmpty(), "A lista de eventos deve estar vazia");

        // Caso 2: Repositório retorna uma lista com eventos
        Evento evento = new Evento(0, "Teste"); // Usando um construtor definido
        List<Evento> eventos = new ArrayList<>();
        eventos.add(evento);

        when(repositoryMock.findAll()).thenReturn(eventos);

        List<Evento> resultNonEmpty = eventoRepoJpaImpl.getEventos();
        assertNotNull(resultNonEmpty);
        assertFalse(resultNonEmpty.isEmpty(), "A lista de eventos não deve estar vazia");
        assertEquals(1, resultNonEmpty.size(), "A lista deve conter 1 evento");
    }

    // Teste para o adicionar equipamento
    @Test
    public void testGetEventoCodigoEventoRepoJpaIml() {
        // Criação do mock do repositório
        IEventoJpaItfRep repositoryMock = mock(IEventoJpaItfRep.class);
        EventoRepoJpaImpl eventoRepoJpaImpl = new EventoRepoJpaImpl(repositoryMock);

        // Caso 1: O repositório encontra o evento com o código fornecido
        Evento evento = new Evento(123L, "Evento de Teste"); // Usando um construtor definido
        evento.setCodigo(123L); // Definindo um código para o evento

        when(repositoryMock.findById(123L)).thenReturn(evento);

        // Chama o método getEventoCodigo com o código que existe
        Evento resultFound = eventoRepoJpaImpl.getEventoCodigo(123L);

        assertNotNull(resultFound, "O evento encontrado não deve ser nulo");
        assertEquals(123L, resultFound.getCodigo(), "O código do evento retornado deve ser igual ao fornecido");

        // Caso 2: O repositório não encontra o evento com o código fornecido
        when(repositoryMock.findById(999L)).thenReturn(null);

        // Chama o método getEventoCodigo com o código que não existe
        Evento resultNotFound = eventoRepoJpaImpl.getEventoCodigo(999L);

        assertNull(resultNotFound, "O evento não encontrado deve retornar nulo");
    }

    private Evento evento;
   

   
    @Test
    public void testGetCod() {
        evento = new Evento(123, "Teste");
        assertEquals(123, evento.getCodigo());
    }
    

    @Test
    public void testGetEvento() {
        Evento eventoTest = new Evento(0, "Teste");
        evento = eventoTest;
        assertEquals(eventoTest, evento);
    }


  
    private EquipamentoRepoJpaImpl equipamentoRepoJpaImpl;

    // Teste de inicialização do Equipamento
    @Test
    void testEquipamentoConstrutor() {
        long expectedId = 1L;
        String expectedNome = "Gerador";
        double expectedCustoDiario = 50.0;

        Equipamento equipamento = new Equipamento(expectedId, expectedNome, expectedCustoDiario);

        assertEquals(expectedId, equipamento.getId());
        assertEquals(expectedNome, equipamento.getNome());
        assertEquals(expectedCustoDiario, equipamento.getCustoDiario());
    }

    // Teste para o ID do Equipamento
    @Test
    void testSetNomeEquipamento() {
        Equipamento equipamento = new Equipamento(1L, "Gerador", 50.0);
        String newNome = "Novo Gerador";

        equipamento.setNome(newNome);

        assertEquals(newNome, equipamento.getNome());
    }

    // Teste para o Custo Diário do Equipamento
    @Test
    void testSetCustoDiarioEquipamento() {
        Equipamento equipamento = new Equipamento(1L, "Gerador", 50.0);
        double newCustoDiario = 60.0;

        equipamento.setCustoDiario(newCustoDiario);

        assertEquals(newCustoDiario, equipamento.getCustoDiario());
    }

    
    // Teste de inicialização da Equipe
    @Test
    void testEquipeConstrutor() {
        long expectedNumero = 1L;
        int expectedQuantidade = 5;
        double expectedLatitude = -23.55052;
        double expectedLongitude = -46.633308;

        Equipe equipe = new Equipe(expectedNumero, expectedQuantidade, expectedLatitude, expectedLongitude);

        assertEquals(expectedNumero, equipe.getNumero());
        assertEquals(expectedQuantidade, equipe.getQuantidade());
        assertEquals(expectedLatitude, equipe.getLatitude());
        assertEquals(expectedLongitude, equipe.getLongitude());
    }

    // Teste para o Número da Equipe
    @Test
    void testSetNumeroEquipe() {
        Equipe equipe = new Equipe(1L, 5, -23.55052, -46.633308);
        long newNumero = 2L;

        equipe.setNumero(newNumero);

        assertEquals(newNumero, equipe.getNumero());
    }

    // Teste para a Quantidade de Equipe
    @Test
    void testSetQuantidadeEquipe() {
        Equipe equipe = new Equipe(1L, 5, -23.55052, -46.633308);
        int newQuantidade = 6;

        equipe.setQuantidade(newQuantidade);

        assertEquals(newQuantidade, equipe.getQuantidade());
    }
    // Teste para a Latitude da Equipe
    @Test
    void testSetLatitudeEquipe() {
        Equipe equipe = new Equipe(1L, 5, -23.55052, -46.633308);
        double newLatitude = -23.55052;

        equipe.setLatitude(newLatitude);

        assertEquals(newLatitude, equipe.getLatitude());
    }

    // Teste para a Longitude da Equipe
    @Test
    void testSetLongitudeEquipe() {
        Equipe equipe = new Equipe(1L, 5, -23.55052, -46.633308);
        double newLongitude = -46.633308;

        equipe.setLongitude(newLongitude);

        assertEquals(newLongitude, equipe.getLongitude());
    }

    @Test
    void testAdicionarEquioamento(){
        Equipe equipe = new Equipe(1L, 5, -23.55052, -46.633308);
        Equipamento equipamento = new Equipamento(1L, "Gerador", 50.0);

        equipe.adicionarEquipamento(equipamento);

        assertTrue(equipe.getEquipamentos().contains(equipamento), "O equipamento deve ser adicionado à equipe");
    }


    @Test
    void testCalcularCustoEquipe() {
        Equipe equipe = new Equipe(1L, 5, -23.55052, -46.633308);
        int duracao = 10;

        double custoEquipe = equipe.calcularCustoEquipe(duracao);

        assertEquals(12500.0, custoEquipe, "O custo da equipe deve ser 12500.0");
    }

    // Teste para calcular o custo dos equipamentos
    @Test
    void testCalcularCustoEquipamentos() {
      Equipe equipe = new Equipe(1L, null, 5, -23.55052, -46.633308);
      Equipamento equipamento1 = new Equipamento(1L, "Gerador", 50.0);
      Equipamento equipamento2 = new Equipamento(2L, "Lanterna", 5.0);
      equipe.adicionarEquipamento(equipamento1);
      equipe.adicionarEquipamento(equipamento2);

      int duracao = 10;

      double custoEquipamentos = equipe.calcularCustoEquipamentos(duracao);

      assertEquals(550.0, custoEquipamentos, "O custo dos equipamentos deve ser 550.0");
    }

    // Teste para calcular o custo do deslocamento
       @Test
       void testCalcularCustoDeslocamento() {
           Equipe equipe = new Equipe(1L, null, 5, -23.55052, -46.633308);
           Equipamento equipamento = new Equipamento(1L, "Gerador", 50.0);
           equipe.adicionarEquipamento(equipamento);

           double eventoLatitude = -22.9068;
           double eventoLongitude = -43.1729;

           double custoDeslocamento = equipe.calcularCustoDeslocamento(eventoLatitude, eventoLongitude);

           assertTrue(custoDeslocamento > 0, "O custo de deslocamento deve ser maior que 0");
   }

    // Teste para calcular a distância entre dois pontos geográficos
    @Test
     void testPodeAtender() {
         Equipe equipe = new Equipe(1L, null, 5, -23.55052, -46.633308);

         double eventoLatitude = -22.9068;
         double eventoLongitude = -43.1729;

         assertTrue(equipe.podeAtender(eventoLatitude, eventoLongitude), "A equipe deve poder atender o evento");

         eventoLatitude = 40.7128;
         eventoLongitude = -74.0060;

         assertFalse(equipe.podeAtender(eventoLatitude, eventoLongitude), "A equipe não deve poder atender o evento");
     }

    // Teste para o método toString
     @Test
     void testToString() {
        Equipe equipe = new Equipe(1L, null, 5, -23.55052, -46.633308);
     String expectedString = "{" +
             " numero=" + equipe.getNumero() +
             ", quantidade=" + equipe.getQuantidade() +
             ", latitude=" + equipe.getLatitude() +
             ", longitude=" + equipe.getLongitude() +
             ", equipamentos=" + equipe.getEquipamentos() +
             "}";
     assertEquals(expectedString, equipe.toString(), "O método toString não retornou a string esperada");
 }

    //  Teste para o adicionar equipamento
    @Test
    void testGetEquipamento() {
        Equipe equipe = new Equipe(1L, null, 5, -23.55052, -46.633308);
       Equipamento equipamento1 = new Equipamento(1L, "Gerador", 50.0);
        Equipamento equipamento2 = new Equipamento(2L, "Lanterna", 5.0);

        equipe.adicionarEquipamento(equipamento1);
        equipe.adicionarEquipamento(equipamento2);

    }
 


    // // @Test
    // // void testGetCodAtendimento() {
    // //     // Criando as instâncias diretamente dentro do teste
    // //     Evento evento = new Evento(1L, "Evento de Teste");
    // //     Equipamento equipamento = new Equipamento(1L, "Equipamento 1", 50.0);
    // //     Equipe equipe = new Equipe(3L, null, 3, -23.55052, -46.633308);
    // //     equipe.adicionarEquipamento(equipamento);
    // //     Atendimento atendimento = new Atendimento(1L, evento, new Date(), 5, "Em andamento");
    // //     atendimento.getEquipes().add(equipe);

    // //     assertEquals(1L, atendimento.getCod());
    // // }

     @Test
     void testGetEventoAtendimento() {
         // Criando as instâncias diretamente dentro do teste
         Evento evento = new Evento(1L, "Evento de Teste");
         Equipamento equipamento = new Equipamento(1L, "Equipamento 1", 50.0);
         Equipe equipe = new Equipe(3L, null, 3, -23.55052, -46.633308);
         equipe.adicionarEquipamento(equipamento);
         Atendimento atendimento = new Atendimento(1L, evento, new Date(), 5, "Em andamento");
         atendimento.getEquipes().add(equipe);

         assertEquals(evento, atendimento.getEvento());
    }

     @Test
     void testGetDatainicio() {
     // Criando as instâncias diretamente dentro do teste
     Evento evento = new Evento(1L, "Evento de Teste");
     Equipamento equipamento = new Equipamento(1L, "Equipamento 1", 50.0);
     Equipe equipe = new Equipe(3L, null, 3, -23.55052, -46.633308);
     equipe.adicionarEquipamento(equipamento);
     Atendimento atendimento = new Atendimento(1L, evento, new Date(), 5, "Em andamento");
     atendimento.getEquipes().add(equipe);
         assertNotNull(atendimento.getDatainicio());
     }

     @Test
     void testGetDuracao() {
         // Criando as instâncias diretamente dentro do teste
         Evento evento = new Evento(1L, "Evento de Teste");
         Equipamento equipamento = new Equipamento(1L, "Equipamento 1", 50.0);
         Equipe equipe = new Equipe(3L, null, 3, -23.55052, -46.633308);
         equipe.adicionarEquipamento(equipamento);
         Atendimento atendimento = new Atendimento(1L, evento, new Date(), 5, "Em andamento");
         atendimento.getEquipes().add(equipe);

         assertEquals(5, atendimento.getDuracao());
     }

     @Test
     void testGetStatus() {
         // Criando as instâncias diretamente dentro do teste
         Evento evento = new Evento(1L, "Evento de Teste");
         Equipamento equipamento = new Equipamento(1L, "Equipamento 1", 50.0);
         Equipe equipe = new Equipe(3L, null, 3, -23.55052, -46.633308);
         equipe.adicionarEquipamento(equipamento);
         Atendimento atendimento = new Atendimento(1L, evento, new Date(), 5, "Em andamento");
         atendimento.getEquipes().add(equipe);

         assertEquals("Em andamento", atendimento.getStatus());
     }

     @Test
     void testSetStatus() {
         // Criando as instâncias diretamente dentro do teste
         Evento evento = new Evento(1L, "Evento de Teste");
         Equipamento equipamento = new Equipamento(1L, "Equipamento 1", 50.0);
         Equipe equipe = new Equipe(3L, null, 3, -23.55052, -46.633308);
         equipe.adicionarEquipamento(equipamento);
         Atendimento atendimento = new Atendimento(1L, evento, new Date(), 5, "Em andamento");
         atendimento.getEquipes().add(equipe);

         atendimento.setStatus("Concluído");
         assertEquals("Concluído", atendimento.getStatus());
     }

     @Test
     void testGetEquipeAtendimento() {
         // Criando uma instância de Equipe
         Equipe equipe = new Equipe(3L, null, 5, -23.55052, -46.633308);  // Parâmetros fictícios para a equipe

         // Criando a instância de Atendimento e atribuindo a equipe
         Atendimento atendimento = new Atendimento(1L, new Evento(1L, "Evento Teste"), new Date(), 5, "Em andamento");
         atendimento.setEquipe(equipe); // Assumindo que existe um setter para a equipe

         // Verificando se o método getEquipe retorna a equipe correta
         assertEquals(equipe, atendimento.getEquipe(), "O método getEquipe deve retornar a equipe correta.");
     }

       @Test
          void testGetEventoCodigo() {
    //     // Criando as instâncias diretamente dentro do teste
           Evento evento = new Evento(1L, "Evento de Teste");
           Equipamento equipamento = new Equipamento(1L, "Equipamento 1", 50.0);
           Equipe equipe = new Equipe(3L, null, 3, -23.55052, -46.633308);
           equipe.adicionarEquipamento(equipamento);
           Atendimento atendimento = new Atendimento(1L, evento, new Date(), 5, "Em andamento");
           atendimento.getEquipes().add(equipe);

           assertEquals(1L, atendimento.getEventoCodigo());
    }


}
