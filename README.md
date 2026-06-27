# Clínica Backend API

API REST para gerenciamento de agendamentos de consultas clínicas.

## Tecnologias

- Java 21
- Spring Boot 3.5
- MySQL
- Gradle
- JUnit 5 + Mockito (testes)

## Pré-requisitos

- Java 21 instalado
- MySQL instalado e rodando na porta 3306
- Gradle (já incluído no projeto via Gradle Wrapper)

## Como Executar

### 1. Clone o repositório

```bash
git clone https://github.com/LauraMilly/clinica-backend.git
cd clinica-backend
```

### 2. Configure o banco de dados

Certifique-se que o MySQL está rodando. O banco `clinica_db` será criado automaticamente.

Se necessário, edite as configurações em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/clinica_db
spring.datasource.username=root
spring.datasource.password=
```

### 3. Execute o projeto

```bash
./gradlew bootRun
```

A API estará disponível em: `http://localhost:8080/api`

### 4. Execute os testes

```bash
./gradlew test
```

---

## Endpoints

### Pacientes

| Método | URL | Descrição |
|--------|-----|-----------|
| POST | `/api/pacientes` | Cadastrar paciente |
| GET | `/api/pacientes` | Listar todos os pacientes |
| GET | `/api/pacientes/{id}` | Buscar paciente por ID |

**Exemplo - Cadastrar Paciente:**
```json
POST /api/pacientes
{
    "nome": "Ana Maria",
    "cpf": "12345678900",
    "email": "ana@gmail.com",
    "telefone": "(11) 98765-4321"
}
```

---

### Profissionais

| Método | URL | Descrição |
|--------|-----|-----------|
| POST | `/api/profissionais` | Cadastrar profissional |
| GET | `/api/profissionais` | Listar todos os profissionais |
| GET | `/api/profissionais/{id}` | Buscar profissional por ID |

**Exemplo - Cadastrar Profissional:**
```json
POST /api/profissionais
{
    "nome": "Dr. Carlos Silva",
    "crm": "123456",
    "especialidade": "Cardiologia",
    "telefone": "(11) 91234-5678"
}
```

---

### Agendamentos

| Método | URL | Descrição |
|--------|-----|-----------|
| POST | `/api/agendamentos` | Criar agendamento |
| GET | `/api/agendamentos` | Listar agendamentos (com filtros) |
| DELETE | `/api/agendamentos/{id}/cancelar` | Cancelar agendamento |

**Exemplo - Criar Agendamento:**
```json
POST /api/agendamentos
{
    "pacienteId": 1,
    "profissionalId": 1,
    "dataHora": "2026-07-01T14:00:00",
    "tipoAtendimento": "Consulta"
}
```

**Filtros disponíveis na listagem:**
```
GET /api/agendamentos?pacienteId=1
GET /api/agendamentos?profissionalId=1
GET /api/agendamentos?status=AGENDADO
GET /api/agendamentos?status=CANCELADO
```

**Exemplo - Cancelar Agendamento:**
```json
DELETE /api/agendamentos/1/cancelar
{
    "motivo": "Paciente viajou"
}
```

---

## Regras de Negócio

- Um profissional não pode ter dois agendamentos no mesmo horário
- Não é permitido criar agendamento com data/hora no passado
- O cancelamento deve registrar motivo obrigatoriamente
- Ao cancelar, o status muda para `CANCELADO` e o registro é mantido
- A listagem permite filtro por paciente, profissional ou status

## Estrutura do Projeto

```
src/main/java/com/mv/clinica_backend/
├── controller/    # Recebe requisições HTTP
├── service/       # Regras de negócio
├── repository/    # Acesso ao banco de dados
├── entity/        # Modelos de dados
├── dto/           # Objetos de transferência de dados
├── exception/     # Tratamento de erros
└── config/        # Configurações (CORS)
```