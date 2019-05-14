package com.eafit.biblioteca.aspectos;

import org.aspectj.lang.Signature;

import com.eafit.biblioteca.dto.Libro;

public aspect Agregar {

	pointcut callAgregar(): 
		call(void com.eafit.biblioteca.dto.LibroDAO.agregar(Libro)) ;

	after() : callAgregar()  {
		System.out.println("Se agrego un libro");
	}

}
