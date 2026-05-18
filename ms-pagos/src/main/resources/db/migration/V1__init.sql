CREATE TABLE pagos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pedido_id INT NOT NULL,
    metodo_pago VARCHAR(150) NOT NULL,
    estado_pago VARCHAR(150) NOT NULL,
    codigo_transaccion VARCHAR(150) NOT NULL,
    numero_cuotas INT NOT NULL,
    monto DECIMAL(12,2) NOT NULL,
    pagado BOOLEAN NOT NULL,
    fecha_pago DATETIME NOT NULL
);

INSERT INTO pagos (pedido_id, metodo_pago, estado_pago, codigo_transaccion, numero_cuotas, monto, pagado, fecha_pago) VALUES (1, 'metodoPago 1', 'APROBADO', 'CODIGOTRANSACCION-001', 10, 1000.00, true, '2026-01-10 10:00:00');
INSERT INTO pagos (pedido_id, metodo_pago, estado_pago, codigo_transaccion, numero_cuotas, monto, pagado, fecha_pago) VALUES (2, 'metodoPago 2', 'APROBADO', 'CODIGOTRANSACCION-002', 20, 2000.00, false, '2026-02-10 10:00:00');
INSERT INTO pagos (pedido_id, metodo_pago, estado_pago, codigo_transaccion, numero_cuotas, monto, pagado, fecha_pago) VALUES (3, 'metodoPago 3', 'APROBADO', 'CODIGOTRANSACCION-003', 30, 3000.00, true, '2026-03-10 10:00:00');
