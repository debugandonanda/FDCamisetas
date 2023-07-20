CREATE PROCEDURE sp_GetPedidosByCliente (
    @idCliente INT
)
AS
BEGIN
    SELECT *
    FROM Pedido
    WHERE id_cliente = @idCliente
END
