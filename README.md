# Desarrollo de Software basado en componentes

Proyecto de una biblioteca realizado en Java con AspectJ

Aplicación de una biblioteca que permite consultar libros disponibles (búsquedas por id, nombre, autor, género), agregar, modificar, o retirar libros. Además permite hacer préstamos, devolver libros y renovar el préstamo de un libro.

La aplicación cuenta con los siguientes aspectos:
 
 - Logging: manejo de logs para cuando se ejecuta cualquier acción en la aplicación.
 - Notificación: se envía al correo electrónico de la persona que inició sesión una notificación con la información de los préstamos (préstamo, renovación o devolución).
 - Excepcion: se controlan las excepciones de la aplicación como por ejemplo cuando se intenta registrar en base de datos un libro ya existente.
 - Backup: se realizan dos tipos de backup, uno para tener una copia de los libros de la biblioteca y otro para tener una copia de los préstamos realizados.
 