# SecureCapita API

API RESTful desenvolvida com **Java** e **Spring Boot** para gerenciamento robusto de faturas e clientes, com foco em segurança avançada utilizando autenticação multi-fator (MFA).

Este é o Back-end da aplicação. O Front-end (Angular) pode ser encontrado aqui:
👉 **[SecureCapita UI](https://github.com/joaoVitorLeal/securecapita-ui)**

---

## Tecnologias Utilizadas

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

## Funcionalidades Principais

* **Autenticação e Segurança:**
    * Login seguro com **JWT (JSON Web Token)** e Refresh Token.
    * **MFA (Multi-Factor Authentication):** Suporte para verificação via **SMS** (Twilio) e **E-mail**.
    * Reset de senha seguro via link por e-mail.
    * Proteção contra ataques de força bruta.
* **Gestão de Clientes:**
    * CRUD completo de clientes.
    * Upload e gestão de imagens de perfil.

[//]: # (* **Gestão de Faturas:**)

[//]: # (    * Criação, listagem e exportação de faturas.)

[//]: # (    * Relatórios e dashboard estatístico.)

## Como Rodar Localmente

### Pré-requisitos
* Java 17 ou superior
* Maven
* Docker (Opcional, para o Banco de Dados)
* MySQL (ou PostgreSQL)

### Passos

1. Clone o repositório:
```bash
git clone [https://github.com/joaoVitorLeal/securecapita.git](https://github.com/joaoVitorLeal/securecapita.git)
```

2. Configure as variáveis de ambiente no application.yml ou no seu sistema (veja a seção abaixo).

3. Execute a aplicação via Maven:
```bash
mvn spring-boot:run
```

## Variáveis de Ambiente

Para rodar a aplicação, você precisará configurar as seguintes chaves no seu `application.yml` ou nas variáveis do sistema:

| Variável | Descrição | Exemplo |
| :--- | :--- | :--- |
| `SECURECAPITA_DB_URL` | URL de conexão JDBC do banco | `jdbc:mysql://localhost:3306/securecapita` |
| `SECURECAPITA_DB_USERNAME` | Usuário do banco de dados | `root` |
| `SECURECAPITA_DB_PASSWORD` | Senha do banco de dados | `sua_senha_aqui` |
| `JWT_SECRET` | Chave secreta para assinatura dos tokens | `uma_chave_muito_forte_e_secreta` |
| `TWILIO_SID` | SID da conta Twilio (MFA via SMS) | `AC...` |
| `TWILIO_TOKEN` | Token de autenticação Twilio | `...` |
| `TWILIO_NUMBER` | Número de envio cadastrado no Twilio | `+15005550006` |
| `SPRING_MAIL_HOST` | Host SMTP do provedor de e-mail | `smtp.gmail.com` |
| `SPRING_MAIL_PORT` | Porta do servidor SMTP | `587` |
| `SPRING_MAIL_USERNAME` | Seu endereço de e-mail | `seu.email@gmail.com` |
| `SPRING_MAIL_PASSWORD` | Senha de aplicativo (App Password) | `abcd-efgh-ijkl-mnop` |
---

## Contribuições
Sinta-se à vontade para abrir issues ou enviar Pull Requests.
