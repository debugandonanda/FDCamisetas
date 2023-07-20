CREATE TABLE Produto (
    id INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tamanho VARCHAR(10) NOT NULL,
    preco DECIMAL(8, 2) NOT NULL
);

CREATE TABLE Cliente (
    id INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    endereco VARCHAR(200) NOT NULL,
    forma_pagamento VARCHAR(50) NOT NULL
);

CREATE TABLE Pedido (
    id INT PRIMARY KEY,
    cliente_id INT NOT NULL,
    produto_id INT NOT NULL,
    status VARCHAR(50) NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id),
    FOREIGN KEY (produto_id) REFERENCES Produto(id)
);

CREATE TABLE Avaliacao (
    id INT PRIMARY KEY,
    produto_id INT NOT NULL,
    comentario VARCHAR(300) NOT NULL,
    avaliacao INT NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES Produto(id)
);

CREATE TABLE Estoque (
    id INT PRIMARY KEY,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES Produto(id)
);