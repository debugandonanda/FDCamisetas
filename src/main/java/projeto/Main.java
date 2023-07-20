import java.util.List;
import projeto.controller.*;
import projeto.model.*;
import projeto.repository;
import projeto.service;
public class Main {
    public static void main(String[] args) {

        // Conectar ao banco de dados
        ConexaoBancoDados conexao = ConexaoBancoDados.getConexao();
        if (conexao != null) {
            System.out.println("Conexão estabelecida com o banco de dados");
        } else {
            System.out.println("Falha na conexão com o banco de dados");
            return;
        }

        try {

            Estoque estoque = new Estoque();

            // Inserindo 300 unidades de cada produto em cada tamanho
            String[] categorias = { "babylook", "comum", "plus" };
            String[] tamanhos = { "P", "M", "G", "GG" };

            for (String categoria : categorias) {
                for (String tamanho : tamanhos) {
                    estoque.adicionarProduto(categoria, tamanho, 300);
                }
            }

            // Exibindo o estoque
            estoque.exibirEstoque();

            // Instanciando objetos de Produto
            Produto produto1 = new Produto(1, "Babylook", "P", 29.99);
            Produto produto2 = new Produto(2, "Comum", "M", 29.99);
            Produto produto3 = new Produto(3, "Plus", "G", 29.99);

            // Instanciando objetos de Cliente
            Cliente cliente1 = new Cliente(1, "João", "joao@example.com", "123456789", "Rua A, 123",
                    "Cartão de Crédito");
            Cliente cliente2 = new Cliente(2, "Maria", "maria@example.com", "987654321", "Rua B, 456",
                    "Boleto Bancário");

            // Instanciando objetos de Pedido
            Pedido pedido1 = new Pedido(1, cliente1, produto1, "Pendente");
            Pedido pedido2 = new Pedido(2, cliente2, produto2, "Entregue");

            // Instanciando objetos de Avaliação
            Avaliacao avaliacao1 = new Avaliacao(1, produto1, "Ótima camiseta!", 5);
            Avaliacao avaliacao2 = new Avaliacao(2, produto2, "Tecido de qualidade.", 4);
            Avaliacao avaliacao3 = new Avaliacao(3, produto3, "Ficou um pouco apertada.", 3);

            // Exemplo de utilização dos objetos

            // Exemplo de busca de produtos no banco de dados
            List<Produto> produtos = Produto.buscarTodosProdutos(conexao);
            for (Produto produto : produtos) {
                System.out.println("ID: " + produto.getProdutoID());
                System.out.println("Nome: " + produto.getNome());
                System.out.println("Categoria: " + produto.getCategoria());
                System.out.println("Tamanho: " + produto.getTamanho());
                System.out.println("Preço: " + produto.getPreco());
                System.out.println("---------------------------------");
            }

            // Exemplo de inserção de um novo cliente no banco de dados
            Cliente clienteNovo = new Cliente(3, "Carlos", "carlos@example.com", "987654321", "Rua C, 789",
                    "Cartão de Crédito");
            clienteNovo.inserirCliente(conexao);

            // Exemplo de atualização do status de um pedido no banco de dados
            Pedido pedido = Pedido.buscarPedidoPorID(conexao, 1);
            if (pedido != null) {
                System.out.println("Status atual do pedido: " + pedido.getStatus());
                pedido.alterarStatus(conexao, "Entregue");
                System.out.println("Novo status do pedido: " + pedido.getStatus());
            }

            // Exemplo de remoção de uma avaliação do banco de dados
            Avaliacao avaliacao = Avaliacao.buscarAvaliacaoPorID(conexao, 2);
            if (avaliacao != null) {
                avaliacao.removerAvaliacao(conexao);
                System.out.println("Avaliação removida com sucesso!");
            }

            // Exemplo de venda de um produto do estoque
            pedido1.venderProduto(estoque, 1); // Venda de 1 unidade do produto do pedido1

            // Exemplo de pagamento de um pedido
            pedido1.pagamentoPedido();

            // Exemplo de rastreamento de um pedido
            Pedido pedidoRastreado = Pedido.rastrearPedido(1);
            if (pedidoRastreado != null) {
                pedidoRastreado.verificarEntrega();
            }

            // Exibindo o estoque novamente
            estoque.exibirEstoque();

        } finally {
            // Fechar a conexão com o banco de dados
            ConexaoBancoDados.fecharConexao(conexao);
        }
    }
}
