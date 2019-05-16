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

import com.eafit.biblioteca.dto.Libro;
import com.eafit.biblioteca.dto.LibroDAO;
import com.eafit.biblioteca.dto.LibroDAOMySQL;
import com.eafit.biblioteca.dto.Usuario;
import com.eafit.biblioteca.dto.UsuarioDAO;
import com.eafit.biblioteca.dto.UsuarioDAOMySLQL;
import com.eafit.biblioteca.backup.ManejoArchivo;

public class Main {

	public static void main(String[] args) {

		LibroDAO libroDao = new LibroDAOMySQL();
		UsuarioDAO usuarioDao = new UsuarioDAOMySLQL();
		List<Libro> libros = new ArrayList<>();
		ManejoArchivo manejoArchivo = new ManejoArchivo();
		
		File excelFile = new File("Backup.xlsx");
		try {
			FileInputStream fis = new FileInputStream(excelFile);
	        XSSFWorkbook workbook = new XSSFWorkbook(fis);
	        XSSFSheet sheet = workbook.getSheetAt(0);
	        String [][] valores = manejoArchivo.LeerValores(sheet);
	        workbook.close();
	        for(String[] registro: valores) {
	        	Libro libro = new Libro(registro[0], registro[1], registro[2], registro[3]);
	        	libroDao.agregar(libro);
	        }
	        

		}catch(Exception e) {
			System.out.println(e);
		}
		
		
		
		//manejoArchivo.GenerarBackup();
        
        

		try {
			libros = libroDao.obtenerTodos();
			
//			Usuario usuario = new Usuario("Andres", "1234");
//			usuarioDao.iniciarSesion(usuario);
//
//			System.out.println("-------------------agregar-------------------");
//			libroDao.agregar(new Libro("LibroX", "DescripciónX", "AutorX", "GeneroX"));
//
//			System.out.println("-------------------buscar por nombre-------------------");
//
			libros = libroDao.obtenerPorNombre("LibroX");
//			System.out.println(libros.get(0).getNombre());
//
//			System.out.println("-------------------buscar por autor-------------------");
//			libros = libroDao.obtenerPorAutor("AutorX");
//			System.out.println(libros.get(0).getAutor());
//
//			System.out.println("-------------------buscar por genero-------------------");
//			libros = libroDao.obtenerPorGenero("GeneroX");
//			System.out.println(libros.get(0).getGenero());

		} catch (SQLException e) {
			System.out.println("Hay problemas para acceder a la base de datos: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Hubo un error: " + e.getMessage());
		}

	}

}