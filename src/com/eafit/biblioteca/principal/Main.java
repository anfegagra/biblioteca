package com.eafit.biblioteca.principal;

import java.sql.SQLException;

import com.eafit.biblioteca.dto.Libro;
import com.eafit.biblioteca.dto.LibroDAO;
import com.eafit.biblioteca.dto.LibroDAOMySQL;

public class Main {

	public static void main(String[] args) {

		LibroDAO libroDao = new LibroDAOMySQL();

		try {

			System.out.println("-------------------agregar-------------------");
			libroDao.agregar(new Libro(1, "Libro1", "..."));

			System.out.println("-------------------agregar-------------------");
			libroDao.agregar(new Libro(2, "Libro2", "..."));
			
		} catch (SQLException e) {
			System.out.println("Hay problemas para acceder a la base de datos: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Hubo un error: " + e.getMessage());
		}

	}

}
