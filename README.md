# INSTRUÇÕES

Conforme informado nos requisitos do projeto o mesmo deixa de ser monolitico, sendo assim optei por separar os projetos conforme boas praticas de desenvolvimento de microserviços. Tentei utilizar as melhores praticas de desenvolvimento para construir da melhor forma possivel o meu desafio . Agradeço a oportunidade e espero imensamente poder trabalhar na Invillia.


## **Atividades Realizadas**

Create a Store 
Update a Store information
Retrieve a Store by parameters
Create an Order with items
Create a Payment for an Order
Retrieve an Order by parameters

## **Nice to have features (describe or implement):**

**Asynchronous processing**

Escolhi não implementar está tarefa devido não possuir tanto conhecimento em utilização de mensageria e creio que utilizar os recursos do spring como @ansync não seja a melhor opção para a utilização. 


**Database**

Optei por utilizar o Postgres para os microservice e utilizei o H2 para alguns testes que fiz a construção. 

**Docker**

Fiz a configuração em todos os projetos do docker e criei a compose para o projeto.

**AWS**

Devido ao tempo curto não implementei está feature. Mas se fosse implementar. Criaria uma instancia ec2 e faria a instalação do docker e do docker compose.  Para facilitar ainda mais seria interessante utilizar uma ferramenta com o Jenkins ou Pipelines (GITLAB) para realizar o build e implantação dos microserviços.

**Security**

Implementei a  segurança baseada em tokens JWT que pode ser verificada no projeto app_back_auth,  optei por criar dois perfis fixos e utilizei o @secured do spring para verificação de roles em todos os serviços.

**Swagger**

Realizei a configuração do swagger em todos os projetos


**Clean Code**

Tentei manter o codigo mais clean possivel.


**Obs.**

Criei o arquivo instrucoes_ambiente.md para facilitar  a montagem do ambiente.



# Invillia recruitment challenge

[![Build Status](https://travis-ci.org/shelsonjava/invillia.svg?branch=master)](https://travis-ci.org/shelsonjava/invillia)

![Invillia Logo](https://invillia.com/public/assets/img/logo-invillia.svg)
[Invillia](https://https://www.invillia.com/) - A transformação começa aqui.

The ACME company is migrating their monolithic system to a microservice architecture and you’re responsible to build their MVP (minimum viable product)  .
https://en.wikipedia.org/wiki/Minimum_viable_product

Your challenge is:
Build an application with those features described below, if you think the requirements aren’t detailed enough please leave a comment (portuguese or english) and proceed as best as you can.

You can choose as many features you think it’s necessary for the MVP,  IT’S NOT necessary build all the features, we strongly recommend to focus on quality over quantity, you’ll be evaluated by the quality of your solution.

If you think something is really necessary but you don’t have enough time to implement please at least explain how you would implement it.

## Tasks

Your task is to develop one (or more, feel free) RESTful service(s) to:
* Create a **Store**
* Update a **Store** information
* Retrieve a **Store** by parameters
* Create an **Order** with items
* Create a **Payment** for an **Order**
* Retrieve an **Order** by parameters
* Refund **Order** or any **Order Item**

Fork this repository and submit your code with partial commits.

## Business Rules

* A **Store** is composed by name and address
* An **Order** is composed by address, confirmation date and status
* An **Order Item** is composed by description, unit price and quantity.
* A **Payment** is composed by status, credit card number and payment date
* An **Order** just should be refunded until ten days after confirmation and the payment is concluded.

## Non functional requirements

Your service(s) must be resilient, fault tolerant, responsive. You should prepare it/them to be highly scalable as possible.

The process should be closest possible to "real-time", balancing your choices in order to achieve the expected
scalability.

## Nice to have features (describe or implement):
* Asynchronous processing
* Database
* Docker
* AWS
* Security
* Swagger
* Clean Code
