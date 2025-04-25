# Consulta de Créditos (Front-end)

Aplicação **Angular 17** para consumir uma API REST de créditos, permitindo:

- Consulta por Número do Crédito ou Número da NFS-e
- Exibição de resultados em tabela interativa (Editar / Excluir)
- Criação e Edição de créditos
- Layout responsivo para desktop e mobile
- Containerização via Docker & Nginx

---

## 🛠 Tecnologias

- **Framework**: Angular 17
- **UI**: Angular Material
- **Formulários**: Reactive Forms
- **Linguagem**: TypeScript, SCSS
- **Container**: Docker multi-stage + Nginx

---

## 🖥 Telas

1. **Consulta de Créditos**

   - Campos de busca: Número do Crédito e Número da NFS-e
   - Tabela de resultados com colunas: Crédito, NFS-e, ISSQN, Tipo
   - Ações por linha: Editar (abra modal / rota) e Excluir (confirmação)

2. **Cadastro de Crédito**

   - Formulário com todos os atributos do DTO
   - Validações obrigatórias e feedback via SnackBar

3. **Edição de Crédito**
   - Carrega dados existentes pelo parâmetro `:numeroCredito`
   - Formulário idêntico ao de cadastro, com campo “Número do Crédito” desabilitado
   - Atualização via PUT e feedback via SnackBar

---

## ✅ Funcionalidades

- **Busca**
  - Por número de crédito (retorna objeto único)
  - Por número da NFS-e (retorna lista de créditos)
  - Mensagens de erro: “Crédito não encontrado” / “NFS-e não encontrada”
- **Tabela**
  - Exibição dinâmica de dados
  - Botões “Editar” e “Excluir” em cada linha
- **Criação**
  - Envio de POST com dados do formulário
- **Edição**
  - Envio de PUT para atualizar dados
- **Responsividade**
  - Grid fluido que se ajusta a diferentes larguras de tela

---

## 📁 Estrutura do Projeto

app-credito-consulta/
├── src/
│ ├── app/
│ │ ├── credito-consulta/
│ │ │ ├── consulta/ # Busca e listagem (consulta.component.ts/.html/.scss)
│ │ │ ├── cadastro/ # Criação de crédito (cadastro.component.ts/.html/.scss)
│ │ │ └── editar/ # Edição de crédito (editar.component.ts/.html/.scss)
│ │ ├── models/ # Modelos TypeScript (credito.model.ts)
│ │ └── services/ # Serviço HTTP (credito.service.ts)
│ ├── app.routes.ts # Rotas: '/', '/cadastro', '/editar/:numeroCredito'
│ ├── app.config.ts # Providers (HttpClient, Router, Material)
│ └── main.ts # Bootstrap standalone
├── angular.json # Configuração Angular CLI
├── package.json # Dependências & scripts
├── styles.scss # Estilos globais
└── Dockerfile # Container multi-stage (build + Nginx)

---

## 🚀 Executando com Docker

1. **Na raiz do projeto** (onde está o `Dockerfile`), copie e cole:
   ```bash
   # 1) Build da imagem Docker
   docker build -t consulta-credito-frontend:latest .
   # 2) Iniciar container em background
   docker run -d \
     --name consulta-frontend \
     -p 4200:80 \
     consulta-credito-frontend:latest
   # 3) Abra no navegador
   http://localhost:4200
   ```
