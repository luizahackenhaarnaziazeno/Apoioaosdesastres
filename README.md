<h1 align="center">🆘 Apoioaodesastres</h1>

<p align="center">
  <i>Este é o módulo backend do sistema de gerenciamento da ACMERescue, uma empresa dedicada ao atendimento de desastres naturais (como ciclones, enchentes e terremotos).</i>
</p>

## Arquitetura e Tecnologias
* O sistema deve ser implementado como um monolito utilizando a arquitetura CLEAN.

## Entidades Principais
* **Evento**: Possui um código único, descrição, data e localização baseada em latitude e longitude.
* **Equipe**: Possui um número único, quantidade de membros e localização. Uma equipe pode possuir vários equipamentos e só consegue atender eventos que estejam a um raio de até 5.000 quilômetros de distância.
* **Equipamento**: Possui identificador único, nome e custo diário.
* **Atendimento**: Vincula um evento específico e pode ter uma equipe alocada. Possui controle de estados (PENDENTE, EXECUTANDO, FINALIZADO ou CANCELADO). O cálculo de custo de um atendimento considera o deslocamento, a duração da equipe e o custo diário dos equipamentos.

## Endpoints da API

Abaixo estão as rotas disponíveis no sistema. Para que a API seja testada adequadamente, o banco de dados deve ser previamente populado com no mínimo 5 eventos, 5 equipes, 10 equipamentos e 3 atendimentos.


### Validações
* <font color="red">POST /acmerescue/validaevento</font>: Retorna se o evento é válido recebendo o código do evento.
* <font color="red">POST /acmerescue/validaatendimento</font>: Retorna se o atendimento é válido recebendo o código do atendimento.
* <font color="red">POST /acmerescue/validaequipe</font>: Retorna se a equipe é válida recebendo o número da equipe.
* <font color="red">POST /acmerescue/validaequipamento</font>: Retorna se o equipamento é válido recebendo o identificador do equipamento.

### Cadastros e Listagens
* <font color="red">GET /acmerescue/cadastro/listaeventos</font>: Lista com todos os eventos cadastrados.
* <font color="red">GET /acmerescue/cadastro/listaatendimentos</font>: Lista com todos os atendimentos cadastrados.
* <font color="red">GET /acmerescue/cadastro/listaequipes</font>: Lista com todas as equipes cadastradas.
* <font color="red">GET /acmerescue/cadastro/listaequipamentos</font>: Lista com todos os equipamentos cadastrados.
* <font color="red">POST /acmerescue/cadastro/cadvinculo</font>: Vincula um equipamento a uma equipe.
* <font color="red">POST /acmerescue/cadastro/cadevento</font>: Cadastra um novo evento.
* <font color="red">POST /acmerescue/cadastro/cadatendimento</font>: Cadastra um novo atendimento.

### Processos e Consultas de Atendimentos
* <font color="red">POST /acmerescue/processo/alocaatendimento</font>: Aloca um atendimento a alguma equipe.
* <font color="red">GET /acmerescue/atendimento/:status</font>: Retorna a lista com todos os atendimentos filtrados por status (PENDENTE, EXECUTANDO, FINALIZADO, CANCELADO).
* <font color="red">POST /acmerescue/atendimento/:codigo</font>: Atualiza o status de um atendimento específico.
* <font color="red">GET /acmerescue/atendimento/custo/:codigo</font>: Retorna o custo de um atendimento de um evento.

### Consultas de Equipes
* <font color="red">GET /acmerescue/equipe/atendimento/:numero</font>: Retorna a lista de atendimentos da equipe informada.
* <font color="red">GET /acmerescue/equipe/equipamento/:numero</font>: Retorna a lista de equipamentos vinculados à equipe informada.



