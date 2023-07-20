DECLARE @idCliente INT
SET @idCliente = 1 -- Defina o ID do cliente desejado

EXEC sp_GetPedidosByCliente @idCliente
