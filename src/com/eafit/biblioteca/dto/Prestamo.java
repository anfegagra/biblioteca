package com.eafit.biblioteca.dto;

public class Prestamo {

	private Integer id;
	private Integer id_libro;
	private String usuario;
	private Date fechaInicio;
	private Date fechaFin;

	public Prestamo() {
	}

	public Prestamo(Integer id, Integer id_libro, String usuario, Date fechaInicio, Date fechaFin) {
		this.id = id;
		this.id_libro = id_libro;
		this.usuario = usuario;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		if (id <= 0 || id == null)
			throw new IllegalArgumentException("El id debe ser positivo");
		this.id = id;
	}
	
	public Integer getIdLibro() {
		return id_libro;
	}

	public void setIdLibro(Integer id) {
		if (id_libro <= 0 || id_libro == null)
			throw new IllegalArgumentException("El id debe ser positivo");
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		if (usuario.equals("") || usuario.length() < 3) {
			throw new IllegalArgumentException("El usuario debe tener al menos tres caracteres");
		}
		this.usuario = usuario;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
}
