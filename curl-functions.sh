curl -d '{ "name":"Brasileiro de Brito"}' -H "Content-Type: application/json" -X POST http://localhost:8080/people
curl -d '{ "name":"Brasiliense"}' -H "Content-Type: application/json" -X POST http://localhost:8080/people
curl -d '{ "name":"Pernambucano Pitombeiras"}' -H "Content-Type: application/json" -X POST http://localhost:8080/people
curl -d '{ "name":"Paranaense Pereira"}' -H "Content-Type: application/json" -X POST http://localhost:8080/people
curl -d '{ "name":"Paraense de Paula"}' -H "Content-Type: application/json" -X POST http://localhost:8080/people
curl -d '{ "name":"Capixaba de Castro"}' -H "Content-Type: application/json" -X POST http://localhost:8080/people

curl -H "Content-Type: application/json" -X GET http://localhost:8080/people

curl -d '{ "name":"PR", "country":"1", "capital":"1"}}' -H "Content-Type: application/json" -X POST http://localhost:8080/states
curl -d '{ "name":"PA", "country":"1", "capital":"1"}}' -H "Content-Type: application/json" -X POST http://localhost:8080/states
curl -d '{ "name":"ES", "country":"1", "capital":"1"}}' -H "Content-Type: application/json" -X POST http://localhost:8080/states

curl -H "Content-Type: application/json" -X GET http://localhost:8080/states

curl -d '[{"op":"add", "from":"/governor", "patch":"6"}]' -H "Content-Type: application/json" -X PATCH http://localhost:8080/states/3
curl -d '[{"op":"add", "from":"/governor", "patch":"7"}]' -H "Content-Type: application/json" -X PATCH http://localhost:8080/states/4
curl -d '[{"op":"add", "from":"/governor", "patch":"8"}]' -H "Content-Type: application/json" -X PATCH http://localhost:8080/states/5

curl -d '{"name":"Belém", "state":"4"}' -H "Content-Type: application/json" -X POST http://localhost:8080/cities
curl -d '{"name":"Curitiba", "state":"3"}' -H "Content-Type: application/json" -X POST http://localhost:8080/cities
curl -d '{"name":"Vitória", "state":"5"}' -H "Content-Type: application/json" -X POST http://localhost:8080/cities

curl -d '[{"op":"add", "from":"/capital", "patch":"7"}]' -H "Content-Type: application/json" -X PATCH http://localhost:8080/states/4
curl -d '[{"op":"add", "from":"/capital", "patch":"6"}]' -H "Content-Type: application/json" -X PATCH http://localhost:8080/states/3
curl -d '[{"op":"add", "from":"/capital", "patch":"8"}]' -H "Content-Type: application/json" -X PATCH http://localhost:8080/states/5

curl -d '[{"op":"add", "from":"/governor", "patch":"1"}]' -H "Content-Type: application/json" -X PATCH http://localhost:8080/states/1
curl -d '[{"op":"add", "from":"/governor", "patch":"5"}]' -H "Content-Type: application/json" -X PATCH http://localhost:8080/states/2



curl -H "Content-Type: application/json" -X GET http://localhost:8080/states/ -# | python -m json.tool
curl -H "Content-Type: application/json" -X GET http://localhost:8080/cities/ -# | python -m json.tool
curl -H "Content-Type: application/json" -X GET http://localhost:8080/people/ -# | python -m json.tool