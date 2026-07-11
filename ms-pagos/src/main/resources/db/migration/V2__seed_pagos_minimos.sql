INSERT INTO pagos (pedido_id, metodo_pago, estado_pago, codigo_transaccion, numero_cuotas, monto, pagado, fecha_pago)
SELECT 4, 'TRANSFERENCIA', 'APROBADO', 'CODIGOTRANSACCION-004', 1, 4500.00, true, '2026-04-10 10:00:00'
WHERE NOT EXISTS (
    SELECT 1 FROM pagos WHERE codigo_transaccion = 'CODIGOTRANSACCION-004'
);

INSERT INTO pagos (pedido_id, metodo_pago, estado_pago, codigo_transaccion, numero_cuotas, monto, pagado, fecha_pago)
SELECT 5, 'DEBITO', 'PENDIENTE', 'CODIGOTRANSACCION-005', 0, 12500.00, false, '2026-05-10 10:00:00'
WHERE NOT EXISTS (
    SELECT 1 FROM pagos WHERE codigo_transaccion = 'CODIGOTRANSACCION-005'
);
