package projeto;

import java.sql.*;
import java.time.LocalDate;

public class Pedido {
    private int id;
    private String cliente;
    private String produto;
    private String status;
    private LocalDate dataDoPedido;

    public Pedido(int id, String cliente, String produto, String status, LocalDate dataDoPedido) {
        this.id = id;
        this.cliente = cliente;
        this.produto = produto;
        this.status = status;
        this.dataDoPedido = dataDoPedido;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataDoPedido() {
        return dataDoPedido;
    }

    public void setDataDoPedido(LocalDate dataDoPedido) {
        this.dataDoPedido = dataDoPedido;
    }

    // Métodos para manipulação do banco de dados

    // Método para criar um novo pedido
    public static void criarPedido(String cliente, String produto) {
        try (Connection conn = ConexaoBancoDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO pedidos (cliente, produto, status, data_do_pedido) VALUES (?, ?, 'Pendente', ?)")) {

            stmt.setString(1, cliente);
            stmt.setString(2, produto);
            stmt.setDate(3, Date.valueOf(LocalDate.now()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para fazer o pagamento do pedido
    public void pagamentoPedido() {
        status = "Concluído";
        System.out.println("Pagamento do pedido #" + id + " realizado com sucesso. Status atualizado para 'Concluído'.");
    }

    // Método para rastrear um pedido pelo ID
    public static Pedido rastrearPedido(int id) {
        try (Connection conn = ConexaoBancoDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pedidos WHERE id = ?")) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String cliente = rs.getString("cliente");
                    String produto = rs.getString("produto");
                    String status = rs.getString("status");
                    LocalDate dataDoPedido = rs.getDate("data_do_pedido").toLocalDate();

                    return new Pedido(id, cliente, produto, status, dataDoPedido);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Método para alterar o status de um pedido
    public static void alterarPedido(int id, String novoStatus) {
        try (Connection conn = ConexaoBancoDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE pedidos SET status = ? WHERE id = ?")) {

            stmt.setString(1, novoStatus);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para verificar entrega do pedido
    public void verificarEntrega() {
        LocalDate dataEntrega = dataDoPedido.plusDays(20);
        LocalDate dataAtual = LocalDate.now();

        if (status.equals("Concluído") && dataAtual.isAfter(dataEntrega)) {
            status = "Entregue";
            System.out.println("Pedido #" + id + " foi entregue.");
        } else {
            System.out.println("Pedido #" + id + " ainda não foi entregue.");
        }
    }
}
