package com.eafit.biblioteca.principal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eafit.biblioteca.dto.Libro;
import com.eafit.biblioteca.dto.LibroDAO;
import com.eafit.biblioteca.dto.LibroDAOMySQL;
import com.eafit.biblioteca.dto.Usuario;
import com.eafit.biblioteca.dto.UsuarioDAO;
import com.eafit.biblioteca.dto.UsuarioDAOMySLQL;

public class Main {

	public static void main(String[] args) {

		LibroDAO libroDao = new LibroDAOMySQL();
		UsuarioDAO usuarioDao = new UsuarioDAOMySLQL();
		List<Libro> libros = new ArrayList<>();

		try {
			
			Usuario usuario = new Usuario("Andres", "1234");
			usuarioDao.iniciarSesion(usuario);

			System.out.println("-------------------agregar-------------------");
			libroDao.agregar(new Libro("LibroX", "DescripciónX", "AutorX", "GeneroX"));

			System.out.println("-------------------buscar por nombre-------------------");

			libros = libroDao.obtenerPorNombre("LibroX");
			System.out.println(libros.get(0).getNombre());

			System.out.println("-------------------buscar por autor-------------------");
			libros = libroDao.obtenerPorAutor("AutorX");
			System.out.println(libros.get(0).getAutor());

			System.out.println("-------------------buscar por genero-------------------");
			libros = libroDao.obtenerPorGenero("GeneroX");
			System.out.println(libros.get(0).getGenero());

		} catch (SQLException e) {
			System.out.println("Hay problemas para acceder a la base de datos: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Hubo un error: " + e.getMessage());
		}

	}

}
