# Java_SpringBoot

## learning SpringBot java

O Spring Boot facilita a criação de aplicativos autônomos baseados em Spring de nível de produção que você pode "simplesmente executar".

Temos uma visão opinativa da plataforma Spring e das bibliotecas de terceiros para que você possa começar com o mínimo de confusão. A maioria dos aplicativos Spring Boot precisa de uma configuração mínima do Spring.

Se você estiver procurando informações sobre uma versão específica ou instruções sobre como atualizar de uma versão anterior, confira a seção de notas de versão do projeto em nosso wiki.

## Recursos

* Crie aplicativos Spring independentes

* Incorpore Tomcat, Jetty ou Undertow diretamente (sem necessidade de implantar arquivos WAR)

* Forneça dependências 'starter' opinativas para simplificar sua configuração de compilação

* Configure automaticamente bibliotecas Spring e de terceiros sempre que possível

* Forneça recursos prontos para produção, como métricas, verificações de integridade e configuração externa

* Absolutamente nenhuma geração de código e nenhum requisito para configuração XML


### link da documentaçao:<https://spring.io/projects/spring-boot>


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
