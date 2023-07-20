package projeto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private String categoria;
    private String tamanho;

    public Produto(int id, String nome, String descricao, double preco, String categoria, String tamanho) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.tamanho = tamanho;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    // Métodos para manipulação do banco de dados

    // Método para buscar todos os produtos no banco de dados
    public static List<Produto> buscarProdutos() {
        List<Produto> produtos = new ArrayList<>();

        try (Connection conn = ConexaoBancoDados.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM produtos")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                double preco = rs.getDouble("preco");
                String categoria = rs.getString("categoria");
                String tamanho = rs.getString("tamanho");

                Produto produto = new Produto(id, nome, descricao, preco, categoria, tamanho);
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    // Método para inserir um novo produto no banco de dados
    public static void inserirProduto(Produto produto) {
        try (Connection conn = ConexaoBancoDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO produtos (nome, descricao, preco, categoria, tamanho) VALUES (?, ?, ?, ?, ?)")) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setString(4, produto.getCategoria());
            stmt.setString(5, produto.getTamanho());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar um produto no banco de dados
    public static void atualizarProduto(Produto produto) {
        try (Connection conn = ConexaoBancoDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE produtos SET nome = ?, descricao = ?, preco = ?, categoria = ?, tamanho = ? WHERE id = ?")) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setString(4, produto.getCategoria());
            stmt.setString(5, produto.getTamanho());
            stmt.setInt(6, produto.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para remover um produto do banco de dados
    public static void removerProduto(int id) {
        try (Connection conn = ConexaoBancoDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM produtos WHERE id = ?")) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
