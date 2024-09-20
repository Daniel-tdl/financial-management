# financial-management

Sistema de gestão financeira

## Especificações técnicas

Arquitetura utilizada: Hexagonal

- Java 21
- SpringBoot 3.2.0
- postgresql

# Autenticação - OAuth

A API utiliza [OAuth2](https://oauth.net/2/) como forma de autenticação/autorização.

## Solicitando tokens de acesso

Para testar a API, é preciso gerar um token na API ( /authenticate ), exemplo a seguir.

#### Dados para gerar o token [POST /authenticate]
| Parâmetro | Descrição |
|---|---|
| `username` | Informar: `username` |
| `password` | Informar: `password` |


# Recursos

#### [POST /account]

Cadastrar conta

#### [GET /account/{id}]

Atualizar conta


####  [PUT /account/{id}]

Alterar a situação da conta


#### [PUT /account/situacao/{situacao}/id/{id}]

Obter a lista de contas a pagar, com filtro de data de vencimento e descrição 

####  [GET /account/list-accounts-payable]

Obter conta filtrando o id


####  [GET /account/get-total-amount-paid-period]

Obter valor total pago por período 


####  [POST /account/upload]

Upload de arquivo csv, na pasta de resourses do projeto tem o exemplo do arquivo csv que pode ser importado as contas em lote.

