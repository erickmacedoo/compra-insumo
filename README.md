# 🛒 Sistema de Compras de Produtos (Compra-Insumo)

Este projeto consiste em uma aplicação Full-Stack desenvolvida para a gestão e cadastro de produtos/insumos de feira. A aplicação foi construída dividida em duas camadas independentes (Backend e Frontend), integradas via API REST com tratamento de CORS.

---

## 📋 Requisitos do Sistema e Tecnologias

### Backend
* **Linguagem:** Java 17
* **Framework:** Spring Boot 3.x
* **Banco de Dados:** H2 Database (Em memória, volátil para ambiente de teste)
* **Gerenciador de Dependências:** Maven

### Frontend
* **Framework:** Angular 18/19
* **Linguagem:** TypeScript
* **Estilização:** CSS3 Nativo com componentes reativos
* **Gerenciador de Pacotes:** NPM

---

## 🚀 Como Executar a Aplicação

### 1. Executando o Backend (Java)
Certifique-se de estar na pasta raiz do projeto (`compra-insumo`) onde se encontra o arquivo `pom.xml`.

No terminal, execute o comando para subir o servidor Spring Boot:
```bash
cd backend
mvn spring-boot:run
```
### 2. Executando o Frontend (Angular)
Abra um novo terminal e navegue até a pasta do frontend:
```bash
cd frontend
```
Para garantir a compatibilidade com a versão do Node instalada na máquina sem conflitos globais do comando ng, execute o servidor através do script nativo do gerenciador:
```bash
npm start
```
ou 
```bash
ng serve
```
Lembre-se de instalar as dependências do NPM, com o seguinte comando (na pasta frontend)
```bash
cd frontend
npm install
```

O frontend estará acessível no navegador em: http://localhost:4200

---

## 🔗 Principais Endpoints da API (REST)

A API do sistema responde no contexto `/api/produtos` e aceita e devolve dados estritamente em formato JSON.

| Método | Endpoint | Descrição | Corpo da Requisição (JSON) |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/produtos` | Lista todos os produtos cadastrados no banco H2. | *Nenhum (Corpo Vazio)* |
| **POST** | `/api/produtos` | Cadastra um novo produto/insumo no banco de dados. | `{"nome": "Maçã", "categoria": "Frutas"}` |
| **PUT** | `/api/produtos/{id}` | Atualiza os dados de um produto existente baseado no ID informado na URL. | `{"nome": "Maçã KG", "categoria": "Frutas"}` |
| **DELETE** | `/api/produtos/{id}` | Remove permanentemente um produto do banco de dados pelo seu ID. | *Nenhum (Corpo Vazio)* |

---

## 🛠️ Acesso ao Console do Banco de Dados (H2)

O Spring Boot possui um cliente web embutido para gerenciar o banco de dados em memória (H2) em tempo de execução. Para inspecionar as tabelas criadas pelo Hibernate, utilize as configurações abaixo:

* **URL de Acesso no Navegador:** `http://localhost:8080/h2-console`

Ao abrir a página de login do H2, certifique-se de preencher os campos exatamente desta forma para estabelecer a conexão:

```ini
Driver Class: org.h2.Driver
JDBC URL:     jdbc:h2:mem:testdb
User Name:    sa
Password:     
```
*(Nota: O campo de senha `Password` deve ser deixado completamente em branco por padrão).*