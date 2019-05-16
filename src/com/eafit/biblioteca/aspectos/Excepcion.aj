package com.eafit.biblioteca.aspectos;

import com.eafit.biblioteca.excepcion.LibroExistenteException;

public aspect Excepcion {

	pointcut handleLibroExistenteException(): 
		handler(LibroExistenteException);

	before() : handleLibroExistenteException()  {
		System.out.println("El libro que está intentando agregar ya se encuentra en la base de datos");
	}

}
