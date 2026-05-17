CREATE TABLE pagos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pedido_id INT NOT NULL,
    codigo_transaccion VARCHAR(150) NOT NULL,
    monto DECIMAL(12,2) NOT NULL,
    metodo_pago VARCHAR(150) NOT NULL,
    estado_pago VARCHAR(150) NOT NULL,
    confirmado BOOLEAN NOT NULL,
    fecha_pago DATETIME NOT NULL
);

INSERT INTO pagos (pedido_id, codigo_transaccion, monto, metodo_pago, estado_pago, confirmado, fecha_pago) VALUES
(1, 'TRX-001', '120000.00', 'DEBITO', 'APROBADO', 1, '2026-05-01 10:20:00'),
(2, 'TRX-002', '120000.00', 'DEBITO', 'APROBADO', 0, '2026-05-01 10:20:00'),
(3, 'TRX-003', '120000.00', 'DEBITO', 'APROBADO', 1, '2026-05-01 10:20:00');
