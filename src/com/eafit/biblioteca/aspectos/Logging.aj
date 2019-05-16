package com.eafit.biblioteca.aspectos;

import java.util.logging.Logger;

import com.eafit.biblioteca.dto.Libro;
import com.eafit.biblioteca.general.Log;

public aspect Logging {

	private static final Log log = new Log();
	private static final Logger LOGGER = log.obtenerLogger();

	pointcut agregarLibro(): 
		call(* com.eafit.biblioteca.dto.LibroDAO.agregar(Libro)) ;

	after() : agregarLibro()  {
		LOGGER.info("Se realizó una inserción en la base de datos.");
	}

	pointcut obtenerPorNombre(): 
		call(* com.eafit.biblioteca.dto.LibroDAO.obtenerPorNombre(String)) ;

	after() : obtenerPorNombre()  {
		LOGGER.info("Se realizó una consulta de libro por nombre a la base de datos.");
	}

	pointcut obtenerPorAutor(): 
		call(* com.eafit.biblioteca.dto.LibroDAO.obtenerPorAutor(String)) ;

	after() : obtenerPorAutor()  {
		LOGGER.info("Se realizó una consulta de libro por autor a la base de datos.");
	}

	pointcut obtenerPorGenero(): 
		call(* com.eafit.biblioteca.dto.LibroDAO.obtenerPorGenero(String)) ;

	after() : obtenerPorGenero()  {
		LOGGER.info("Se realizó una consulta de libro por género a la base de datos.");
	}

	pointcut prestarLibro(): 
		call(* com.eafit.biblioteca.dto.PrestamoDAO.prestarLibro(Libro, String)) ;

	after() : prestarLibro()  {
		LOGGER.info("Se realizó el préstamo de un libro.");
	}

	pointcut renovarPrestamo(): 
		call(* com.eafit.biblioteca.dto.PrestamoDAO.renovarPrestamo(Libro, String, String)) ;

	after() : renovarPrestamo()  {
		LOGGER.info("Se renovó el préstamo de un libro.");
	}

	pointcut devolverLibro(): 
		call(* com.eafit.biblioteca.dto.PrestamoDAO.devolverLibro(Libro, String)) ;

	after() : devolverLibro()  {
		LOGGER.info("Se realizó la devolución de un libro.");
	}

}
