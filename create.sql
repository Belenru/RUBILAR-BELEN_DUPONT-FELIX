DROP TABLE IF EXISTS ODONTOLOGO;
CREATE TABLE ODONTOLOGO (ID INT AUTO_INCREMENT PRIMARY KEY, NOMBRE VARCHAR(100) NOT NULL, APELLIDO VARCHAR(100) NOT NULL, NUMERO_MATRICULA VARCHAR(100));

// test

INSERT INTO ODONTOLOGO (NOMBRE, APELLIDO, NUMERO_MATRICULA) VALUES ('Claudio', 'Valencia', 87742585-k, 1), ('Magdalena', 'Vega', 23784783-k, 2) ;
