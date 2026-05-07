<div align="center"> 

## 🆘 Apoioaodesastres</h1>

<p align="center">
  <i>Este é o módulo backend do sistema de gerenciamento da ACMERescue, uma empresa dedicada ao atendimento de desastres naturais (como ciclones, enchentes e terremotos).</i>
</p>

## Arquitetura e Tecnologias
* O sistema deve ser implementado como um monolito utilizando a arquitetura CLEAN.

## Entidades Principais
* **Evento**:Possui um código único (long), descrição (String), data (String) e localização baseada em latitude (double) e longitude (double).
* **Equipe**: Possui um número único (long), quantidade de membros (int) e localização (latitude e longitude em double). Uma equipe pode possuir vários equipamentos e só consegue atender eventos que estejam a um raio de até 5.000 quilômetros de distância.
* **Equipamento**: Possui identificador único (long), nome (String) e custo diário (double).
* **Atendimento**: Vincula um evento específico e pode ter uma equipe alocada. Possui controle de estados (PENDENTE, EXECUTANDO, FINALIZADO ou CANCELADO). O cálculo de custo de um atendimento considera o deslocamento, a duração da equipe e o custo diário dos equipamentos.

---

## Endpoints da API
Abaixo estão as rotas disponíveis no sistema.Para que a API seja testada adequadamente, o banco de dados deve ser previamente populado via script com no mínimo 5 eventos, 5 equipes, 10 equipamentos e 3 atendimentos.

### Valida Evento

| Método | Endpoint                                         |
|--------|--------------------------------------------------|
| POST   | `/acmerescue/validaevento`                       |

#### Descrição
Retorna se o evento é válido.

#### Corpo da Requisição

| Parâmetro         | Tipo   | Descrição                                      |
|-------------------|--------|------------------------------------------------|
| `codigo do evento`| long   | Código único do evento a ser validado.         |

#### Resposta (JSON)

| Atributo | Tipo    | Descrição                                      |
|----------|---------|------------------------------------------------|
| Retorno  | boolean | Retorna `true` se o evento for válido, ou `false` caso contrário. |


### Valida Atendimento

| Método | Endpoint                                         |
|--------|--------------------------------------------------|
| POST   | `/acmerescue/validaatendimento`                  |

#### Descrição
Retorna se o atendimento é válido.

#### Corpo da Requisição

| Parâmetro              | Tipo   | Descrição                                           |
|------------------------|--------|-----------------------------------------------------|
| `código do atendimento`| long   | Código único do atendimento a ser validado.         |

#### Resposta (JSON)

| Atributo | Tipo    | Descrição                                      |
|----------|---------|------------------------------------------------|
| Retorno  | boolean | Retorna `true` ou `false`.                     |


### Valida Equipe

| Método | Endpoint                                         |
|--------|--------------------------------------------------|
| POST   | `/acmerescue/validaequipe`                       |

#### Descrição
Retorna se a equipe é válida.

#### Corpo da Requisição

| Parâmetro         | Tipo   | Descrição                                      |
|-------------------|--------|------------------------------------------------|
| `número da equipe`| long   | Número identificador da equipe.                |

#### Resposta (JSON)

| Atributo | Tipo    | Descrição                                      |
|----------|---------|------------------------------------------------|
| Retorno  | boolean | Retorna `true` ou `false`.                     |


### Valida Equipamento

| Método | Endpoint                                         |
|--------|--------------------------------------------------|
| POST   | `/acmerescue/validaequipamento`                  |

#### Descrição
Retorna se o equipamento é válido.

#### Corpo da Requisição

| Parâmetro                    | Tipo   | Descrição                                            |
|------------------------------|--------|------------------------------------------------------|
| `identificador do equipamento`| long  |ID único do equipamento a ser validado.               |

#### Resposta (JSON)

| Atributo | Tipo    | Descrição                                      |
|----------|---------|------------------------------------------------|
| Retorno  | boolean | Retorna `true` ou `false`.                     |


### Lista Eventos

| Método | Endpoint                                         |
|--------|--------------------------------------------------|
| GET    | `/acmerescue/cadastro/listaeventos`              |

#### Descrição
Lista com todos os eventos cadastrados.

#### Parâmetros de Entrada

| Parâmetro | Tipo | Descrição                                      |
|-----------|------|------------------------------------------------|
| Nenhum    | -    | Este endpoint não requer parâmetros de entrada. |

#### Resposta (JSON)
Retorna um array de objetos `[{codigo, descricao, data, ...}]`:

| Atributo   | Tipo   | Descrição                           |
|------------|--------|-------------------------------------|
| `codigo`   | long   | Código único do evento.             |
| `descricao`| string | Descrição do evento.                |
| `data`     | string | Data do evento.                     |
| `latitude` | double | Latitude do evento.                 |
| `longitude`| double | Longitude do evento.                |


### Lista Atendimentos

| Método | Endpoint                                   |
|--------|--------------------------------------------|
| GET    | `/acmerescue/cadastro/listaatendimentos`   |

#### Descrição
Lista com todos os atendimentos cadastrados.

#### Parâmetros de Entrada

| Parâmetro | Tipo | Descrição                                      |
|-----------|------|------------------------------------------------|
| Nenhum    | -    | Este endpoint não requer parâmetros de entrada.|

#### Resposta (JSON)
Retorna um array de objetos `[{cod, dataInicio, duracao, ...}]`:

| Atributo     | Tipo   | Descrição                           |
|--------------|--------|-------------------------------------|
| `cod`        | long   | Código único do atendimento.        |
| `dataInicio` | date   | Data de início do atendimento.      |
| `duracao`    | int    | Duração do atendimento em dias.     |
| `status`     | string | Status do atendimento.              |


### Lista Equipes

| Método | Endpoint                              |
|--------|---------------------------------------|
| GET    |`/acmerescue/cadastro/listaequipes`    |

#### Descrição
Lista com todas as equipes cadastradas.

#### Parâmetros de Entrada

| Parâmetro | Tipo | Descrição                                      |
|-----------|------|------------------------------------------------|
| Nenhum    | -    | Este endpoint não requer parâmetros de entrada.|

#### Resposta (JSON)
Retorna um array de objetos `[{numero, quantidade, ...}]`:

| Atributo     | Tipo   | Descrição                           |
|--------------|--------|-------------------------------------|
| `numero`     | long   | Número único da equipe.             |
| `quantidade` | int    | Quantidade de membros na equipe.    |
| `latitude`   | double | Latitude da equipe.                 |
| `longitude`  | double | Longitude da equipe.                |


### Lista Equipamentos

| Método | Endpoint                                    |
|--------|---------------------------------------------|
| GET    | `/acmerescue/cadastro/listaequipamentos`    |

#### Descrição
Lista com todos os equipamentos cadastrados.

#### Parâmetros de Entrada

| Parâmetro | Tipo | Descrição                                      |
|-----------|------|------------------------------------------------|
| Nenhum    | -    | Este endpoint não requer parâmetros de entrada.|

#### Resposta (JSON)
Retorna um array de objetos `[{id, nome, custoDiario, ...}]`:

| Atributo      | Tipo   | Descrição                           |
|---------------|--------|-------------------------------------|
| `id`          | long   | ID único do equipamento.            |
| `nome`        | string | Nome do equipamento.                |
| `custoDiario` | double | Custo diário do equipamento.        |


### Cadastra Vínculo

| Método | Endpoint                               |
|--------|----------------------------------------|
| POST   | `/acmerescue/cadastro/cadvinculo`      |

#### Descrição
Vincula um equipamento a uma equipe.

#### Corpo da Requisição

| Parâmetro           | Tipo | Descrição                           |
|---------------------|------|-------------------------------------|
| `id do equipamento` | long | ID único do equipamento.            |
| `numero da equipe`  | long | Número identificador da equipe.     |

#### Resposta (JSON)

| Atributo | Tipo    | Descrição                                              |
|----------|---------|--------------------------------------------------------|
| Retorno  | boolean | Retorna se o vínculo teve sucesso (`true` ou `false`). |


### Cadastra Evento

| Método | Endpoint                               |
|--------|----------------------------------------|
| POST   | `/acmerescue/cadastro/cadevento`       |

#### Descrição
Cadastra um novo evento.

#### Corpo da Requisição

| Parâmetro   | Tipo   | Descrição                           |
|-------------|--------|-------------------------------------|
| `codigo`    | long   | Código do evento.                   |
| `descricao` | string | Descrição do evento.                |
| Outros      | -      | Demais atributos do evento.         |

#### Resposta (JSON)

| Atributo | Tipo    | Descrição                                               |
|----------|---------|---------------------------------------------------------|
| Retorno  | boolean | Retorna se o cadastro teve sucesso (`true` ou `false`). |


### Cadastra Atendimento

| Método | Endpoint                                  |
|--------|-------------------------------------------|
| POST   | `/acmerescue/cadastro/cadatendimento`     |

#### Descrição
Cadastra um novo atendimento.

#### Corpo da Requisição

| Parâmetro          | Tipo       | Descrição                           |
|--------------------|------------|-------------------------------------|
| `cod`              | long       | Código do atendimento.              |
| `dataInicio`       | string/date| Data de início do atendimento.      |
| `codigo do evento` | long       | Código do evento vinculado.         |

#### Resposta (JSON)

| Atributo | Tipo    | Descrição                                               |
|----------|---------|---------------------------------------------------------|
| Retorno  | boolean | Retorna se o cadastro teve sucesso (`true` ou `false`). |


### Aloca Atendimento

| Método | Endpoint                                  |
|--------|-------------------------------------------|
| POST   | `/acmerescue/processo/alocaatendimento`   |

#### Descrição
Aloca um atendimento a alguma equipe.

#### Corpo da Requisição

| Parâmetro | Tipo | Descrição                           |
|-----------|------|-------------------------------------|
| `cod`     | long | Código do atendimento a ser alocado.|

#### Resposta (JSON)

| Atributo | Tipo    | Descrição                                                        |
|----------|---------|------------------------------------------------------------------|
| Retorno  | boolean | Retorna se o cadastro/alocação teve sucesso (`true` ou `false`). |


### Filtra Atendimentos por Status

| Método | Endpoint                               |
|--------|----------------------------------------|
| GET    | `/acmerescue/atendimento/:status`      |

#### Descrição
Retorna a lista com todos os atendimentos conforme o status informado.

#### Parâmetros de Entrada

| Parâmetro | Tipo   | Descrição                                              |
|-----------|--------|--------------------------------------------------------|
| `status`  | string | Pode ser: PENDENTE, EXECUTANDO, FINALIZADO, CANCELADO. |

#### Resposta (JSON)
Retorna um array de objetos do tipo atendimento `[{código do atendimento, ..., código do evento, status , ...}]`.


### Atualiza Status do Atendimento

| Método | Endpoint                              |
|--------|---------------------------------------|
| POST   | `/acmerescue/atendimento/:codigo`     |

#### Descrição
Atualizar o status de um atendimento.

#### Corpo da Requisição

| Parâmetro | Tipo   | Descrição                           |
|-----------|--------|-------------------------------------|
| `status`  | string | Novo status do atendimento.         |

#### Resposta (JSON)
Retorna o cadastro completo do atendimento atualizado:

| Atributo                | Tipo | Descrição                              |
|-------------------------|------|----------------------------------------|
| `código do atendimento` | long | Código único do atendimento atualizado.|
| Outros                  | -    | Demais dados completos do atendimento. |


### Calcula Custo do Atendimento

| Método | Endpoint                                   |
|--------|--------------------------------------------|
| GET    | `/acmerescue/atendimento/custo/:codigo`    |

#### Descrição
Retorna o custo de um atendimento de um evento.

#### Parâmetros de Entrada

| Parâmetro | Tipo | Descrição                           |
|-----------|------|-------------------------------------|
| `codigo`  | long | Código do evento.                   |

#### Resposta (JSON)
Retorna um array de objetos contendo os dados de custo `[{código do evento, código do atendimento, número equipe, custo}, ...]`:

| Atributo                | Tipo   | Descrição                                |
|-------------------------|--------|------------------------------------------|
| `código do evento`      | long   | Código do evento.                        |
| `código do atendimento` | long   | Código do atendimento.                   |
| `número equipe`         | long   | Número da equipe alocada.                |
| `custo`                 | double | Valor calculado do custo do atendimento. |


### Atendimentos por Equipe

| Método | Endpoint                                   |
|--------|--------------------------------------------|
| GET    | `/acmerescue/equipe/atendimento/:numero`   |

#### Descrição
Retorna a lista de atendimentos da equipe informada.

#### Parâmetros de Entrada

| Parâmetro | Tipo | Descrição                          |
|-----------|------|------------------------------------|
| `numero`  | long | Número da equipe.                  |

#### Resposta (JSON)
Retorna um array de objetos referentes aos atendimentos da equipe `[{código do atendimento, ...}, ...]`.


### Equipamentos por Equipe

| Método | Endpoint                                   |
|--------|--------------------------------------------|
| GET    | `/acmerescue/equipe/equipamento/:numero`   |

#### Descrição
Retorna a lista de equipamentos da equipe informada.

#### Parâmetros de Entrada

| Parâmetro | Tipo | Descrição                          |
|-----------|------|------------------------------------|
| `numero`  | long | Número da equipe.                  |

#### Resposta (JSON)
Retorna um array de objetos contendo os equipamentos alocados para a equipe `[{id do equipamento, ...}, ...]`.


 ## Linguagem Utilizada:
<div style="display: inline_block"><br>
<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" /> 

# Autora:

| [<img loading="lazy" src="https://avatars.githubusercontent.com/u/142232479?v=4" width=115><br><sub>Luiza Hackenhaar Naziazeno</sub>](https://github.com/luizahackenhaarnaziazeno)|
| :---: |

