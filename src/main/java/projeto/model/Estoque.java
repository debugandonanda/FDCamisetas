package projeto;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private Map<String, Integer> estoque;

    public Estoque() {
        this.estoque = new HashMap<>();
    }

    public void adicionarProduto(String categoria, String tamanho, int quantidade) {
        String chave = categoria + "_" + tamanho;
        int quantidadeAtual = estoque.getOrDefault(chave, 0);
        estoque.put(chave, quantidadeAtual + quantidade);
    }

    public int verificarQuantidade(String categoria, String tamanho) {
        String chave = categoria + "_" + tamanho;
        return estoque.getOrDefault(chave, 0);
    }

    public void exibirEstoque() {
        for (String chave : estoque.keySet()) {
            int quantidade = estoque.get(chave);
            System.out.println(chave + ": " + quantidade);
        }
    }

}