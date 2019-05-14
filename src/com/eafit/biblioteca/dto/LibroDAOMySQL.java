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
		String query = "INSERT INTO libro (nombre, descripcion) values(?, ?)";
		ps = conn.prepareStatement(query);
		ps.setString(1, c.getNombre());
		ps.setString(2, c.getDescripcion());
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
		List<Libro> categorias = new ArrayList<>();
		conn = Conexion.getConexion();
		String s = "SELECT * FROM libro";
		st = conn.createStatement();
		rs = st.executeQuery(s);
		while (rs.next()) {
			categorias.add(new Libro(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion")));
		}
		conn.close();
		return categorias;
	}

	@Override
	public Libro obtenerPorId(Integer id) throws SQLException, InstantiationException, IllegalAccessException {
		Libro categoria = null;
		conn = Conexion.getConexion();
		String query = "SELECT * FROM libro WHERE id=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		rs = ps.executeQuery();

		if (rs.next()) {
			categoria = new Libro(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"));
		}
		conn.close();
		return categoria;
	}

	@Override
	public List<Libro> obtenerPorNombre() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Libro> obtenerPorAutor() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Libro> obtenerPorGenero() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
