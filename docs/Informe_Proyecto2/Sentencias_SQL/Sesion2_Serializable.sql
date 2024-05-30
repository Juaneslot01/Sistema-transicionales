SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
//Inicio Cuenta1
UPDATE cuentas
SET saldo = saldo - 30000
WHERE numCuenta = 87957;

INSERT INTO operacionesCuentas (tipoOperacion,idPuntoAtencion, valor, numCuenta, id,fecha)
VALUES ('Retiro', 654, 30000, 87957, 236, SYSDATE);
//Final cuenta1

//Inicio cuenta2
UPDATE cuentas
SET saldo = saldo + 5000
WHERE numCuenta = 67894;

INSERT INTO operacionesCuentas (tipoOperacion,idPuntoAtencion, valor, numCuenta, id,fecha)
VALUES ('Consignacion', 654, 5000, 67894, 237, SYSDATE);
//Final Cuenta2

COMMIT;

SELECT numCuenta, saldo
FROM cuentas
WHERE numCuenta IN (87957, 67894);

