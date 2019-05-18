package com.eafit.biblioteca.dto;

import java.util.List;

public interface PrestamoDAO {

	public void prestarLibro(Libro l, String usuario) throws Exception;

	public void devolverLibro(Libro l, String usuario) throws Exception;

	public void renovarPrestamo(Libro l, String usuario, String fechaFin) throws Exception;
	
	public List<Prestamo> consultarPrestamos() throws Exception;
	
	public void agregarRegistroPrestamo(Prestamo prestamo) throws Exception;

}
