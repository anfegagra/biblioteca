/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eafit.biblioteca.dto;

import java.util.List;

public interface LibroDAO {

	public void agregar(Libro c) throws Exception;

	public void modificar(Libro c) throws Exception;

	public void retirar(Libro c) throws Exception;

	public List<Libro> obtenerTodos() throws Exception;
	
	public List<Libro> obtenerPorNombre() throws Exception;
	
	public List<Libro> obtenerPorAutor() throws Exception;
	
	public List<Libro> obtenerPorGenero() throws Exception;

	public Libro obtenerPorId(Integer id) throws Exception;
}
