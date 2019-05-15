package com.eafit.biblioteca.dto;

/**
*
* @author Juan David Loaiza
*
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.eafit.biblioteca.bd.Conexion;

public class PrestamoDAOMySQL implements PrestamoDAO {
	
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = null;
	Conexion conexion = null;
	
	@Override
	public void prestarLibro(Libro l, String usuario) throws SQLException, InstantiationException, IllegalAccessException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		conn = Conexion.getConexion();
		String query = "INSERT INTO Prestamo (id_libro, usuario, fechaInicio, fechaFin) values(?, ?, ?, ?, ?)";
		ps = conn.prepareStatement(query);
		ps.setInt(1, l.getId());
		ps.setString(2, usuario);
		ps.setString(3, dateFormat.format(date));
		ps.setString(4, fechaFinPrestamo(date, dateFormat));
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void renovarPrestamo(Libro l, String usuario, Date fechaFin) throws SQLException, InstantiationException, IllegalAccessException {
		conn = Conexion.getConexion();
		String query = "UPDATE Prestamo SET fechaFin=? WHERE id = ? AND usuario = ?";
		ps = conn.prepareStatement(query);
		ps.setString(1, parseDate(fechaFin));
		ps.setInt(3, l.getId());
		ps.setString(4, usuario);
		ps.executeUpdate();
		conn.close();
	}
 
	@Override
	public void devolverLibro(Libro l, String usuario) throws SQLException, InstantiationException, IllegalAccessException {
		conn = Conexion.getConexion();
		String query = "DELETE FROM Prestamo WHERE id_libro=? AND usuario = ?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, l.getId());
		ps.setString(2, usuario);
		ps.executeUpdate();
		conn.close();
	}
	
	private static String fechaFinPrestamo(Date fecha, DateFormat dateFormat){
	      Calendar calendar = Calendar.getInstance();
	      calendar.setTime(fecha); 
	      calendar.add(Calendar.DAY_OF_YEAR, 15);  
	      return dateFormat.format(calendar.getTime()); 
	}
}
