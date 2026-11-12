# Flow Management API

**API REST para gerenciamento de usuários, produtos, categorias e pedidos.**

![Java](https://img.shields.io/badge/Java-25-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.2-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![H2](https://img.shields.io/badge/Database-H2-09476B?style=for-the-badge&logo=h2&logoColor=white)

[Repositório do projeto](https://github.com/nathaliadebellis/flow-management-api) · [Reportar problema](https://github.com/nathaliadebellis/flow-management-api/issues)

---

## Sobre o projeto

O **Flow Management API** é uma aplicação backend desenvolvida em Java com Spring Boot para gerenciar operações relacionadas a usuários, produtos, categorias e pedidos.

A aplicação disponibiliza uma API REST, utiliza Spring Data JPA e Hibernate para persistência de dados e organiza o código em camadas, separando as responsabilidades de cada componente.

O projeto faz parte do meu processo de aprofundamento em desenvolvimento backend, com aplicação prática de conceitos de Programação Orientada a Objetos e do ecossistema Spring.

## Funcionalidades

Atualmente, a API disponibiliza as seguintes operações:

- **Usuários:** listar, consultar por ID, cadastrar, atualizar e excluir.
- **Categorias:** listar e consultar por ID.
- **Produtos:** listar e consultar por ID.
- **Pedidos:** listar e consultar por ID.

> No estado atual do projeto, as operações de escrita estão implementadas para usuários. Categorias, produtos e pedidos disponibilizam operações de consulta.

## Modelo de domínio

O sistema representa um domínio de comércio eletrônico, composto pelas seguintes entidades e relacionamentos:

- **User:** representa os usuários cadastrados. Um usuário pode possuir vários pedidos.
- **Order:** representa um pedido realizado por um usuário. Um pedido pode conter vários itens e possuir um pagamento.
- **OrderItem:** representa os itens de um pedido, relacionando o pedido a um produto.
- **Product:** representa os produtos disponíveis e pode estar associado a diferentes categorias.
- **Category:** representa as categorias dos produtos.
- **Payment:** representa as informações de pagamento de um pedido.

O status de cada pedido é representado pela enumeração `OrderStatus`:

- `WAITING_PAYMENT` — aguardando pagamento.
- `PAID` — pagamento confirmado.
- `SHIPPED` — pedido enviado.
- `DELIVERED` — pedido entregue.
- `CANCELED` — pedido cancelado.

## Tecnologias utilizadas

- **Java 25:** linguagem de programação utilizada no desenvolvimento.
- **Spring Boot 4.1.2:** estrutura principal da aplicação.
- **Spring Web MVC:** construção dos endpoints REST.
- **Spring Data JPA:** abstração do acesso e da persistência de dados.
- **Hibernate:** implementação de mapeamento objeto-relacional.
- **Jakarta Persistence:** especificação utilizada no mapeamento das entidades.
- **H2 Database:** banco de dados em memória utilizado no perfil de teste.
- **PostgreSQL Driver:** driver JDBC incluído nas dependências do projeto.
- **Maven:** gerenciamento de dependências e automação do build.
- **JUnit 5:** suporte aos testes automatizados.

## Arquitetura e organização

A aplicação está organizada em camadas para separar as responsabilidades e facilitar a manutenção e a evolução do código.

O fluxo geral de uma requisição é:

```text
Cliente HTTP
    |
    v
Resources / Controllers
    |
    v
Services
    |
    v
Repositories
    |
    v
Banco de dados
```

### Estrutura de pacotes

```text
com.flowmanagement
|
+-- config
|   +-- TestConfig.java
|
+-- entities
|   +-- enums
|   |   +-- OrderStatus.java
|   |
|   +-- pk
|   |   +-- OrderItemPK.java
|   |
|   +-- Category.java
|   +-- Order.java
|   +-- OrderItem.java
|   +-- Payment.java
|   +-- Product.java
|   +-- User.java
|
+-- repositories
|   +-- CategoryRepository.java
|   +-- OrderItemRepository.java
|   +-- OrderRepository.java
|   +-- ProductRepository.java
|   +-- UserRepository.java
|
+-- resources
|   +-- exceptions
|   |   +-- ResourceExceptionHandler.java
|   |   +-- StandardError.java
|   |
|   +-- CategoryResource.java
|   +-- OrderResource.java
|   +-- ProductResource.java
|   +-- UserResource.java
|
+-- services
|   +-- exception
|   |   +-- DatabaseException.java
|   |   +-- ResourceNotFoundException.java
|   |
|   +-- CategoryService.java
|   +-- OrderService.java
|   +-- ProductService.java
|   +-- UserService.java
|
+-- FlowmanagementApplication.java
```

### Responsabilidades das camadas

**Entities**

Contêm as classes que representam as entidades do domínio e seus relacionamentos.

**Repositories**

Abstraem o acesso aos dados por meio das interfaces do Spring Data JPA.

**Services**

Concentram as operações da aplicação e fazem a intermediação entre os recursos REST e os repositórios.

**Resources**

Recebem as requisições HTTP e disponibilizam os endpoints da API.

**Exceptions**

Centralizam o tratamento de exceções e a padronização das respostas de erro.

**Config**

Agrupa configurações da aplicação e a carga inicial de dados utilizada no perfil de teste.

## Endpoints

A aplicação utiliza o endereço base:

```text
http://localhost:8080
```

### Usuários — `/users`

- `GET /users` — lista os usuários.
- `GET /users/{id}` — consulta um usuário pelo ID.
- `POST /users` — cadastra um usuário.
- `PUT /users/{id}` — atualiza um usuário.
- `DELETE /users/{id}` — exclui um usuário.

### Categorias — `/categories`

- `GET /categories` — lista as categorias.
- `GET /categories/{id}` — consulta uma categoria pelo ID.

### Produtos — `/products`

- `GET /products` — lista os produtos.
- `GET /products/{id}` — consulta um produto pelo ID.

### Pedidos — `/orders`

- `GET /orders` — lista os pedidos.
- `GET /orders/{id}` — consulta um pedido pelo ID.

## Tratamento de exceções

O tratamento de exceções é centralizado na classe `ResourceExceptionHandler`, utilizando a anotação `@ControllerAdvice`.

As exceções tratadas atualmente são:

- **`ResourceNotFoundException` — HTTP 404:** utilizada quando um recurso solicitado não é encontrado.
- **`DatabaseException` — HTTP 400:** utilizada para erros relacionados a operações no banco de dados.

As respostas seguem o formato definido pela classe `StandardError`, que contém:

- Data e hora;
- Código HTTP;
- Descrição do erro;
- Mensagem;
- Caminho da requisição.

## Como executar o projeto

### Pré-requisitos

- JDK 25;
- Git.

O projeto inclui Maven Wrapper, portanto não é necessário instalar o Maven separadamente.

### 1. Clone o repositório

```bash
git clone https://github.com/nathaliadebellis/flow-management-api.git
```

### 2. Acesse a pasta do projeto

```bash
cd flow-management-api
```

### 3. Execute a aplicação

**Windows:**

```bash
mvnw.cmd spring-boot:run
```

**Linux ou macOS:**

```bash
./mvnw spring-boot:run
```

Após a inicialização, a API estará disponível em:

```text
http://localhost:8080
```

## Banco de dados H2

O perfil `test` está configurado como ativo em `application.properties`.

Nesse ambiente, a aplicação utiliza o H2 em memória e carrega dados de exemplo por meio da classe `TestConfig`.

O console do banco pode ser acessado em:

```text
http://localhost:8080/h2-console
```

### Configurações de conexão

- **JDBC URL:** `jdbc:h2:mem:testdb`
- **User Name:** `sa`
- **Password:** deixar em branco.

Os dados são mantidos apenas durante a execução da aplicação. Ao reiniciá-la, a carga inicial é executada novamente.

## Testes automatizados

Para executar os testes:

**Windows:**

```bash
mvnw.cmd test
```

**Linux ou macOS:**

```bash
./mvnw test
```

Atualmente, o projeto possui um teste de contexto Spring Boot (`contextLoads`), que verifica se o contexto da aplicação consegue ser iniciado.

A ampliação da cobertura de testes está prevista como uma das próximas etapas de evolução.

## Conceitos aplicados

Durante o desenvolvimento, foram trabalhados conceitos e práticas relacionados a:

- Programação Orientada a Objetos com Java;
- Encapsulamento e relacionamentos entre entidades;
- Composição e associações entre objetos;
- Enumerações;
- Interfaces e abstração;
- Arquitetura em camadas;
- Injeção de dependência;
- Desenvolvimento de APIs REST;
- Persistência de dados com JPA e Hibernate;
- Tratamento de exceções;
- Consultas e operações com banco de dados;
- Versionamento de código com Git.

## Próximas melhorias

O projeto permanece em desenvolvimento. As próximas evoluções planejadas incluem:

- [ ] Implementar operações de escrita para categorias, produtos e pedidos.
- [ ] Adicionar validações dos dados recebidos pela API.
- [ ] Ampliar a cobertura de testes unitários e de integração.
- [ ] Documentar os endpoints com OpenAPI/Swagger.
- [ ] Configurar e validar a persistência em PostgreSQL.
- [ ] Revisar configurações e dados de exemplo para preparar a aplicação para produção.

---

## Autoria

**Nathalia De Bellis Gomes Anselmo**

Estudante de Sistemas de Informação | Desenvolvimento Backend

- [GitHub](https://github.com/nathaliadebellis)
- [Flow Management API — Repositório](https://github.com/nathaliadebellis/flow-management-api)
