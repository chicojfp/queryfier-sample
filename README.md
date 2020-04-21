# queryfier-sample

## Como testar
Baixe o projeto querify do github.com (ele não está em repositório maven);

```shell 
git clone https://github.com/chicojfp/querify.git
```

Compile e instale o projeto:

```shell
cd querify
mvn clean package install 

cd ..
```

Baixe o projeto queryfier-sample do github.com (ele não está em repositório maven);

```shell 
git clone https://github.com/chicojfp/queryfier-sample.git
```

Compile e instale o projeto:

```shell
cd querifier-sample
mvn clean package install 
```

Execute o Projeto e deixe executando.

Será iniciado um servidor web na porta 8080.
Serão disponibilizados 3 serviços:
* ``/cities``
* ``/stater``
* ``/people``

Todos aceitam os métodos `GET`, `POST`, `PUT`, `PATCH` e `DELETE`.

A solução utilizar - parcialmente - o padrão JSON+PATCH para atualização de dados.

```shell
curl -H "Content-Type: application/json" -X GET http://localhost:8080/states/ -# | python -m json.tool

curl -H "Content-Type: application/json" -X GET http://localhost:8080/cities/ -# | python -m json.tool

curl -H "Content-Type: application/json" -X GET http://localhost:8080/people/ -# | python -m json.tool
```

## Exemplos de Uso:

Adicionar pessoas

```shell
curl -d '{ "name":"Brasileiro de Brito"}' -H "Content-Type: application/json" -X POST http://localhost:8080/people
curl -d '{ "name":"Brasiliense"}' -H "Content-Type: application/json" -X POST http://localhost:8080/people
curl -d '{ "name":"Pernambucano Pitombeiras"}' -H "Content-Type: application/json" -X POST http://localhost:8080/people
curl -d '{ "name":"Paranaense Pereira"}' -H "Content-Type: application/json" -X POST http://localhost:8080/people
curl -d '{ "name":"Paraense de Paula"}' -H "Content-Type: application/json" -X POST http://localhost:8080/people
curl -d '{ "name":"Capixaba de Castro"}' -H "Content-Type: application/json" -X POST http://localhost:8080/people

curl -H "Content-Type: application/json" -X GET http://localhost:8080/people
```

Adicionar Estados:

```shell

curl -d '{ "name":"PR", "country":"1", "capital":"1"}}' -H "Content-Type: application/json" -X POST http://localhost:8080/states
curl -d '{ "name":"PA", "country":"1", "capital":"1"}}' -H "Content-Type: application/json" -X POST http://localhost:8080/states
curl -d '{ "name":"ES", "country":"1", "capital":"1"}}' -H "Content-Type: application/json" -X POST http://localhost:8080/states

curl -H "Content-Type: application/json" -X GET http://localhost:8080/states
```

Alterar um campo dos Estados recém adicionados.
É utilizado o padrão json+patch que o corpo do PATCH recebe uma lista de objetos com os padrões:
* `op`: Indica a operação a ser realizada. Pode ser `add`, `remove`, `move` (Não suportada);
* `from`: Indica o campo - path jason - que deve ser alterado na requisição;
* `patch`: Valor a ser aplicado (ou para o qual deve ser aplicado).


```shell
curl -d '[{"op":"add", "from":"/governor", "patch":"6"}]' -H "Content-Type: application/json" -X PATCH http://localhost:8080/states/3

curl -d '[{"op":"add", "from":"/governor", "patch":"7"}]' -H "Content-Type: application/json" -X PATCH http://localhost:8080/states/4

curl -d '[{"op":"add", "from":"/governor", "patch":"8"}]' -H "Content-Type: application/json" -X PATCH http://localhost:8080/states/5
```

Adiciona algumas capitais:

```shell
curl -d '{"name":"Belém", "state":"4"}' -H "Content-Type: application/json" -X POST http://localhost:8080/cities

curl -d '{"name":"Curitiba", "state":"3"}' -H "Content-Type: application/json" -X POST http://localhost:8080/cities

curl -d '{"name":"Vitória", "state":"5"}' -H "Content-Type: application/json" -X POST http://localhost:8080/cities
```

Altera os estados adicionados com as capitais corretas:

```shell
curl -d '[{"op":"add", "from":"/capital", "patch":"7"}]' -H "Content-Type: application/json" -X PATCH http://localhost:8080/states/4

curl -d '[{"op":"add", "from":"/capital", "patch":"6"}]' -H "Content-Type: application/json" -X PATCH http://localhost:8080/states/3

curl -d '[{"op":"add", "from":"/capital", "patch":"8"}]' -H "Content-Type: application/json" -X PATCH http://localhost:8080/states/5
```

Altera os governadores dos estados:

```shell
curl -d '[{"op":"add", "from":"/governor", "patch":"1"}]' -H "Content-Type: application/json" -X PATCH http://localhost:8080/states/1

curl -d '[{"op":"add", "from":"/governor", "patch":"5"}]' -H "Content-Type: application/json" -X PATCH http://localhost:8080/states/2
```

Remove uma cidade:

```shell
curl -H "Content-Type: application/json" -X DELETE http://localhost:8080/cities/5
```

Exibindo os dados finais:

```shell
curl -H "Content-Type: application/json" -X GET http://localhost:8080/states/ -# | python -m json.tool

curl -H "Content-Type: application/json" -X GET http://localhost:8080/cities/ -# | python -m json.tool

curl -H "Content-Type: application/json" -X GET http://localhost:8080/people/ -# | python -m json.tool
```



