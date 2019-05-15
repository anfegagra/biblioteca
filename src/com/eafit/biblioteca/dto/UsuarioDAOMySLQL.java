package com.eafit.biblioteca.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.eafit.biblioteca.bd.Conexion;

public class UsuarioDAOMySLQL implements UsuarioDAO {
	
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = null;
	Conexion conexion = null;

	@Override
	public Usuario iniciarSesion(Usuario usuario) throws SQLException {
		Usuario u = null;
		conn = Conexion.getConexion();
		String query = "SELECT * FROM biblioteca.usuario WHERE nombre=? && contrasena=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, usuario.getNombre());
		ps.setString(2, usuario.getContrasena());
		rs = ps.executeQuery();

		if (rs.next()) {
			u = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("correo"));
		}
		conn.close();
		return u;
	}

}
