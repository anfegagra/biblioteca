package com.eafit.biblioteca.dto;

public interface PrestamoDAO {

	public void prestarLibro(Libro l, String usuario) throws Exception;

	public void devolverLibro(Libro l, String usuario) throws Exception;

	public void renovarPrestamo(Libro l, String usuario, String fechaFin) throws Exception;

}
