# Product - vendas


## Endpoints

- BaseURL: /api
- POST:  create()
- GET: getAll()
- GET /{id} :getById()
- PUT /{id} : update()
- DELETE  /{id} : delete()

## Model

```json
Cliente:

{
  "id": 1,
  "nome": "Alex Green",
  "cpf": "00123564891"
}

```

```json
produto:

{
  "id": 1,
  "descricao": "Impressora",
  "preco": 700
}

```

## Business Rules

- Só é permitido cradastrar cliente com cpf

- Não é possível pesquisar produtos que não estão cadastrados

- Não é possível inserir descricões com menos de 50 caracteres


# Order- vendas

## Endpoints

- BaseURL: /api
- POST:  create()
- GET: getAll()
- GET /{id} :getById()
- PUT /{id} : update()


## Model

```json
Pedido:

{
    "cliente" : 1,
    "total" : 1400,
    "items" : [
        {
            "produto": 1,
            "quantidade" : 2
        }
    ]
}
```

## Business Rules

- Só é possivel a criação do pedido caso o id do cliente exista e o id do produto também.
- Pedido só pode ser criar com o Status