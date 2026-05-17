CREATE TABLE pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_pedido VARCHAR(150) NOT NULL,
    cliente_id INT NOT NULL,
    total DECIMAL(12,2) NOT NULL,
    pagado BOOLEAN NOT NULL,
    fecha_pedido DATETIME NOT NULL,
    estado VARCHAR(150) NOT NULL,
    observacion VARCHAR(150) NOT NULL
);

CREATE TABLE detalles_pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pedido_id INT NOT NULL,
    producto_id INT NOT NULL,
    nombre_producto VARCHAR(150) NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(12,2) NOT NULL,
    subtotal DECIMAL(12,2) NOT NULL,
    activo BOOLEAN NOT NULL,
    fecha_registro DATETIME NOT NULL,
    CONSTRAINT fk_detalles_pedido_pedido_id FOREIGN KEY (pedido_id) REFERENCES pedidos(id)
);

INSERT INTO pedidos (numero_pedido, cliente_id, total, pagado, fecha_pedido, estado, observacion) VALUES
('PED-001', 1, '120000.00', 1, '2026-05-01 10:00:00', 'CREADO', 'Entrega normal'),
('PED-002', 2, '120000.00', 0, '2026-05-01 10:00:00', 'CREADO', 'Entrega normal'),
('PED-003', 3, '120000.00', 1, '2026-05-01 10:00:00', 'CREADO', 'Entrega normal');
INSERT INTO detalles_pedido (pedido_id, producto_id, nombre_producto, cantidad, precio_unitario, subtotal, activo, fecha_registro) VALUES
(1, 1, 'Teclado mecanico', 2, '30000.00', '60000.00', 1, '2026-05-01 10:05:00'),
(2, 2, 'Teclado mecanico', 3, '30000.00', '60000.00', 0, '2026-05-01 10:05:00'),
(3, 3, 'Teclado mecanico', 4, '30000.00', '60000.00', 1, '2026-05-01 10:05:00');
