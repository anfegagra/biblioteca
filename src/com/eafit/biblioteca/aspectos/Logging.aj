package com.eafit.biblioteca.aspectos;

import java.util.logging.Logger;

import com.eafit.biblioteca.dto.Libro;
import com.eafit.biblioteca.general.Log;

public aspect Logging {

	private static final Log log = new Log();
	private static final Logger LOGGER = log.obtenerLogger();

	pointcut agregarLibro(Libro libro): 
		call(* com.eafit.biblioteca.dto.LibroDAO.agregar(Libro)) 
		&& args(libro);

	after(Libro libro) : agregarLibro(libro)  {
		LOGGER.info("El libro con nombre '" + libro.getNombre() + "' se insert� en la base de datos.");
	}

	pointcut obtenerPorNombre(String nombre): 
		call(* com.eafit.biblioteca.dto.LibroDAO.obtenerPorNombre(String)) 
		&& args(nombre);

	after(String nombre) : obtenerPorNombre(nombre)  {
		LOGGER.info("El libro con nombre '" + nombre + "' fue consultado a la base de datos.");
	}
	
	pointcut obtenerTodosConEstado(): 
		call(* com.eafit.biblioteca.dto.LibroDAO.obtenerTodosConEstado());

	after() : obtenerTodosConEstado()  {
		LOGGER.info("Se realiz� una consulta de los libros disponibles.");
	}

	pointcut obtenerPorAutor(): 
		call(* com.eafit.biblioteca.dto.LibroDAO.obtenerPorAutor(String)) ;

	after() : obtenerPorAutor()  {
		LOGGER.info("Se realiz� una consulta de libro por autor a la base de datos.");
	}

	pointcut obtenerPorGenero(): 
		call(* com.eafit.biblioteca.dto.LibroDAO.obtenerPorGenero(String)) ;

	after() : obtenerPorGenero()  {
		LOGGER.info("Se realiz� una consulta de libro por g�nero a la base de datos.");
	}
	
	pointcut consultarPrestamos(): 
		call(* com.eafit.biblioteca.dto.PrestamoDAO.consultarPrestamos()) ;

	after() : consultarPrestamos()  {
		LOGGER.info("Se realiz� una consulta de los pr�stamos activos.");
	}

	pointcut prestarLibro(): 
		call(* com.eafit.biblioteca.dto.PrestamoDAO.prestarLibro(Libro, String)) ;

	after() : prestarLibro()  {
		LOGGER.info("Se realiz� el pr�stamo de un libro.");
	}

	pointcut renovarPrestamo(): 
		call(* com.eafit.biblioteca.dto.PrestamoDAO.renovarPrestamo(Libro, String, String)) ;

	after() : renovarPrestamo()  {
		LOGGER.info("Se renov� el pr�stamo de un libro.");
	}

	pointcut devolverLibro(): 
		call(* com.eafit.biblioteca.dto.PrestamoDAO.devolverLibro(Libro, String)) ;

	after() : devolverLibro()  {
		LOGGER.info("Se realiz� la devoluci�n de un libro.");
	}

}
