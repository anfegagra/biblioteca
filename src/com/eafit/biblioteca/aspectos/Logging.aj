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
		LOGGER.info("Se realiz� una inserci�n en la base de datos.");
	}

	pointcut obtenerPorNombre(): 
		call(* com.eafit.biblioteca.dto.LibroDAO.obtenerPorNombre(String)) ;

	after() : obtenerPorNombre()  {
		LOGGER.info("Se realiz� una consulta de libro por nombre a la base de datos.");
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
