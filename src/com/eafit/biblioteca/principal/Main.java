package com.eafit.biblioteca.principal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.Scanner;

import com.eafit.biblioteca.dto.Libro;
import com.eafit.biblioteca.dto.LibroDAO;
import com.eafit.biblioteca.dto.LibroDAOMySQL;
import com.eafit.biblioteca.dto.PrestamoDAO;
import com.eafit.biblioteca.dto.PrestamoDAOMySQL;
import com.eafit.biblioteca.dto.Usuario;
import com.eafit.biblioteca.dto.UsuarioDAO;
import com.eafit.biblioteca.dto.UsuarioDAOMySLQL;
import com.eafit.biblioteca.excepcion.LibroExistenteException;
import com.eafit.biblioteca.backup.ManejoArchivo;

public class Main {

	private final static String[] INICIO_SESION = { "Iniciar sesion", "Salir" };
	private final static String[] MODULOS = { "Gestion de libros", "Gestion de prestamos", "Salir" };
	private final static String[] GESTION_LIBROS = { "Agregar libro", "Retirar libro", "Salir" };
	private final static String[] GESTION_PRESTAMOS = { "Prestar libro", "Devolver libro", "Renovar prestamo",
			"Salir" };

	private static boolean sesion = true;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		boolean inicioSesion = false;	
		
		LibroDAO libroDao = new LibroDAOMySQL();
		PrestamoDAO prestamoDao = new PrestamoDAOMySQL();
		UsuarioDAO usuarioDao = new UsuarioDAOMySLQL();
		List<Libro> libros = new ArrayList<>();
		Libro libro = null;
		ManejoArchivo manejoArchivo = new ManejoArchivo();
		
		manejoArchivo.CargarCatalogo();
		//manejoArchivo.CargarPrestamos();

		Scanner leer = new Scanner(System.in);
		int opcion = 0;

		do {
			if (!inicioSesion) {
				// Iniciar sesion
				desplegarMenu(INICIO_SESION);
				opcion = leer.nextInt();
				validarOpcion(opcion, INICIO_SESION);
				System.out.println("Ingreses usuario: ");
				String user = leer.next();
				System.out.println("Ingreses contrasenia: ");
				String password = leer.next();
				Usuario usuario = new Usuario(user, password);
				try {
					usuarioDao.iniciarSesion(usuario);
				}catch (Exception e) {}
				inicioSesion = true;
			}
			// Modulos
			desplegarMenu(MODULOS);
			opcion = leer.nextInt();
			validarOpcion(opcion, MODULOS);
			if (opcion == 0) {
				// Gestion Libros
				desplegarMenu(GESTION_LIBROS);
				opcion = leer.nextInt();
				validarOpcion(opcion, GESTION_LIBROS);
				if (opcion == 0) {
					// Agregar Libro
					System.out.println("Ingrese nombre: ");
					String nombre = leer.next();
					libro = libroDao.obtenerPorNombre(nombre);
					if (libro == null) {
						System.out.println("Ingrese Descripcion: ");
						String des = leer.next();
						System.out.println("Ingrese Autor: ");
						String autor = leer.next();
						System.out.println("Ingrese Genero: ");
						String gen = leer.next();
						libroDao.agregar(new Libro(nombre, des, autor, gen));
					} else {
						throw new LibroExistenteException();
					}
				} else {
					// Retirar Libro
					System.out.println("Ingrese nombre: ");
					String nombre = leer.next();
					libro = libroDao.obtenerPorNombre(nombre);
					if (libro == null) {
						libroDao.retirar(libro);
					} else {
						throw new LibroExistenteException();
					}
				}
			} else {
				// Gestion Prestamos
				desplegarMenu(GESTION_PRESTAMOS);
				opcion = leer.nextInt();
				validarOpcion(opcion, GESTION_PRESTAMOS);
				if (opcion == 0) {
					// Prestar Libro
					System.out.println("Ingrese nombre: ");
					String nombre = leer.next();
					libro = libroDao.obtenerPorNombre(nombre);
					if (libro == null) {
						System.out.println("Ingrese usuario: ");
						String user = leer.next();
						prestamoDao.prestarLibro(libro, user);
					} else {
						throw new LibroExistenteException();
					}
				}else if (opcion == 1) {
					// Devolver Libro
					System.out.println("Ingrese nombre: ");
					String nombre = leer.next();
					libro = libroDao.obtenerPorNombre(nombre);
					if (libro == null) {
						System.out.println("Ingrese usuario: ");
						String user = leer.next();
						prestamoDao.devolverLibro(libro, user);
					} else {
						throw new LibroExistenteException();
					}
				}else {
					// Renovar prestamo
					System.out.println("Ingrese nombre: ");
					String nombre = leer.next();
					libro = libroDao.obtenerPorNombre(nombre);
					if (libro == null) {
						System.out.println("Ingrese usuario: ");
						String user = leer.next();
						System.out.println("Ingrese nueva fecha: ");
						String fecha = leer.next();
						prestamoDao.renovarPrestamo(libro, user, fecha);
					} else {
						throw new LibroExistenteException();
					}
				}
			}

		} while (sesion);

		try {

			Usuario usuario = new Usuario("Andres", "1234");
			usuarioDao.iniciarSesion(usuario);

			System.out.println("-------------------agregar-------------------");
			libro = libroDao.obtenerPorNombre("LibroKKK");
			if (libro == null) {
				libroDao.agregar(new Libro("LibroKKK", "Descripciï¿½nZ", "AutorZ", "GeneroZ"));
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

	public static void desplegarMenu(String[] menu) {
		System.out.println("ELIJA UNA OPCION:");
		for (int i = 0; i < menu.length; i++) {
			System.out.println(i + ": " + menu[i]);
		}
	}

	public static void validarOpcion(int opcion, String[] menu) {
		if (opcion == menu.length - 1) {
			sesion = false;
			System.exit(0);
		}else if( opcion < 0 || opcion > menu.length - 1) {
			System.out.println("Ingreso opcion incorrecta, ejecute nuevamente el programa");
			System.exit(0);
		}

	}

}