package util;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDados {
    private static final String URL = "jdbc:mysql://localhost:3306/fd_camisetas?useSSL=false";
    private static final String USUARIO = "Nanda";
    private static final String SENHA = "Fdye@021";

    public static ConexaoBancoDados getConexao() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao conectar com o banco de dados.");
        }
    }
}
