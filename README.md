# 💳 Projeto Credito Consulta

Projeto desenvolvido como desafio técnico, composto por um back-end em **Java com Spring Boot** e um front-end em **Angular 17**, permitindo consulta, gerenciamento e visualização de créditos fiscais. O projeto conta com integração a banco de dados PostgreSQL e comunicação assíncrona utilizando **Apache Kafka**.

---

## 🧩 Tecnologias Utilizadas

- **Back-end:** Java 17, Spring Boot, Spring Data JPA, Spring Web, Spring Kafka, Springdoc OpenAPI (Swagger).
- **Front-end:** Angular 17, Angular Material, RxJS.
- **Banco de Dados:** PostgreSQL.
- **Mensageria:** Apache Kafka, Zookeeper.
- **Infraestrutura:** Docker e Docker Compose.

---

## 🚀 Funcionalidades do Projeto

**Backend (Spring Boot):**

- 🔎 Buscar crédito por número do crédito
- 🔍 Buscar crédito por número da NFS-e
- 📋 Listar todos os créditos cadastrados
- ➕ Criar novo crédito
- ✏️ Atualizar crédito existente
- ❌ Deletar crédito
- 📦 Publicar eventos no tópico Kafka `creditos-eventos` nas operações de busca
- 📄 Documentação completa e interativa das APIs via Swagger

**Frontend (Angular):**

- 🧾 Tela de busca por número de crédito ou número da NFS-e
- 📊 Exibição dos resultados em tabela responsiva
- 📌 Visualização de detalhes do crédito via modal
- ✏️ Ações de edição com atualização automática
- ❌ Ações de exclusão com feedback visual
- ⚠️ Mensagens amigáveis de erro e validação
- 📱 Responsividade para dispositivos móveis

---

## 📄 Documentação da API (Swagger)

Após subir a aplicação completa ou apenas o backend, acesse diretamente pelo navegador a URL da documentação interativa: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Aqui você encontra detalhes completos de cada rota, parâmetros e respostas esperadas.

---

## 📡 Comunicação com Apache Kafka

O projeto utiliza Apache Kafka para garantir maior eficiência na comunicação assíncrona, desacoplando eventos das requisições HTTP. A cada requisição GET realizada na busca de créditos (seja por número de crédito ou número da NFS-e), o sistema publica automaticamente um evento no tópico Kafka chamado: **creditos-eventos**.

Essa abordagem é ideal para análises posteriores, processamento assíncrono, auditorias e logs distribuídos.

---

## 🐳 Como rodar o projeto com Docker

O projeto pode ser executado de três maneiras diferentes utilizando Docker e Docker Compose, conforme sua necessidade.

### 🔹 Rodando apenas o Front-end (Angular):

Para executar somente o Front-end, você encontra o arquivo Docker Compose dentro da pasta **`app-credito-consulta`**.

Entre nessa pasta e siga as orientações disponíveis lá para iniciar o Front-end isoladamente.

### 🔹 Rodando apenas o Back-end (Spring Boot):

Para executar somente o Back-end com todos os serviços necessários (PostgreSQL e Kafka), você encontra um Docker Compose específico dentro da pasta **`backend-credito-consulta`**.

Entre nessa pasta e siga as instruções específicas disponíveis lá para subir o back-end e todos os serviços relacionados de forma isolada.

### 🔹 Rodando o projeto completo (Frontend + Backend + Banco + Kafka):

Se você quiser executar todo o projeto de uma única vez, ou seja, Front-end, Back-end, Banco de Dados PostgreSQL e Kafka integrados em uma única rede Docker, utilize o arquivo Docker Compose que está disponível diretamente na **raiz do projeto**.

### 🚀 Executando o projeto completo

1. Abra um terminal na **raiz do repositório**
2. Execute:

```bash
docker-compose up --build

Serviço | URL
🖥️ Front-end Angular | http://localhost:4200
📚 Swagger (API Backend) | http://localhost:8080/swagger-ui.html


```
