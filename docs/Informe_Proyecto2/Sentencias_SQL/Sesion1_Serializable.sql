

SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

//Inicio Cuenta1
UPDATE cuentas
SET saldo = saldo + 1000000
WHERE numCuenta = 87957;

INSERT INTO operacionesCuentas (tipoOperacion,idPuntoAtencion, valor, numCuenta, id,fecha)
VALUES ('Consignacion', 654, 1000000, 87957, 234, SYSDATE);
//Final cuenta1


//Inicio cuenta2
UPDATE cuentas
SET saldo = saldo - 50000
WHERE numCuenta = 67894;

INSERT INTO operacionesCuentas (tipoOperacion,idPuntoAtencion, valor, numCuenta, id,fecha)
VALUES ('Retiro', 654, 1000000, 67894, 235, SYSDATE);
//Final Cuenta2

Commit;

SELECT numCuenta, saldo
FROM cuentas
WHERE numCuenta IN (87957, 67894);
