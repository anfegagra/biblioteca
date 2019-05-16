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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.eafit.biblioteca.bd.Conexion;

public class LibroDAOMySQL implements LibroDAO {

	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = null;
	Conexion conexion = null;

	@Override
	public void agregar(Libro c) throws SQLException, InstantiationException, IllegalAccessException {
		conn = Conexion.getConexion();
		String query = "INSERT INTO libro (nombre, descripcion, autor, genero, prestado) values(?, ?, ?, ?, ?)";
		ps = conn.prepareStatement(query);
		ps.setString(1, c.getNombre());
		ps.setString(2, c.getDescripcion());
		ps.setString(3, c.getAutor());
		ps.setString(4, c.getGenero());
		ps.setBoolean(5, c.isPrestado());
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void modificar(Libro c) throws SQLException, InstantiationException, IllegalAccessException {
		conn = Conexion.getConexion();
		String query = "UPDATE libro SET nombre=?, descripcion=? WHERE id =  ?";
		ps = conn.prepareStatement(query);
		ps.setString(1, c.getNombre());
		ps.setString(2, c.getDescripcion());
		ps.setInt(3, c.getId());
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void retirar(Libro c) throws SQLException, InstantiationException, IllegalAccessException {
		conn = Conexion.getConexion();
		String query = "DELETE FROM libro WHERE id=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, c.getId());
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public List<Libro> obtenerTodos() throws SQLException, InstantiationException, IllegalAccessException {
		List<Libro> libros = new ArrayList<>();
		conn = Conexion.getConexion();
		String s = "SELECT * FROM libro";
		st = conn.createStatement();
		rs = st.executeQuery(s);
		while (rs.next()) {
			libros.add(new Libro(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"), rs.getString("autor"),
					rs.getString("genero")));
		}
		conn.close();
		return libros;
	}

	@Override
	public Libro obtenerPorId(Integer id) throws SQLException, InstantiationException, IllegalAccessException {
		Libro libro = null;
		conn = Conexion.getConexion();
		String query = "SELECT * FROM libro WHERE id=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		rs = ps.executeQuery();

		if (rs.next()) {
			libro = new Libro(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"));
		}
		conn.close();
		return libro;
	}

	@Override
	public List<Libro> obtenerPorNombre(String nombre) throws Exception {
		List<Libro> libros = new ArrayList<>();
		conn = Conexion.getConexion();
		String query = "SELECT * FROM biblioteca.libro WHERE nombre = ?";
		ps = conn.prepareStatement(query);
		ps.setString(1, nombre);
		rs = ps.executeQuery();
		while (rs.next()) {
			libros.add(new Libro(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"), rs.getString("autor"),
					rs.getString("genero")));
		}
		conn.close();
		return libros;
	}

	@Override
	public List<Libro> obtenerPorAutor(String autor) throws Exception {
		List<Libro> libros = new ArrayList<>();
		conn = Conexion.getConexion();
		String query = "SELECT * FROM biblioteca.libro WHERE autor = ?";
		ps = conn.prepareStatement(query);
		ps.setString(1, autor);
		rs = ps.executeQuery();
		while (rs.next()) {
			libros.add(new Libro(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"), rs.getString("autor"),
					rs.getString("genero")));
		}
		conn.close();
		return libros;
	}

	@Override
	public List<Libro> obtenerPorGenero(String genero) throws Exception {
		List<Libro> categorias = new ArrayList<>();
		conn = Conexion.getConexion();
		String query = "SELECT * FROM biblioteca.libro WHERE genero = ?";
		ps = conn.prepareStatement(query);
		ps.setString(1, genero);
		rs = ps.executeQuery();
		while (rs.next()) {
			categorias.add(new Libro(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"), rs.getString("autor"),
					rs.getString("genero")));
		}
		conn.close();
		return categorias;
	}

}
