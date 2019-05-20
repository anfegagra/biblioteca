# Desarrollo de Software basado en componentes

Proyecto de una biblioteca realizado en Java con AspectJ

Aplicación de una biblioteca que permite consultar libros disponibles (búsquedas por id, nombre, autor, género), agregar, modificar, o retirar libros. Además permite hacer préstamos, devolver libros y renovar el préstamo de un libro.

La aplicación cuenta con los siguientes aspectos:
 
 - Logging: manejo de logs para cuando se ejecuta cualquier acción en la aplicación.
 - Notificación: se envía al correo electrónico de la persona que inició sesión una notificación con la información de los préstamos (préstamo, renovación o devolución).
 - Excepcion: se controlan las excepciones de la aplicación como por ejemplo cuando se intenta registrar en base de datos un libro ya existente.
 - Backup: se realizan dos tipos de backup, uno para tener una copia de los libros de la biblioteca y otro para tener una copia de los préstamos realizados.
 
Se deben incluír las siguientes librerías:

 - mysql-connector-java-5.1.47
 - mail
 - commons-codec-1.10
 - commons-collections4-4.1
 - curvesapi-1.04
 - dom4j-2.0.2
 - jaxen-1.1.6
 - poi-3.17
 - poi-ooxml-3.17
 - poi-ooxml-schemas-3.17
 - stax-api-1.0.1
 - xmlbeans-2.6.0
 
Nota: en el video llamado "funcionamiento" se muestra la aplicación de la biblioteca funcionando Adicionalmente para correr la aplicación se debe ejecutar el archivo VentanaPrincipal ubicado en el paquete com.eafit.biblioteca.principal, y además ejecutar los siguientes queries requeridos para el correcto funcionamiento:

CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

CREATE TABLE IF NOT EXISTS libro (
	id int(11) NOT NULL AUTO_INCREMENT, 
    nombre varchar(100) NOT NULL, 
    descripcion varchar(5000) NOT NULL,
    autor varchar(100) NOT NULL,
    genero varchar(100) NOT NULL,
    prestado bool NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS usuario (
	id int(11) NOT NULL AUTO_INCREMENT, 
    nombre varchar(100) NOT NULL,
    correo varchar(100) NOT NULL,
    contrasena varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO biblioteca.usuario values (0, "sunombre", "reemplazarporuncorreoreal", "contrasena");

CREATE TABLE IF NOT EXISTS Prestamo (
	id int(11) NOT NULL AUTO_INCREMENT, 
    id_libro int(11) NOT NULL, 
    usuario varchar(100) NOT NULL,
    fechaInicio varchar(100) NOT NULL,
    fechaFin varchar(100) NOT NULL,
    PRIMARY KEY (id)
);
