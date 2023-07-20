CREATE PROCEDURE sp_CalcularTotalVendas (
    @idProduto INT,
    @totalVendas DECIMAL(10, 2) OUTPUT
)
AS
BEGIN
    SELECT @totalVendas = SUM(preco * quantidade)
    FROM PedidoItem
    WHERE id_produto = @idProduto
END