DROP TABLE IF EXISTS movimientos_stock;
DROP TABLE IF EXISTS inventarios;

CREATE TABLE inventarios (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             activo BIT NOT NULL,
                             capacidad_total INT,
                             codigo_bodega VARCHAR(255),
                             fecha_apertura DATE,
                             nombre_bodega VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE movimientos_stock (
                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                   aprobado BIT NOT NULL,
                                   cantidad INT,
                                   fecha_movimiento DATE,
                                   motivo VARCHAR(255),
                                   producto_id INT,
                                   tipo_movimiento VARCHAR(255),
                                   inventario_id INT,
                                   CONSTRAINT fk_movimiento_inventario
                                       FOREIGN KEY (inventario_id)
                                           REFERENCES inventarios(id)
                                           ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;