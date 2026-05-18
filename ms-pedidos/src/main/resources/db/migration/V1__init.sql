CREATE TABLE pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    numero_pedido VARCHAR(150) NOT NULL,
    estado VARCHAR(150) NOT NULL,
    direccion_entrega VARCHAR(150) NOT NULL,
    cantidad_productos INT NOT NULL,
    total DECIMAL(12,2) NOT NULL,
    pagado BOOLEAN NOT NULL,
    fecha_pedido DATETIME NOT NULL
);

INSERT INTO pedidos (usuario_id, numero_pedido, estado, direccion_entrega, cantidad_productos, total, pagado, fecha_pedido) VALUES (1, 'NUMEROPEDIDO-001', 'CREADO', 'direccionEntrega 1', 10, 1000.00, true, '2026-01-10 10:00:00');
INSERT INTO pedidos (usuario_id, numero_pedido, estado, direccion_entrega, cantidad_productos, total, pagado, fecha_pedido) VALUES (2, 'NUMEROPEDIDO-002', 'CREADO', 'direccionEntrega 2', 20, 2000.00, false, '2026-02-10 10:00:00');
INSERT INTO pedidos (usuario_id, numero_pedido, estado, direccion_entrega, cantidad_productos, total, pagado, fecha_pedido) VALUES (3, 'NUMEROPEDIDO-003', 'CREADO', 'direccionEntrega 3', 30, 3000.00, true, '2026-03-10 10:00:00');

CREATE TABLE detalle_pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pedido_id INT,
    producto_id INT NOT NULL,
    nombre_producto VARCHAR(150) NOT NULL,
    observacion VARCHAR(150) NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(12,2) NOT NULL,
    subtotal DECIMAL(12,2) NOT NULL,
    activo BOOLEAN NOT NULL,
    fecha_registro DATETIME NOT NULL,
    CONSTRAINT fk_detalle_pedidos_pedido_id FOREIGN KEY (pedido_id) REFERENCES pedidos(id)
);

INSERT INTO detalle_pedidos (pedido_id, producto_id, nombre_producto, observacion, cantidad, precio_unitario, subtotal, activo, fecha_registro) VALUES (1, 1, 'nombreProducto 1', 'observacion 1', 10, 1000.00, 1000.00, true, '2026-01-10 10:00:00');
INSERT INTO detalle_pedidos (pedido_id, producto_id, nombre_producto, observacion, cantidad, precio_unitario, subtotal, activo, fecha_registro) VALUES (2, 2, 'nombreProducto 2', 'observacion 2', 20, 2000.00, 2000.00, false, '2026-02-10 10:00:00');
INSERT INTO detalle_pedidos (pedido_id, producto_id, nombre_producto, observacion, cantidad, precio_unitario, subtotal, activo, fecha_registro) VALUES (3, 3, 'nombreProducto 3', 'observacion 3', 30, 3000.00, 3000.00, true, '2026-03-10 10:00:00');
