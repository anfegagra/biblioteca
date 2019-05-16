package com.eafit.biblioteca.principal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eafit.biblioteca.dto.Libro;
import com.eafit.biblioteca.dto.LibroDAO;
import com.eafit.biblioteca.dto.LibroDAOMySQL;
import com.eafit.biblioteca.dto.PrestamoDAO;
import com.eafit.biblioteca.dto.PrestamoDAOMySQL;
import com.eafit.biblioteca.dto.Usuario;
import com.eafit.biblioteca.dto.UsuarioDAO;
import com.eafit.biblioteca.dto.UsuarioDAOMySLQL;
import com.eafit.biblioteca.excepcion.LibroExistenteException;

public class Main {

	public static void main(String[] args) {

		LibroDAO libroDao = new LibroDAOMySQL();
		PrestamoDAO prestamoDao = new PrestamoDAOMySQL();
		UsuarioDAO usuarioDao = new UsuarioDAOMySLQL();
		List<Libro> libros = new ArrayList<>();
		Libro libro = null;

		try {

			Usuario usuario = new Usuario("Andres", "1234");
			usuarioDao.iniciarSesion(usuario);

			System.out.println("-------------------agregar-------------------");
			libro = libroDao.obtenerPorNombre("LibroKKK");
			if (libro == null) {
				libroDao.agregar(new Libro("LibroKKK", "DescripciónZ", "AutorZ", "GeneroZ"));
			} else {
				throw new LibroExistenteException();
			}

			System.out.println("-------------------buscar por nombre-------------------");
			libro = libroDao.obtenerPorNombre("LibroX");
			System.out.println(libro.getNombre());

			System.out.println("-------------------buscar por autor-------------------");
			libros = libroDao.obtenerPorAutor("AutorX");
			System.out.println(libros.get(0).getAutor());

			System.out.println("-------------------buscar por genero-------------------");
			libros = libroDao.obtenerPorGenero("GeneroX");
			System.out.println(libros.get(0).getGenero());

			System.out.println("-------------------prestarLibro-------------------");
			prestamoDao.prestarLibro(libros.get(0), usuario.getNombre());

			System.out.println("-------------------renovarPrestamo-------------------");
			prestamoDao.renovarPrestamo(libros.get(0), usuario.getNombre(), "2019/06/15");

			System.out.println("-------------------devolverLibro-------------------");
			prestamoDao.devolverLibro(libros.get(0), usuario.getNombre());

		} catch (LibroExistenteException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println("Hay problemas para acceder a la base de datos: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Hubo un error: " + e.getMessage());
		}

	}

}
