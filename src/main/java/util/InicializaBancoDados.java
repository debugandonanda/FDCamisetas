package projeto;//Foi utilizada a biblioteca JDBC para executar os comandos SQL de criação das tabelas e inserção dos dados

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InicializaBancoDados{
    public void inicializaBancoDados() {
        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://localhost:3306/?user=Nanda&password=Fdye@021";
        String databaseName = "fd_camisetas";

        // Comandos SQL para criação das tabelas e inserção dos dados
        String[] sqlCommands = {
                // Criação da tabela "Produtos"
                "CREATE TABLE Produtos (" +
                        "  ProdutoID INT PRIMARY KEY," +
                        "  Nome VARCHAR(50) NOT NULL," +
                        "  Categoria VARCHAR(20) NOT NULL," +
                        "  Tamanho VARCHAR(5) NOT NULL," +
                        "  Preco DECIMAL(10,2) NOT NULL" +
                        ");",

                // Criação da tabela "Clientes"
                "CREATE TABLE Clientes (" +
                        "  ClienteID INT PRIMARY KEY," +
                        "  Nome VARCHAR(50) NOT NULL," +
                        "  Email VARCHAR(50) NOT NULL," +
                        "  Telefone VARCHAR(15) NOT NULL," +
                        "  Endereco VARCHAR(100) NOT NULL," +
                        "  DadosPagamento VARCHAR(100) NOT NULL" +
                        ");",

                // Criação da tabela "Pedidos"
                "CREATE TABLE Pedidos (" +
                        "  PedidoID INT PRIMARY KEY," +
                        "  ClienteID INT," +
                        "  ProdutoID INT," +
                        "  Status VARCHAR(20) NOT NULL," +
                        "  FOREIGN KEY (ClienteID) REFERENCES Clientes(ClienteID)," +
                        "  FOREIGN KEY (ProdutoID) REFERENCES Produtos(ProdutoID)" +
                        ");",

                // Criação da tabela "Avaliacoes"
                "CREATE TABLE Avaliacoes (" +
                        "  AvaliacaoID INT PRIMARY KEY," +
                        "  ProdutoID INT," +
                        "  Comentario VARCHAR(100) NOT NULL," +
                        "  Nota INT NOT NULL," +
                        "  FOREIGN KEY (ProdutoID) REFERENCES Produtos(ProdutoID)" +
                        ");",

                // Inserção dos dados na tabela "Produtos"
                "INSERT INTO Produtos (ProdutoID, Nome, Categoria, Tamanho, Preco) VALUES" +
                        "  (1, 'Babylook', 'babylook', 'P', 29.99)," +
                        "  (2, 'Comum', 'comum', 'M', 29.99)," +
                        "  (3, 'Plus', 'plus', 'G', 29.99);",

                // Inserção dos dados na tabela "Clientes"
                "INSERT INTO Clientes (ClienteID, Nome, Email, Telefone, Endereco, DadosPagamento) VALUES" +
                        "  (1, 'João', 'joao@example.com', '123456789', 'Rua A, 123', 'Cartão de Crédito')," +
                        "  (2, 'Maria', 'maria@example.com', '987654321', 'Rua B, 456', 'Boleto Bancário');",

                // Inserção dos dados na tabela "Pedidos"
                "INSERT INTO Pedidos (PedidoID, ClienteID, ProdutoID, Status) VALUES" +
                        "  (1, 1, 1, 'Pendente')," +
                        "  (2, 2, 2, 'Entregue');",

                // Inserção dos dados na tabela "Avaliacoes"
                "INSERT INTO Avaliacoes (AvaliacaoID, ProdutoID, Comentario, Nota) VALUES" +
                        "  (1, 1, 'Ótima camiseta!', 5)," +
                        "  (2, 2, 'Tecido de qualidade.', 4)," +
                        "  (3, 3, 'Ficou um pouco apertada.', 3);"
        };

        try (Connection connection = DriverManager.getConnection(url)) {
            // Criação do banco de dados
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + databaseName;
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(createDatabaseQuery);
                System.out.println("Banco de dados criado com sucesso");
            } catch (SQLException e) {
                System.out.println("Erro ao criar o banco de dados: " + e.getMessage());
                return;
            }

            // Conexão com o banco de dados criado
            String databaseUrl = "jdbc:mysql://localhost:3306/" + databaseName + "?user=root&password=senha";
            try (Connection dbConnection = DriverManager.getConnection(databaseUrl)) {
                // Execução dos comandos SQL
                for (String sqlCommand : sqlCommands) {
                    try (Statement statement = dbConnection.createStatement()) {
                        statement.executeUpdate(sqlCommand);
                    } catch (SQLException e) {
                        System.out.println("Erro ao executar o comando SQL: " + e.getMessage());
                        return;
                    }
                }

                System.out.println("Banco de dados criado e populado com sucesso");
            } catch (SQLException e) {
                System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao servidor de banco de dados: " + e.getMessage());
        }
    }

    public void run() {
        initializeDatabase();
    }
}
