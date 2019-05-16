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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
		String query = "INSERT INTO Prestamo (id_libro, usuario, fechaInicio, fechaFin) values(?, ?, ?, ?)";
		ps = conn.prepareStatement(query);
		ps.setInt(1, l.getId());
		ps.setString(2, usuario);
		ps.setString(3, (dateFormat.format(date)).toString());
		ps.setString(4, fechaFinPrestamo(date, dateFormat));
		ps.executeUpdate();
		conn.close();
		conn = Conexion.getConexion();
		query = "UPDATE Libro SET prestado=TRUE WHERE id = ?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, l.getId());
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void renovarPrestamo(Libro l, String usuario, String fechaFin) throws SQLException, InstantiationException, IllegalAccessException {
		conn = Conexion.getConexion();
		String query = "UPDATE Prestamo SET fechaFin=? WHERE id_libro = ? AND usuario = ?";
		ps = conn.prepareStatement(query);
		ps.setString(1, fechaFin);
		ps.setInt(2, l.getId());
		ps.setString(3, usuario);
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
