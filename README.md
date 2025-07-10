# Pizza Dev

## Como rodar o projeto
- Passo 1: Popular as variáveis de ambiente
    - Variáveis Docker: 
      1. Renomeie o `.envTemplate` na raíz do repositório para `.env`
      2. Popule as variáveis de ambiente:
          ```
              ENV_POSTGRES_USER=seu_user
              ENV_POSTGRES_PASSWORD=sua_senha
          ```
    - Variáveis Spring Boot:
      1. Renomeie o `.envTemplate` em `/src/main/resources` para `.env`
      2. Popule as variáveis de ambiente (essas variáveis devem ser iguais às da raíz do projeto):
         ```
              ENV_POSTGRES_USER=seu_user
              ENV_POSTGRES_PASSWORD=sua_senha
          ```
- Passo 2: Rodar o docker
    - Execute no seu terminar o seguinte comando:
        ```
            docker-compose up -d
        ```
- Passo 3: Executar o projeto Spring Boot
    - Inicialize via terminal com os seguintes comandos:
        ```
            mvn clean install
            mvn spring-boot:run
        ```
