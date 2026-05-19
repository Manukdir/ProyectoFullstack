INSERT INTO inventarios (activo, capacidad_total, codigo_bodega, fecha_apertura, nombre_bodega)
VALUES (b'1', 1000, 'BOD-001', '2026-01-10', 'Bodega Central');

INSERT INTO inventarios (activo, capacidad_total, codigo_bodega, fecha_apertura, nombre_bodega)
VALUES (b'1', 500, 'BOD-002', '2026-02-15', 'Bodega Norte');

INSERT INTO inventarios (activo, capacidad_total, codigo_bodega, fecha_apertura, nombre_bodega)
VALUES (b'1', 2500, 'BOD-003', '2026-03-20', 'Bodega Sur');


INSERT INTO movimientos_stock (aprobado, cantidad, fecha_movimiento, motivo, producto_id, tipo_movimiento, inventario_id)
VALUES (b'1', 50, '2026-05-10', 'Ingreso inicial de mercadería', 1, 'ENTRADA', 1);

INSERT INTO movimientos_stock (aprobado, cantidad, fecha_movimiento, motivo, producto_id, tipo_movimiento, inventario_id)
VALUES (b'1', 20, '2026-05-12', 'Despacho urgente a tienda', 1, 'SALIDA', 1);

INSERT INTO movimientos_stock (aprobado, cantidad, fecha_movimiento, motivo, producto_id, tipo_movimiento, inventario_id)
VALUES (b'1', 100, '2026-05-15', 'Reposición stock importación', 2, 'ENTRADA', 2);