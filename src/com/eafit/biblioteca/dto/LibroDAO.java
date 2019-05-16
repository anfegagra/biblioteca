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
	
	public List<Libro> obtenerTodosConEstado() throws Exception;
	
	public Libro obtenerPorNombre(String nombre) throws Exception;
	
	public List<Libro> obtenerPorAutor(String autor) throws Exception;
	
	public List<Libro> obtenerPorGenero(String genero) throws Exception;

	public Libro obtenerPorId(Integer id) throws Exception;
}
