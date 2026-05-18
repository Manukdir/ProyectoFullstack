CREATE TABLE inventarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    producto_id INT NOT NULL,
    codigo_bodega VARCHAR(150) NOT NULL,
    ubicacion VARCHAR(150) NOT NULL,
    cantidad_disponible INT NOT NULL,
    cantidad_minima INT NOT NULL,
    activo BOOLEAN NOT NULL,
    fecha_actualizacion DATETIME NOT NULL
);

INSERT INTO inventarios (producto_id, codigo_bodega, ubicacion, cantidad_disponible, cantidad_minima, activo, fecha_actualizacion) VALUES (1, 'CODIGOBODEGA-001', 'ubicacion 1', 10, 10, true, '2026-01-10 10:00:00');
INSERT INTO inventarios (producto_id, codigo_bodega, ubicacion, cantidad_disponible, cantidad_minima, activo, fecha_actualizacion) VALUES (2, 'CODIGOBODEGA-002', 'ubicacion 2', 20, 20, false, '2026-02-10 10:00:00');
INSERT INTO inventarios (producto_id, codigo_bodega, ubicacion, cantidad_disponible, cantidad_minima, activo, fecha_actualizacion) VALUES (3, 'CODIGOBODEGA-003', 'ubicacion 3', 30, 30, true, '2026-03-10 10:00:00');

CREATE TABLE movimientos_stock (
    id INT AUTO_INCREMENT PRIMARY KEY,
    inventario_id INT,
    tipo_movimiento VARCHAR(150) NOT NULL,
    motivo VARCHAR(150) NOT NULL,
    documento_referencia VARCHAR(150) NOT NULL,
    cantidad INT NOT NULL,
    costo_movimiento DECIMAL(12,2) NOT NULL,
    activo BOOLEAN NOT NULL,
    fecha_movimiento DATETIME NOT NULL,
    CONSTRAINT fk_movimientos_stock_inventario_id FOREIGN KEY (inventario_id) REFERENCES inventarios(id)
);

INSERT INTO movimientos_stock (inventario_id, tipo_movimiento, motivo, documento_referencia, cantidad, costo_movimiento, activo, fecha_movimiento) VALUES (1, 'ENTRADA', 'motivo 1', 'DOCUMENTOREFERENCIA-001', 10, 1000.00, true, '2026-01-10 10:00:00');
INSERT INTO movimientos_stock (inventario_id, tipo_movimiento, motivo, documento_referencia, cantidad, costo_movimiento, activo, fecha_movimiento) VALUES (2, 'ENTRADA', 'motivo 2', 'DOCUMENTOREFERENCIA-002', 20, 2000.00, false, '2026-02-10 10:00:00');
INSERT INTO movimientos_stock (inventario_id, tipo_movimiento, motivo, documento_referencia, cantidad, costo_movimiento, activo, fecha_movimiento) VALUES (3, 'ENTRADA', 'motivo 3', 'DOCUMENTOREFERENCIA-003', 30, 3000.00, true, '2026-03-10 10:00:00');
