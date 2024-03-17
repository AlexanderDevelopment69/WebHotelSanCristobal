create database hotelSanCristobal;
use hotelSanCristobal;


-- Tabla de Clientes
CREATE TABLE clientes (
                          id_cliente INT PRIMARY KEY AUTO_INCREMENT,
                          nombre VARCHAR(100) NOT NULL,
                          apellido VARCHAR(100) NOT NULL,
                          dni VARCHAR(20) NOT NULL UNIQUE,
                          email VARCHAR(100) NOT NULL UNIQUE,
                          password VARCHAR(255) NOT NULL
);

-- Tabla de Administradores
CREATE TABLE administradores (
                                 id_admin INT PRIMARY KEY AUTO_INCREMENT,
                                 nombre VARCHAR(100) NOT NULL,
                                 dni VARCHAR(20) NOT NULL UNIQUE,
                                 email VARCHAR(100) NOT NULL UNIQUE,
                                 password VARCHAR(255) NOT NULL
);


-- Crear tabla de Tipos de Habitaciones
CREATE TABLE tipos_habitaciones (
                                    id_tipo_habitacion INT PRIMARY KEY AUTO_INCREMENT,
                                    nombre_tipo VARCHAR(50) NOT NULL,
                                    descripcion TEXT,
                                    costo DECIMAL(10, 2) NOT NULL,
                                    cantidad_total INT NOT NULL DEFAULT 0,
                                    cantidad_disponible INT NOT NULL DEFAULT 0
);


-- Crear tabla de servicios
CREATE TABLE servicios (
                           id_servicio INT PRIMARY KEY AUTO_INCREMENT,
                           nombre VARCHAR(100) NOT NULL,
                           descripcion TEXT  ,
                           id_tipo_habitacion  INT,
                           FOREIGN KEY (id_tipo_habitacion) REFERENCES tipos_habitaciones(id_tipo_habitacion)
);



-- Crear tabla de habitaciones
CREATE TABLE habitaciones (
                              id_habitacion INT PRIMARY KEY AUTO_INCREMENT,
                              numero_habitacion INT NOT NULL,
                              id_tipo_habitacion INT,
                              estado VARCHAR(20) DEFAULT 'DISPONIBLE',
                              FOREIGN KEY (id_tipo_habitacion) REFERENCES tipos_habitaciones(id_tipo_habitacion)
);

CREATE TABLE eventos (
                         id_evento INT PRIMARY KEY AUTO_INCREMENT,
                         nombre_evento VARCHAR(100) NOT NULL,
                         fecha_inicio DATE NOT NULL,
                         fecha_fin DATE NOT NULL,
                         descripcion TEXT
);

CREATE TABLE tipos_habitaciones_eventos (
                                            id_tipo_habitacion_evento INT PRIMARY KEY AUTO_INCREMENT,
                                            id_tipo_habitacion INT,
                                            id_evento INT,
                                            costo_tipo_habitacion_evento DECIMAL(10, 2) NOT NULL,
                                            FOREIGN KEY (id_tipo_habitacion) REFERENCES tipos_habitaciones(id_tipo_habitacion),
                                            FOREIGN KEY (id_evento) REFERENCES eventos(id_evento)
);

select* from servicios;




CREATE TABLE reservas (
                          id_reserva INT PRIMARY KEY AUTO_INCREMENT,
                          id_cliente INT,
                          id_habitacion INT,
                          fecha_inicio DATE NOT NULL,
                          fecha_fin DATE NOT NULL,
                          estado_reserva VARCHAR(20) DEFAULT 'PENDIENTE',
                          FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
                          FOREIGN KEY (id_habitacion) REFERENCES habitaciones(id_habitacion)
);


CREATE TABLE tarjetas (
                          id_tarjeta INT PRIMARY KEY AUTO_INCREMENT,
                          id_cliente INT,
                          token_tarjeta VARCHAR(255) NOT NULL,  -- Token o referencia devuelto por el PSP
                          tipo_tarjeta VARCHAR(10) NOT NULL,     -- Puedes usar un enum si prefieres
                          FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);


CREATE TABLE pagos (
                       id_pago INT PRIMARY KEY AUTO_INCREMENT,
                       id_reserva INT,
                       id_tarjeta INT,
                       monto DECIMAL(10, 2) NOT NULL,
                       fecha_pago DATE NOT NULL,
                       FOREIGN KEY (id_reserva) REFERENCES reservas(id_reserva),
                       FOREIGN KEY (id_tarjeta) REFERENCES tarjetas(id_tarjeta)
);


-- Tabla para el Chatbot
CREATE TABLE chatbot_interacciones (
                                       id_interaccion INT PRIMARY KEY AUTO_INCREMENT,
                                       nombres_usuario varchar(250),
                                       correo_usuario varchar(250),
                                       tipo_interaccion VARCHAR(50) NOT NULL, -- Puede ser 'Consulta_Reserva', 'Otra_Consulta', etc.
                                       mensaje_usuario TEXT NOT NULL,
                                       respuesta_chatbot TEXT NOT NULL,
                                       fecha_interaccion_usuario DATETIME,
                                       fecha_interaccion DATETIME
);


# -- Tabla para el Chatbot
# CREATE TABLE chatbot_interacciones (
                                         #     id_interaccion INT PRIMARY KEY AUTO_INCREMENT,
                                         #     id_cliente INT,
                                         #     tipo_interaccion VARCHAR(50) NOT NULL, -- Puede ser 'Consulta_Reserva', 'Otra_Consulta', etc.
                                         #     mensaje_usuario TEXT NOT NULL,
                                         #     respuesta_chatbot TEXT NOT NULL,
                                         #     fecha_interaccion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                         #     FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
                                             # );



#-------------------------------------------------------------- INSERCCION DE DATOS-----------------------------------------------------------



-- Insertar registros para tipos de habitaciones con rutas de imágenes
INSERT INTO tipos_habitaciones (nombre_tipo, descripcion, costo, cantidad_total, cantidad_disponible, imagen)
VALUES ('Habitación Individual', 'Con baño privado para 1 persona, con una cama individual de una plaza y media.',
        60.00, 9, 9, 'room1.jpg'),
       ('Habitación Doble', 'Con baño privado para 2 personas, con dos camas de una plaza y media.', 100.00, 3, 3,
        'room2.jpg'),
       ('Habitación Matrimonial', 'Con baño privado para 2 personas, con una cama de 2 plazas.', 100.00, 10, 10,
        'room3.jpg');

-- Insertar registros para servicios
INSERT INTO servicios (nombre, descripcion, id_tipo_habitacion)
VALUES ('Baño privado', 'Con ducha, suministros básicos de tocador y agua caliente.', 1),
       ('Televisión por cable', 'Con canales variados para el entretenimiento.', 1),
       ('Teléfono', 'Para llamadas internas y externas.', 1),
       ('Wifi', 'Fibra óptica de alta velocidad.', 1),
       ('Desayuno', 'Desayuno continental.', 1),
       ('Lavandería', 'Opcional y sujeto a cargo adicional.', 1),
       ('Baño privado', 'Con ducha, suministros básicos de tocador y agua caliente.', 2),
       ('Televisión por cable', 'Con canales variados para el entretenimiento.', 2),
       ('Teléfono', 'Para llamadas internas y externas.', 2),
       ('Wifi', 'Fibra óptica de alta velocidad.', 2),
       ('Desayuno', 'Desayuno continental.', 2),
       ('Lavandería', 'Opcional y sujeto a cargo adicional.', 2),
       ('Estacionamiento', 'Estacionamiento gratuito.', 2),
       ('Baño privado', 'Con ducha, suministros básicos de tocador y agua caliente.', 3),
       ('Televisión por cable', 'Con canales variados para el entretenimiento.', 3),
       ('Teléfono', 'Para llamadas internas y externas.', 3),
       ('Wifi', 'Fibra óptica de alta velocidad.', 3),
       ('Desayuno', 'Desayuno continental.', 3),
       ('Lavandería', 'Opcional y sujeto a cargo adicional.', 3),
       ('Estacionamiento', 'Estacionamiento gratuito.', 3);


-- Insertar registros para habitaciones, 22 habitaciones
INSERT INTO habitaciones (numero_habitacion, id_tipo_habitacion)
VALUES (1, 1), -- Habitación Individual 9
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 1),
       (9, 1),
       -- Habitación Doble 3
       (10, 2),
       (11, 2),
       (12, 2),
       -- Habitación Matrimonial 10
       (13, 3),
       (14, 3),
       (15, 3),
       (16, 3),
       (17, 3),
       (18, 3),
       (19, 3),
       (20, 3),
       (21, 3),
       (22, 3);


-- Insertar registros en la tabla eventos
INSERT INTO eventos (nombre_evento, fecha_inicio, fecha_fin, descripcion)
VALUES ('Semana santa', '2024-04-10', '2024-04-17', 'Vacaciones de Semana Santa'),
       ('Carnavales', '2024-02-21', '2024-02-25', 'Celebración de Carnavales'),
       ('Fiestas patrias', '2024-07-28', '2024-07-29', 'Celebración de las fiestas patrias.');

