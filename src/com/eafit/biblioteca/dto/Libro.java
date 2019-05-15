/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eafit.biblioteca.dto;

/**
 *
 * @author andres.granados
 */
public class Libro {

	private Integer id;
	private String nombre;
	private String descripcion;
	private String autor;
	private String genero;
	private boolean prestado;

	public Libro() {
		nombre = "";
	}

	public Libro(Integer id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Libro(String nombre, String descripcion, String autor, String genero) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.autor = autor;
		this.genero = genero;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		if (id <= 0 || id == null)
			throw new IllegalArgumentException("El id debe ser positivo");
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre.equals("") || nombre.length() < 3) {
			throw new IllegalArgumentException("El nombre debe tener al menos tres caracteres");
		}
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public boolean isPrestado() {
		return prestado;
	}

	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}

}
