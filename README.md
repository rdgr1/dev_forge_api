
# 🛡️ Dev Forge API

A Dev Forge API é uma aplicação Java + Spring Boot projetada para integração segura com serviços de IA (como Gemini API) e autenticação JWT baseada em chaves RSA.
Ela fornece endpoints de autenticação, relatórios e integração assistiva com IA.

⸻

### 📂 Estrutura do Projeto

```bash
src/main/java/org/rod/dev/report/forge/
├── config/
│   ├── AssistantConfig.java
│   └── StartupRunner.java
│
├── controller/
│   ├── AuthController.java
│   └── GoogleAssistController.java
│
├── dto/
│   ├── AuthResponse.java
│   ├── LoginRequest.java
│   └── RelatorioRequest.java
│
├── model/
│   └── User.java
│
├── repo/
│   └── UserRepo.java
│
├── security/
│   ├── CustomUserDetailsService.java
│   ├── JwtUtil.java
│   └── SecurityConfig.java
│
├── service/
│   ├── AssistantAiService.java
│   └── AuthService.java
│
├── util/
│   ├── AssistantTools.java
│   └── ForgeApplication.java
```


⸻

## 🚀 Como rodar o projeto localmente

1️⃣ Clonar o repositório

```bash
git clone https://github.com/rdgr1/dev_forge_api.git
cd dev_forge_api
```

2️⃣ Configurar o banco de dados (PostgreSQL)

Crie um banco local ou use o Railway (como no exemplo).
Certifique-se de ter um usuário e senha válidos.

⸻

### ⚙️ CONFIGURAÇÃO CRÍTICA — application.properties

⚠️ ATENÇÃO: O projeto NÃO FUNCIONA sem estas configurações!

O arquivo src/main/resources/application.properties deve estar corretamente configurado com suas credenciais, modelo Gemini e chaves JWT.

```properties
spring.application.name=forge

gemini.api.key=SEU_TOKEN_GEMINI_AQUI
gemini.model=gemini-2.5-flash

server.port=8081

spring.datasource.url=jdbc:postgresql://
spring.datasource.username=postgres
spring.datasource.password=SENHA_AQUI

spring.jpa.hibernate.ddl-auto=update

spring.security.oauth2.resourceserver.jwt.public-key-location=classpath:public.pem
spring.security.oauth2.resourceserver.jwt.private-key-location=classpath:private.pem
```

💡 Dica:
As chaves public.pem e private.pem devem estar na pasta src/main/resources/.
Sem elas, o Spring Security não conseguirá validar nem gerar tokens JWT.

⸻

### 🔐 CONFIGURAÇÃO CRÍTICA — SecurityConfig.java

⚠️ Esta classe é o coração da segurança da aplicação.
Ela configura:
	•	O provider de autenticação (DaoAuthenticationProvider)
	•	A leitura das chaves RSA (private.pem, public.pem)
	•	O JwtDecoder
	•	O CORS (com origem permitida https://site.com/)
	•	As rotas públicas e protegidas

As linhas mais importantes são:

.authenticationProvider(authenticationProvider()) // ← LINHA CRUCIAL
.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder)))

E o trecho de CORS:

config.setAllowedOrigins(List.of("https://site.com/"));

⚠️ Se você mudar o domínio do front-end, lembre-se de atualizar esta origem, senão o front não conseguirá se comunicar com a API.

⸻

### ▶️ Rodando a aplicação

```bash
mvn clean install
mvn spring-boot:run
```

A API estará acessível em:

http://localhost:8081


⸻

### 📡 Endpoints principais

Método	Endpoint	Descrição
```curl
POST	/api/v1/login	Autenticação de usuário
POST	/api/v1/register	Registro de novo usuário
POST	/api/v1/relatorio	Geração de relatório com suporte da IA Gemini
GET	/api/v1/user	Retorna dados do usuário autenticado
```


⸻

### 🧠 Integração com IA (Gemini)

A API utiliza o Google Gemini API via AssistantAiService.
O modelo pode ser trocado alterando:

gemini.model=gemini-2.5-flash

Requer uma chave válida (gemini.api.key).

⸻

### 🔑 JWT & Segurança
	•	Tokens JWT são gerados e validados usando chaves RSA (private.pem e public.pem).
	•	A expiração e assinatura estão configuradas no JwtUtil.
	•	Apenas endpoints explícitos no SecurityConfig estão liberados para acesso público.

⸻

### 🧰 Utilitários
	•	AssistantTools: Funções auxiliares para integração com IA.
	•	StartupRunner: Scripts executados ao iniciar a aplicação.

⸻

### 🧑‍💻 Contribuição
	1.	Fork o projeto
	2.	Crie sua branch (feature/nova-feature)
	3.	Commit suas alterações (git commit -m 'feat: nova feature')
	4.	Push na branch (git push origin feature/nova-feature)
	5.	Abra um Pull Request 🚀

⸻

#### 📄 Licença

Distribuído sob a licença MIT.
Use, modifique e aprenda livremente.

