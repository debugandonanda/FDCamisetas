CREATE PROCEDURE sp_InsertCliente (
    @id INT,
    @nome VARCHAR(50),
    @email VARCHAR(50),
    @telefone VARCHAR(20),
    @endereco VARCHAR(100),
    @formaPagamento VARCHAR(50)
)
AS
BEGIN
    INSERT INTO Cliente (id, nome, email, telefone, endereco, forma_pagamento)
    VALUES (@id, @nome, @email, @telefone, @endereco, @formaPagamento)
END
