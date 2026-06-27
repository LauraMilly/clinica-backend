# Decisões Técnicas

## Principais decisões técnicas

### Linguagem e Framework
Utilizei **Java 21 com Spring Boot 3.5** por ser o stack principal da MV Sistemas, garantindo familiaridade com o ambiente de trabalho real da empresa.

### Banco de Dados
Optei pelo **MySQL** por ser um banco relacional amplamente utilizado em sistemas de saúde. O projeto também demonstra compatibilidade com **Oracle** (diferencial solicitado), bastando alterar as configurações em `application.properties`.

### Arquitetura em Camadas
Adotei o padrão clássico de **4 camadas**:
- **Controller**: Recebe e responde requisições HTTP
- **Service**: Aplica as regras de negócio
- **Repository**: Acessa o banco de dados via Spring Data JPA
- **Entity**: Modela os dados e tabelas

Essa separação garante código organizado, testável e fácil de manter.

### DTOs (Data Transfer Objects)
Utilizei DTOs para controlar o que é retornado pela API, evitando expor dados sensíveis como CPF do paciente diretamente nas respostas.

### Tratamento de Erros
Implementei um **GlobalExceptionHandler** centralizado que captura todas as exceções e retorna respostas padronizadas com status HTTP correto (400 em vez de 500).

### CORS
Configurei o CORS para permitir que o frontend (React) consuma a API sem bloqueios de segurança do navegador.

---

## O que foi priorizado

- ✅ Todas as regras de negócio obrigatórias
- ✅ Arquitetura limpa e organizada em camadas
- ✅ Testes automatizados cobrindo as regras principais
- ✅ Tratamento de erros adequado
- ✅ DTOs para segurança dos dados
- ✅ Documentação clara no README

---

## O que ficou de fora

- ❌ Autenticação/Autorização (JWT) - não era requisito obrigatório
- ❌ Documentação Swagger - não deu tempo de implementar
- ❌ Frontend - está planejado mas não foi requisito obrigatório
- ❌ Paginação na listagem - simplificado para o escopo do teste

---

## Uso de IA

Utilizei IA como suporte no desenvolvimento, principalmente para:

- Entender conceitos de arquitetura em camadas
- Revisar código e identificar erros
- Aprender boas práticas de Spring Boot
- Depurar erros de compilação e testes

Todo o código foi revisado, entendido e validado por mim antes de ser commitado. Cada decisão técnica foi compreendida antes de ser aplicada, não apenas copiada.

Os testes foram executados e validados manualmente via Postman, confirmando o funcionamento correto de todos os endpoints e regras de negócio.