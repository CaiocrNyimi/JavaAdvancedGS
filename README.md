# Skill4Green

Projeto desenvolvido em Java + Spring Boot para incentivar práticas sustentáveis dentro da empresa. O sistema gerencia colaboradores, cursos, tarefas e missões relacionadas à economia de energia e redução de impacto ambiental.

## Tecnologias utilizadas

- Java 17
- Spring Boot 3.2
- Spring Security (com BCrypt para senhas)
- Spring Data JPA
- H2 Database (ambiente de desenvolvimento)
- Swagger/OpenAPI (documentação da API)

## Estrutura básica

- **Colaboradores** → usuários do sistema (com perfil, nível verde e ecoins)
- **Cursos** → treinamentos sobre sustentabilidade
- **Tarefas** → ações práticas para economia de energia
- **Missões** → metas maiores para setores ou equipes
- **Execuções** → registro das tarefas realizadas pelos colaboradores

## Como rodar

### 1. Clone o repositório:

```bash
    git clone https://github.com/CaiocrNyimi/JavaAdvancedGS.git
```

### 2. Entre na pasta:

```bash
    cd skill4green
```

### 3. Rode com Maven:

```bash
    mvn spring-boot:run
```

### 4. Acesse a API em:

```
    http://localhost:8080
```

## Docmentação da API

Swagger disponível em:

```
    http://localhost:8080/api/swagger-ui/index.html
```

## Objetivos do projeto

O projeto é uma base simples para:

- Gerenciar colaboradores e suas ações sustentáveis;
- Registrar economia de energia e redução de CO₂;
- Incentivar aprendizado com cursos e missões.