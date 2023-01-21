# Artigo: Algumas considerações sobre @Transactional

Este artigo foi elaborado a partir da pergunta de um aluno:

### Entendendo melhor o uso da annotation @Transactional



Bem, primeiro temos que entender que o básico do uso de transações é que elas devem ser utilizadas toda vez que for necessário fazer modificações no banco de dados (INSERT, UPDATE e DELETE), e na otimização de Queries (no caso com readonly=true), ok?



Um outro detalhe é que, por padrão, os JpaRepositories já introduzem essa annotation nos métodos que executam essas operações não tendo a necessidade explicita de fazer isso, por isso você usa eles e não ocorre erro, mas se vc tentar usar o EntityManager diretamente pra fazer as operações e não usar @Transactional ao fazer alterações no banco, vai dar erro de transação.



Com isso em mente vemos que, utilizando JpaRepositories, ao fazer 1 única operação de escrita no banco, não é necessário inserir essa annotation, então ela só é obrigatória de fato em algumas situações e eu vou te dar exemplos das que já fiz na prática:



Cenário 1: Tenho um método no meu serviço que salva um pedido, os itens do pedido e a forma de pagamento.



Nesse cenário temos 3 entidades envolvidas, logo teremos pelo menos 3 Repositorios diferentes fazendo uma unica operação: salvarPedido, nesse caso devemos obrigatóriamente adicionar o @Transactional, pois se houver uma exception na hora de salvar o pagamento, por exemplo, o Spring fará um rollback e não persistirá nada que foi feito desde o inicio da operação, ou seja, mesmo que tiver havido sucesso ao salvar o pedido e os itens, eles não estarão persistidos pois ocorreu um erro ao salvar o pagamento e quando vc usa o @Transactional nesse método você está indicado que quer fazer tudo em uma unica transação, seguindo assim o principio do ACID.


``
@Transactionalpublic void salvarPedido(Pedido pedido){    
pedidoRepository.save(pedido);    
itemPedidoRepository.save(pedido.getItens());    
pagamentoRepository.save(pedido.getPagamento());
}
``


Você pode utilizar este mesmo raciocínio quando houver qualquer outra operação dentro daquele método que se der erro, deve ser feito um rollback, como por exemplo no cenário onde vc precisa cadastrar um cliente, depois mandar a foto dele para um bucket na nuvem e por fim enviar um email confirmando o cadastro do mesmo. Neste caso, imagine que o cliente foi persistido no banco, mas ocorreu um erro ao tentar salvar a foto dele no bucket, você não quer que o cliente fique sem a foto, correto? Use o @Transactional.

No caso do email, existe aqui uma opção.. digamos que salvar o cliente e a foto são necessários no cadastro e o email é opcional, não vai fazer diferença, é apenas um informativo e não é requisito imprescindível, nesse caso você coloca um try/catch apenas na parte do email, retornando apenas uma mensagem talvez, dizendo que a operação ocorreu com sucesso, entretanto não foi possivel enviar o email, e com esse try catch, você impede de este método lançar uma exception, evitando assim o rollback das informações persistidas.



Cenário 2: Tenho muitas entidades na aplicação e de bancos de dados distintos.



Imagine uma aplicação que faz leitura de bases Oracle e MySQL, os dados dos funcionários na base MySQL e os dados da empresa na base Oracle (acredite, isso não é tão incomum).

Neste caso a annotation @Transactional pode ser obrigatório em quase todos os métodos de persistência, senão poderá ocorrer problemas. Quando eu tenho mais de uma conexão com banco de dados, eu tenho mais de um TransactionManager registrado no contexto do Spring, um para o Oracle (transactionManagerOracle) e um para o MySQL (transactionManagerMySQL), dessa forma, quando eu vou executar uma operação de escrita, por exemplo, de funcionário, eu preciso inserir acima do método salvarFuncionario, a annotation @Transactional com o nome do transactionManager, para que o spring consiga identificar para que banco de dados deve abrir transação:


``exemplo

@Transactional("transactionManagerMySQL")
public void salvarFuncionario(Funcionario fun){    
funcionarioRepository.save(fun);
}

``

Obs: para este tipo de configuração de multiplas conexões, é necessário configurar o transactionManager de cada uma nas suas configurações.