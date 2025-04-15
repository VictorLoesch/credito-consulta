# Backend - Consulta de Créditos

Este projeto é uma API RESTful desenvolvida em **Java 8 + Spring Boot** para realizar consultas de créditos. Ele possui integração com banco de dados **PostgreSQL** e mensageria via **Apache Kafka**, além de documentação com **Swagger** e testes automatizados.

---

## ✅ Tecnologias e Ferramentas Utilizadas

- Java 8
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Flyway (migrations automáticas)
- Apache Kafka
- Swagger (OpenAPI 3)
- JUnit 5 + Mockito (testes unitários)
- Testcontainers (testes de integração com Docker)
- Docker + Docker Compose

---

## 📚 Funcionalidades da API

- CRUD completo de **Créditos**
    - Criar crédito
    - Atualizar crédito
    - Deletar crédito
    - Buscar crédito por ID
    - Buscar todos os créditos
    - Buscar crédito por número de nota fiscal

- Publicação de eventos no Kafka:
    - Quando um crédito é **consultado por ID** (`GET /api/creditos/credito/{id}`)
    - Quando um crédito é **consultado por número de nota fiscal** (`GET /api/creditos/{numero}`)

  A mensagem é enviada para o tópico Kafka `auditoria-credito`.

---

## ⚙️ Kafka - Configurações

- Tópico: `auditoria-credito`
- Broker: `kafka:9092` (usado no ambiente Docker)
- As mensagens são publicadas **apenas em ações de consulta**:
    - `GET /api/creditos/credito/{id}`
    - `GET /api/creditos/{numero}`

As configurações estão no `application.yml` e também no `docker-compose.yml`.

---

## 🧪 Testes Automatizados

- **Testes Unitários**
    - Camada de serviço
    - Camada de controller
    - Validações e exceções

- **Testes de Integração**
    - Utilizando Testcontainers com PostgreSQL

---

## 🧪 Documentação da API

A documentação interativa está disponível via Swagger:

- URL: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🐳 Docker e Docker Compose

O projeto já está preparado para rodar com Docker Compose. Isso facilita o ambiente de desenvolvimento local com PostgreSQL, Kafka e a aplicação.

### Estrutura do Docker Compose

- Serviço `postgres`: banco de dados relacional
- Serviço `kafka`: broker Kafka para mensageria
- Serviço `backend_credito_consulta`: aplicação Java

---

## 🚀 Como executar com Docker Compose

```bash
# 1. Navegar até a raiz do projeto
cd backend-credito-consulta

# 2. Subir todos os serviços (aplicação, banco e Kafka)
docker-compose up --build

# 3. Parar todos os serviços
docker-compose down
