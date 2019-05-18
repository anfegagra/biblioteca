package com.eafit.biblioteca.backup;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.eafit.biblioteca.dto.Libro;
import com.eafit.biblioteca.dto.Prestamo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class ManejoArchivo {
	
	private static final CellType STRING = null;
	private static final CellType NUMERIC = null;
	
	
	public void CargarCatalogo(){}
	public void CargarPrestamos(){}

	
	
	public String[][] MapLibro(List<Libro> libros) {
		String[][] informacion = new String[libros.size()][6];
		
		for(int i = 0; i < libros.size(); i++) {
			informacion[i][0] = Integer.toString(libros.get(i).getId());
			informacion[i][1] = libros.get(i).getNombre();
			informacion[i][2] = libros.get(i).getDescripcion();
			informacion[i][3] = libros.get(i).getAutor();
			informacion[i][4] = libros.get(i).getGenero();
			informacion[i][5] = Boolean.toString(libros.get(i).isPrestado());
		}
		return informacion;
	}
	
	public String[][] MapPrestamo(List<Prestamo> prestamos) {
		String[][] informacion = new String[prestamos.size()][5];
		
		for(int i = 0; i < prestamos.size(); i++) {
			informacion[i][0] = Integer.toString(prestamos.get(i).getId());
			informacion[i][1] = Integer.toString(prestamos.get(i).getIdLibro());
			informacion[i][2] = prestamos.get(i).getUsuario();
			informacion[i][3] = prestamos.get(i).getFechaInicio();
			informacion[i][4] = prestamos.get(i).getFechaFin();
		}
		return informacion;
	}

}
