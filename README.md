# DEVinPharmacy LTDA

## Medication Management - Back-End

## Descrição do Sistema

### Tecnologias

O sistema foi desenvolvido utilizando 
[Java](https://www.java.com), 
[PostgreSql](https://www.postgresql.org), e 
[Spring Boot](https://spring.io/projects/spring-boot).

Também foram empregadas tecnologias do Spring Boot como:

- [Lombok](https://projectlombok.org)
- [Spring Validation](https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html)
- [Model Mapper](https://modelmapper.org)
- [Swagger](https://swagger.io)
- [OpenAPI](https://springdoc.org)

Para o monitoramento das respostas do sistema desenvolvido 
foi utilizado o [Postman](https://www.postman.com), para enviar requisições e receber 
as respostas da aplicação.

### O Projeto

O sistema desenvolvido em Java 17, usando o Spring Boot 3.2.0.

Foi criado um esquema REST em camadas para tratar do desenvolvimento da API, e separar as responsabilidades de cada entidade.

O sistema pode ser visto no [vídeo do Projeto](https://drive.google.com/file/d/1UGGAWjVcAkvxQTexvIhv2fo5vwbIcpnu/view?usp=share_link).

E a documentação via Swagger foi gerada no endereço localhost:8080/docs.html, e pode ser vista [Aqui](localhost:8080/docs.html).

### Por dentro do Sistema

- git clone: https://github.com/AlvaroHCP/Modulo2-Projeto.git

Rodar o sistema usando a IDE de sua preferência e pronto. 
Aqui foi utilizado o [IntelliJ](https://www.jetbrains.com/pt-br/idea/).

Agora já se pode enviar e receber requisições do sistema 
utilizando a porta local http://localhost:8080/.

### Serviços e Rotas

#### 1. Inicialização

- /inicializacao (POST): Rota utilizada para alimentar o banco de dados 
com as informações já cadastradas no próprio sistema.

Essa rota alimenta o sistema com todos os dados de Farmácias, 
Medicamentos, e Estoque, para persistˆncia no Banco de Dados.

#### 2. Farmácias

- /farmacias (GET): Rota para consultar todas as farmácias do banco de dados.
- /farmacias/{cnpj} (GET): Rota para consultar uma farrmácia específica pelo cnpj dela no banco de dados.
- /farmacias (POST): Rota para cadastrar uma nova farmácia no sistema. Os dados devem ser enviados como JSON no corpo da requisição Http.

#### 3. Medicamentos

- /medicamentos (GET): Rota para consultar todos os medicamentos do banco de dados.
- /medicamentos (POST): Rota para cadastrar um novo medicamento no sistema. Os dados devem ser enviados como JSON no corpo da requisição Http.

#### 4. Estoques

- /estoque/{cnpj} (GET): Rota para consultar no banco de dados tod o estoque de3 uma farmácia, dado o seu cnpj.
- /estoque (POST): Rota para adicionar uma certa quantidade de medicamento a uma farmácia. Os dados devem ser passados no formato JSON no corpo da requisição.
- /estoque (DELETE): Rota para diminuir uma quantidade de medicamento d uma dada farmácia. Os dados devem ser passados no formato JSON no corpo da requisição.
- /estoque (PUT): Rota que faz a troca de medicamento entre duas farmácias. O medicamento sai do estoque de uma, e é adicionado ao estoque da outra.  Os dados devem ser passados no formato JSON no corpo da requisição.

## O que pode melhorar?

- Melhorar a execução dos serviços para limpar mais o código.
- Criar o relacionamento entre as entidades Farmácia e Endereço de forma a usar @OnetoOne
- Criar classe de serviço que faça o reconhecimento das farmácias mais próximas para transferir medicamentos.


