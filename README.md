# 🎬 Catálogo de Filmes, Livros e Séries

Projeto simples desenvolvido como parte do Projeto Integrador do curso de **Bacharelado em Ciência da Computação na Universidade Cruzeiro do Sul.**
O objetivo é demonstrar a aplicação prática dos conceitos de **Desenvolvimento Web com Java (JSP, Servlets e JDBC), Boas práticas de Programação Orientada a Objetos (POO) e integração com banco de dados relacional.**

## 🚀 Funcionalidades

- 📚 CRUD completo (Criar, Ler, Atualizar e Deletar) de títulos.
- 🔍 Busca dinâmica por título ou autor/diretor.
- 🧩 Categorias de gêneros múltiplas (seleção com CTRL).
- 🧠 Padrão DAO (Data Access Object) para organizar o código e separar a lógica de persistência.
- 🎨 Interface com JSP e CSS responsiva e simples.
- 💾 Banco de dados relacional (MySQL) para armazenar os dados.
- 🔒 Uso de PreparedStatement para evitar SQL Injection.

## 🛠️ Tecnologias Utilizadas
| Camada  | Tecnologias |
| ------------- | ------------- |
| Frontend  | HTML5, CSS3, JSP  |
| Backend  | Java, Servlets, JDBC  |
| Banco de Dados  | MySQL  |
| Servidor de Aplicação  | Apache Tomcat  |

## 🧩 Estrutura do Projeto
```
catalogo/
    ├── MainJava/
    │   ├── src.main/
    │       ├──java
    │             ├── com.catalogo.dao/        # Classes DAO (acesso ao banco)
    │             ├── com.catalogo.model/      # Classes de modelo (Título, Gênero etc.)
    │             ├── com.catalogo.servlet/    # Servlets para controle da aplicação
    │       ├── webapp/
    │       ├── images/                        # Arquivos CSS
    │       ├── style/                         # Logos e ícones
    │       ├── WEB-INF/
    │       │   └── web.xml                    # Configuração do Tomcat
    │       ├── adicionar.jsp
    │       ├── editar.jsp
    │       ├── listar.jsp
    │       ├── detalhes.jsp
    │       └── index.jsp
    │
    └── README.md
```

## ⚙️ Como Executar o Projeto

### 1 - Clonar o repositório
```
git clone https://github.com/seu-usuario/catalogo.git
```

### 2 - Configurar o banco de dados MySQL
```
-- criar database (se necessário)
CREATE DATABASE IF NOT EXISTS catalogo
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_unicode_ci;
USE catalogo;

-- tabela generos
CREATE TABLE IF NOT EXISTS generos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- tabela titulos
CREATE TABLE IF NOT EXISTS titulos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tipo ENUM('livro','filme','serie') NOT NULL,
  titulo VARCHAR(255) NOT NULL,
  autor_diretor VARCHAR(255),
  ano_lancamento INT,
  sinopse TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- tabela de relacionamento many-to-many
CREATE TABLE IF NOT EXISTS titulos_generos (
  id_titulo INT NOT NULL,
  id_genero INT NOT NULL,
  CONSTRAINT fk_titulos_generos_titulo FOREIGN KEY (id_titulo) REFERENCES titulos(id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_titulos_generos_genero FOREIGN KEY (id_genero) REFERENCES generos(id) ON DELETE CASCADE ON UPDATE CASCADE,
  INDEX fk_titulos_generos_titulo_idx (id_titulo),
  INDEX fk_titulos_generos_genero_idx (id_genero)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```
#### 🔎 Exemplo de SELECT usado no projeto para teste (para obter gêneros concatenados)
```
SELECT t.*, GROUP_CONCAT(g.nome SEPARATOR ', ') AS genero
FROM titulos t
LEFT JOIN titulos_generos tg ON t.id = tg.id_titulo
LEFT JOIN generos g ON tg.id_genero = g.id
GROUP BY t.id;
```

#### Atualize as configurações de conexão no arquivo ConnectionFactory.java, se necessário (usuário, senha e nome do banco).

### 3 - Configurar o Tomcat

- Adicione o projeto ao servidor Apache Tomcat.

- Inicie o servidor e acesse em:
👉 http://localhost:8080/catalogo/listar

## 📸 Screenshots

<img width="620" height="325" alt="image" src="https://github.com/user-attachments/assets/b4b17bac-aa41-4397-adf3-0d668778c70c" />
<img width="500" height="405" alt="image" src="https://github.com/user-attachments/assets/193a224e-5e82-4ba0-a4f1-11da2c164550" />
<img width="500" height="268" alt="image" src="https://github.com/user-attachments/assets/0bc92dbb-4ab8-4d60-8d1a-41e7e70c6bdb" />





## 💡 Observações

- Este projeto não tem fins comerciais.
- Foi desenvolvido exclusivamente para fins acadêmicos como parte do Projeto Integrador.
- O foco é demonstrar o uso de Servlets, JSP e POO em uma aplicação Java simples.
- O layout foi construído com CSS puro, buscando responsividade e visual limpo.

## 👩‍💻 Autora

**Eduarda Depetris**

🎓 Bacharelado em Ciência da Computação – Universidade Cruzeiro do Sul

## 🏁 Licença

Este projeto é de uso acadêmico e livre para consulta e aprendizado.
Sinta-se à vontade para estudar, adaptar e evoluir o código. 🚀
