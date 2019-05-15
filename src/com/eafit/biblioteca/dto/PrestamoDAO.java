package com.eafit.biblioteca.dto;

import java.util.Date;

public interface PrestamoDAO {

	public void prestarLibro(Libro l, String usuario) throws Exception;

	public void devolverLibro(Libro l, String usuario) throws Exception;

	public void renovarPrestamo(Libro l, String usuario, Date fechaFin) throws Exception;

}
