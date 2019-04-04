# INSTRUÇÕES


Por se tratar de microservices dividi o projeto em 5 que são:

app_back_gateway

Utlizei o Zuul para criar o gateway do projeto

app_back_auth 

Projeto responsavel pela geração do token JWT

app_back_store
app_back_order
app_back_payment  


# Subindo o projeto 

1.  Realizar o clone do projeto 
2.  Rodar o comando mvn install no projeto principal
3.  Criar o banco invillia no postgres 
4.  Atualizar o application.proprerties dos projeto  app_back_store , app_back_order,app_back_payment
5.  Na sua IDE você pode subir os projetos executando a classe principal de cada um ou acessar a pasta target do projeto via cmd e executar o comando "java -jar {jardoprojeto}"

# Subindo o projeto no Docker

1.  Realizar o clone do projeto 
2.  Rodar o comando mvn install no projeto principal
3.  Acessar a pasta de cada projeto e criar a imagem de cada um utilizando o comando docker build, Ex:    docker build -t app gateway-docker
4.  Acessar a pasta raiz do projeto e rodar  o comando docker-compose up.

# Considerações

- Os testes de unitarios e integração deverão ser revisados no projeto.



