-- Inserção de dados na tabela Produto
INSERT INTO Produto (id, nome, tamanho, preco) VALUES
(1, 'Babylook', 'P', 29.99),
(2, 'Comum', 'M', 29.99),
(3, 'Plus', 'G', 29.99);

-- Inserção de dados na tabela Cliente
INSERT INTO Cliente (id, nome, email, telefone, endereco, forma_pagamento) VALUES
(1, 'João', 'joao@example.com', '123456789', 'Rua A, 123', 'Cartão de Crédito'),
(2, 'Maria', 'maria@example.com', '987654321', 'Rua B, 456', 'Boleto Bancário');

-- Inserção de dados na tabela Pedido
INSERT INTO Pedido (id, cliente_id, produto_id, status) VALUES
(1, 1, 1, 'Pendente'),
(2, 2, 2, 'Entregue');

-- Inserção de dados na tabela Avaliacao
INSERT INTO Avaliacao (id, produto_id, comentario, classificacao) VALUES
(1, 1, 'Ótima camiseta!', 5),
(2, 2, 'Tecido de qualidade.', 4),
(3, 3, 'Ficou um pouco apertada.', 3);

-- Inserção de dados na tabela Estoque
INSERT INTO Estoque (id, produto_id, quantidade) VALUES
(1, 1, 10),
(2, 2, 20),
(3, 3, 5);
