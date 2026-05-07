<h1 align="center">🆘 apoioaodesastres</h1>

<p align="center">
  <i>Módulo de backend para gerenciamento de equipes, equipamentos e atendimentos a eventos (ACMERescue).</i>
</p>

<hr>

## 📍 Endpoints (Módulos Iniciais)

Os endpoints abaixo representam a fase inicial de validação e listagem do sistema.

| Método | Endpoint |
| :---: | :--- |
| <span style="color:red">**POST**</span> | <span style="color:red">`/acmerescue/validaevento`</span> |
| <span style="color:red">**POST**</span> | <span style="color:red">`/acmerescue/validaatendimento`</span> |
| <span style="color:red">**POST**</span> | <span style="color:red">`/acmerescue/validaequipe`</span> |
| <span style="color:red">**POST**</span> | <span style="color:red">`/acmerescue/validaequipamento`</span> |
| <span style="color:red">**GET**</span>  | <span style="color:red">`/acmerescue/cadastro/listaeventos`</span> |
| <span style="color:red">**GET**</span>  | <span style="color:red">`/acmerescue/cadastro/listaatendimentos`</span> |
| <span style="color:red">**GET**</span>  | <span style="color:red">`/acmerescue/cadastro/listaequipes`</span> |
| <span style="color:red">**GET**</span>  | <span style="color:red">`/acmerescue/cadastro/listaequipamentos`</span> |

---

## 📝 Detalhes das Requisições

### 🔍 Validadores (POST)
Verificam a existência ou validade de registros específicos no banco de dados, retornando `true` ou `false`.

**Corpo da Requisição (JSON):**
* **Evento:** `{ "codigo": long }`
* **Atendimento:** `{ "cod": long }`
* **Equipe:** `{ "numero": long }`
* **Equipamento:** `{ "id": long }`

### 📋 Listagens (GET)
Fornecem arrays contendo todos os dados cadastrados para cada entidade.

<details>
<summary><b>Exemplo de Resposta: Lista de Eventos (Clique para expandir)</b></summary>

```json
[{
  "codigo": 101,
  "descricao": "Enchente Regional",
  "data": "2024-11-21",
  "latitude": -30.0277,
  "longitude": -51.2287
}]


---

## 📝 Detalhes das Requisições
