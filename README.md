#Este es el repositorio correspondiente a la Tarea 4 de la materia Programacion I
#Dioris Arias - 2024076
#Tomar en consideracion que la base de datos usada es la MySQL como recomendada en el material
#Y que se creo una base de datos con las credenciales correspondintes para Username: root y Password: root

#Los codigos fuentes se encuentran en la carpeta src
#En MySQL se debe crear una base de datos y tablas de la siguiente forma:

-- Crear la base de datos
CREATE DATABASE usuarios_db;

-- Usar la base de datos
USE usuarios_db;

-- Crear la tabla de usuarios
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(50) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono VARCHAR(15),
    correo VARCHAR(100),
    contrasena VARCHAR(100) NOT NULL
);




