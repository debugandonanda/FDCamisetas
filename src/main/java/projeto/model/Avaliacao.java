package projeto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Avaliacao {
    private int id;
    private int produtoId;
    private String comentario;
    private int pontuacao;

    public Avaliacao(int id, int produtoId, String comentario, int pontuacao) {
        this.id = id;
        this.produtoId = produtoId;
        this.comentario = comentario;
        this.pontuacao = pontuacao;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    // Métodos para manipulação do banco de dados

    // Método para buscar todas as avaliações de um produto
    public static List<Avaliacao> buscarAvaliacoesPorProduto(int produtoId) {
        List<Avaliacao> avaliacoes = new ArrayList<>();

        try (Connection conn = ConexaoBancoDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM avaliacoes WHERE produto_id = ?")) {

            stmt.setInt(1, produtoId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String comentario = rs.getString("comentario");
                    int pontuacao = rs.getInt("pontuacao");

                    Avaliacao avaliacao = new Avaliacao(id, produtoId, comentario, pontuacao);
                    avaliacoes.add(avaliacao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return avaliacoes;
    }

    // Método para inserir uma nova avaliação
    public static void inserirAvaliacao(Avaliacao avaliacao) {
        try (Connection conn = ConexaoBancoDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO avaliacoes (produto_id, comentario, pontuacao) VALUES (?, ?, ?)")) {

            stmt.setInt(1, avaliacao.getProdutoId());
            stmt.setString(2, avaliacao.getComentario());
            stmt.setInt(3, avaliacao.getPontuacao());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para remover uma avaliação
    public static void removerAvaliacao(int id) {
        try (Connection conn = ConexaoBancoDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM avaliacoes WHERE id = ?")) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

