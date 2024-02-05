# Engenharia de Software - Análise e Desenvolvimento de Software

## Descrição

Este é um programa Java simples para gerenciar projetos e seus clientes associados. Ele permite realizar operações básicas de CRUD (Criar, Ler, Atualizar, Excluir) em projetos quanto em clientes. Os dados são persistidos utilizando o SQLite.

## Requisitos

- Java JDK 17 (ou superior) instalado em seu sistema. [Java Downloads | Oracle](https://www.oracle.com/java/technologies/downloads/)
- Ambiente de terminal para execução do programa.
- Maven 3.8.6
- SQLite3 3.37.2

## Instalação

Clone este repositório em sua máquina local:

```bash Copy code
git clone https://github.com/italomaia03/es-desafio-pratico-2.git
```

Navegue até o diretório do projeto:

```bash Copy code
cd es-desafio-pratico-2
```

## Como rodar a aplicação

Crie um arquivo com extensão .db.

```bash Copy code
touch caminho_do_diretório/nome_do_arquivo.db
```

Rode os scripts SQL para criar as tabelas.

```bash Copy code
sqlite3 caminho_do_diretório/nome_do_arquivo.db < caminho_do_diretório/scripts/ddl_bd.sql
```

Renomeie o arquivo .env.example para .env e adicione a URL de conexão com
o banco de dados SQLite3.

```bash Copy code
mv .env.example .env
```

```bash Copy code
SQLITE_URL=sua_url_aqui
```

Compile o programa Java:

```bash Copy code
mvn package
```

Execute o programa:

```bash Copy code
java -cp target/es-desafio-pratico-2-1.0-SNAPSHOT-jar-with-dependencies.jar Main
```

Você será apresentado a um menu com as seguintes opções:

1. Cadastrar clientes
2. Cadastrar projetos
3. Mostrar todos os clientes
4. Mostrar todos os projetos
5. Mostrar cliente
6. Mostrar projeto
7. Editar cliente
8. Editar projeto
9. Remover cliente
10. Remover projeto
11. Sair

## Aprendizados

Aprendi várias lições valiosas. Descobri que é crucial manter uma visão clara da integridade conceitual do projeto, sempre buscando seguir os mesmos padrões desde o início. Ocultar informações e agrupar funcionalidades relacionadas em classes e pacotes coesos ajudou a tornar o código mais limpo e fácil de manter. Além disso, buscar seguir os princípios SOLID, focando em classes simples e responsáveis por uma única tarefa. Esses aprendizados não apenas aprimoraram as habilidades de programação, mas também ajudam a construir um sistema mais flexível e adaptável.

## 🔗 Links

[![portfolio](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/italomaia03)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/italo-maia/)
