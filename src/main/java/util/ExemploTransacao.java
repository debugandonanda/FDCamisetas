package projeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ExemploTransacao {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            // Estabelece a conexão com o banco de dados
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fd_camisetas", "Nanda", "Fdye@021");

            // Desabilita o modo de autocommit
            connection.setAutoCommit(false);

            // Cria o objeto Statement
            statement = connection.createStatement();

            // Executa as operações dentro da transação
            try {
                // Operação 1
                String sql1 = "INSERT INTO tabela1 (coluna1, coluna2) VALUES ('valor1', 'valor2')";
                statement.executeUpdate(sql1);

                // Operação 2
                String sql2 = "UPDATE tabela2 SET coluna1 = 'novo_valor' WHERE coluna2 = 'valor'";
                statement.executeUpdate(sql2);

                // Operação 3
                String sql3 = "DELETE FROM tabela3 WHERE coluna1 = 'valor'";
                statement.executeUpdate(sql3);

                // Comita a transação se todas as operações foram executadas com sucesso
                connection.commit();
                System.out.println("Transação concluída com sucesso.");
            } catch (SQLException e) {
                // Em caso de erro, realiza o rollback da transação
                connection.rollback();
                System.out.println("Ocorreu um erro. Transação foi desfeita.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha os recursos
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
