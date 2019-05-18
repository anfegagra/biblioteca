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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.eafit.biblioteca.bd.Conexion;
import com.eafit.biblioteca.excepcion.LibroExistenteException;

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
		conn = Conexion.getConexion();
		query = "UPDATE Libro SET prestado=FALSE WHERE id = ?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, l.getId());
		ps.executeUpdate();
		conn.close();
	}
	
	private static String fechaFinPrestamo(Date fecha, DateFormat dateFormat){
	      Calendar calendar = Calendar.getInstance();
	      calendar.setTime(fecha); 
	      calendar.add(Calendar.DAY_OF_YEAR, 15);  
	      return dateFormat.format(calendar.getTime()); 
	}
	
	@Override
	public List<Prestamo> consultarPrestamos() throws SQLException, InstantiationException, IllegalAccessException {
		List<Prestamo> prestamos = new ArrayList<>();
		conn = Conexion.getConexion();
		String s = "SELECT * FROM prestamo";
		st = conn.createStatement();
		rs = st.executeQuery(s);
		while (rs.next()) {
			prestamos.add(new Prestamo(rs.getInt("id"), rs.getInt("id_libro"), rs.getString("usuario"), 
					rs.getString("fechaInicio"), rs.getString("fechaFin")));
		}
		conn.close();
		return prestamos;
	}
	
	@Override
	public void agregarRegistroPrestamo(Prestamo c) throws SQLException, LibroExistenteException, InstantiationException, IllegalAccessException {
		conn = Conexion.getConexion();
		String query = "INSERT INTO prestamo (id_libro, usuario, fechaInicio, fechaFin)  values(?, ?, ?, ?, ?)";
		ps = conn.prepareStatement(query);
		ps.setInt(1, c.getIdLibro());
		ps.setString(2, c.getUsuario());
		ps.setString(3, c.getFechaInicio());
		ps.setString(4, c.getFechaFin());
		ps.executeUpdate();
		conn.close();
	}
}
