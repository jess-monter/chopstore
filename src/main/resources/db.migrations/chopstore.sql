CREATE TABLE chopstore.usuario (
	idusuario INT auto_increment NOT NULL,
	nombre varchar(256) NULL,
	apellido varchar(256) NULL,
	correo varchar(256) NOT NULL,
	contrasena varchar(256) NOT NULL,
	rol varchar(9) NOT NULL,
	telefono varchar(10) NULL,
	CONSTRAINT usuario_PK PRIMARY KEY (idusuario)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE chopstore.categoria (
	idcategoria INT auto_increment NOT NULL,
	nombre varchar(256) NOT NULL,
	CONSTRAINT categoria_PK PRIMARY KEY (idcategoria)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE chopstore.producto (
	idproducto INT auto_increment NOT NULL,
	idusuario INT NOT NULL,
	idcategoria INT NOT NULL,
	nombre varchar(256) NOT NULL,
	descripcion varchar(256) NULL,
	precio DECIMAL(10,2) NOT NULL,
	imagen varchar(256) NOT NULL,
	cantidad INT NOT NULL,
	detalles varchar(256) NULL,
	CONSTRAINT producto_PK PRIMARY KEY (idproducto),
	CONSTRAINT producto_FK FOREIGN KEY (idusuario) REFERENCES chopstore.usuario(idusuario) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT producto_FK_1 FOREIGN KEY (idcategoria) REFERENCES chopstore.categoria(idcategoria) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE chopstore.resena (
	idresena INT auto_increment NOT NULL,
	idusuario INT NOT NULL,
	idproducto INT NOT NULL,
	calificacion INT NOT NULL,
	comentario varchar(256) NULL,
	CONSTRAINT resena_PK PRIMARY KEY (idresena),
	CONSTRAINT resena_FK FOREIGN KEY (idusuario) REFERENCES chopstore.usuario(idusuario) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT resena_FK_1 FOREIGN KEY (idproducto) REFERENCES chopstore.producto(idproducto) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE chopstore.compra (
	idcompra INT auto_increment NOT NULL,
	idusuario INT NOT NULL,
	pago DECIMAL(10,2) NOT NULL,
	fecha DATE NOT NULL,
	CONSTRAINT compra_PK PRIMARY KEY (idcompra),
	CONSTRAINT compra_FK FOREIGN KEY (idusuario) REFERENCES chopstore.usuario(idusuario) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE chopstore.involucrar (
	idcompra INT NOT NULL,
	idproducto INT NOT NULL,
	cantidad INT NOT NULL,
	CONSTRAINT involucrar_FK FOREIGN KEY (idcompra) REFERENCES chopstore.compra(idcompra) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT involucrar_FK_1 FOREIGN KEY (idproducto) REFERENCES chopstore.producto(idproducto) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO chopstore.categoria (nombre)
VALUES ("Accesorios");
INSERT INTO chopstore.categoria (nombre)
VALUES ("Alimentos y Bebidas");
INSERT INTO chopstore.categoria (nombre)
VALUES ("Arte");
INSERT INTO chopstore.categoria (nombre)
VALUES ("Bebé");
INSERT INTO chopstore.categoria (nombre)
VALUES ("Belleza");
INSERT INTO chopstore.categoria (nombre)
VALUES ("Deportes");
INSERT INTO chopstore.categoria (nombre)
VALUES ("Electrónicos");
INSERT INTO chopstore.categoria (nombre)
VALUES ("Hogar y Cocina");
INSERT INTO chopstore.categoria (nombre)
VALUES ("Industria y Ciencia");
INSERT INTO chopstore.categoria (nombre)
VALUES ("Instrumentos Musicales");
INSERT INTO chopstore.categoria (nombre)
VALUES ("Jugetes");
INSERT INTO chopstore.categoria (nombre)
VALUES ("Libros y Revistas");
INSERT INTO chopstore.categoria (nombre)
VALUES ("Mascotas");
INSERT INTO chopstore.categoria (nombre)
VALUES ("Música");
INSERT INTO chopstore.categoria (nombre)
VALUES ("Oficina y Papeleria");
INSERT INTO chopstore.categoria (nombre)
VALUES ("Películas y Series");
INSERT INTO chopstore.categoria (nombre)
VALUES ("Ropa y Zapatos");
INSERT INTO chopstore.categoria (nombre)
VALUES ("Salud");
INSERT INTO chopstore.categoria (nombre)
VALUES ("Software");
INSERT INTO chopstore.categoria (nombre)
VALUES ("Videojuegos");
