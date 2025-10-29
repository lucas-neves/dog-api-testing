# Dog API Testing

Projeto de testes automatizados para a Dog API usando REST Assured, TestNG e Allure Report.

## Tecnologias

- **Java 21**
- **Maven** - Gerenciamento de dependências
- **REST Assured** - Testes de API
- **TestNG** - Framework de testes
- **Allure Report** - Relatórios detalhados

## Endpoints Testados

### 1. GET /breeds/list/all
Lista todas as raças disponíveis com suas sub-raças.

**Cenários:**
- ✅ Retorna lista completa com status 200
- ✅ Valida estrutura de dados
- ✅ Verifica campos obrigatórios
- ✅ Valida Content-Type JSON

### 2. GET /breed/{breed}/images
Retorna imagens de uma raça específica.

**Cenários:**
- ✅ Retorna imagens para raça válida
- ✅ Valida formato das URLs
- ✅ Retorna erro 404 para raça inválida
- ✅ Funciona com sub-raças
- ✅ Valida Content-Type JSON

### 3. GET /breeds/image/random
Retorna imagem aleatória de cachorro.

**Cenários:**
- ✅ Retorna imagem aleatória com sucesso
- ✅ Valida formato da URL
- ✅ Retorna múltiplas imagens aleatórias
- ✅ Verifica campos obrigatórios
- ✅ Valida Content-Type JSON

## Pré-requisitos

- Java 21 ou superior
- Maven 3.6 ou superior

## Execução Local

### 1. Executar todos os testes
```bash
mvn clean test
```

### 2. Gerar relatório Allure
```bash
mvn allure:report
```

### 3. Visualizar relatório
```bash
mvn allure:serve
```

## Execução via GitHub Actions

O projeto está configurado para execução automática via GitHub Actions:

- ✅ Executa em push para branches `main` e `develop`
- ✅ Executa em Pull Requests para `main`
- ✅ Pode ser executado manualmente via workflow_dispatch
- ✅ Gera e armazena relatórios Allure
- ✅ Publica resultados dos testes

### Visualizar relatórios no GitHub

O relatório é publicado automaticamente em:
```
https://lucas-neves.github.io/dog-api-testing/allure-report/
```

## Estrutura do Projeto

```
dog-api-testing/
├── src/test/java/com/dogapi/
│   ├── BaseTest.java           # Configuração base
│   ├── BreedsListTest.java     # Testes de lista de raças
│   ├── BreedImagesTest.java    # Testes de imagens por raça
│   └── RandomImageTest.java    # Testes de imagem aleatória
├── .github/workflows/
│   └── tests.yml               # CI/CD GitHub Actions
├── pom.xml                     # Dependências Maven
└── README.md
```

## Relatórios

O projeto gera relatórios detalhados com **Allure Report** contendo:

- 📊 Resumo de execução (passou/falhou)
- 📝 Detalhes de cada teste
- 🔍 Request/Response completos
- ⏱️ Tempo de execução
- 📈 Gráficos e tendências
- ❌ Stack trace de erros

## Features Implementadas

- ✅ Validação de status codes
- ✅ Validação de estrutura de dados
- ✅ Validação de Content-Type
- ✅ Testes de cenários negativos
- ✅ Integração com Allure Report
- ✅ Pipeline CI/CD com GitHub Actions

## Autor

Lucas Neves
