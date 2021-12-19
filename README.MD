# Sicredi Challenge

### Resumo

Solução para o Desafio Técnico do Sicredi.

#### Desafio proposto

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por
votação. A partir disso, você precisa criar uma solução back-end para gerenciar essas sessões de
votação. Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de
uma API REST:

Cadastrar uma nova pauta; Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar
aberta por um tempo determinado na chamada de abertura ou 1 minuto por default); Receber votos dos
associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único
e pode votar apenas uma vez por pauta); Contabilizar os votos e dar o resultado da votação na pauta.

#### Tecnologias utilizadas

Para esse projeto, foram utilizadas as seguintes tecnologias:

* Java 11: Linguagem utilizada no projeto;
* Spring: Inicialização da API e injeção de dependências;
* Gradle: Automação do build e gestão de dependências;
* Swagger: Documentação interativa da API;
* MongoDB: Banco de dados não relacional para a persistência dos dados;
* Docker: Administração de containers

#### Requerimentos
* Java 11
* Docker
* Docker Compose

#### Inicialização do sistema
##### Usando uma IDE
* docker-compose up -d
* Executar o sistema através da IDE
##### Usando somente o Docker(Tópico Kafka não disponível)
docker-compose -f docker-compose-build.yml up --build -d

**Importante** Os comandos descritos nesse documento devem ser executados num terminal aberto na
pasta raiz do projeto.

#### Iniciando os testes

#### URL do Swagger

* [Swagger](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)

#### Administração do MongoDB

O banco poderá ser administrado através do [Mongo Express](http://127.0.0.1:8081/db/db_assembly/).

#### Qualidade do código

A cobertura de testes e outras métricas relacionadas à qualidade do código, podem ser vistas no
[Sonarqube](https://sonarcloud.io/summary/overall?id=dchristofolli_cooperative-assembly) do projeto

#### Kafka

O resultado de cada sessão de votação será enviado pelo Kafka ao tópico "results.topic". O consumo
das mensagens será feito pelo sistema, e as mensagens serão exibidas no log. Eu escrevi um
[post no Medium](https://medium.com/@danielchristofolli/starting-with-apache-kafka-ae20669a8160)
sobre o assunto.

##### Compressão de dados

Foi usada a compressão no formato GZIP nos eventos do Kafka, visando um bom equilíbrio entre consumo
de recursos e espaço em disco

#### Style Guide

A padronização do código foi feita
usando [Google Style Guides](https://google.github.io/styleguide/)

#### Versionamento

O projeto foi versionado pela URL no modelo path, por ter o visual mais limpo e ser mais amigável ao
desenvolvedor.
