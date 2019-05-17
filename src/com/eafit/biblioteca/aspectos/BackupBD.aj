package com.eafit.biblioteca.aspectos;

import com.eafit.biblioteca.dto.Libro;
import com.eafit.biblioteca.dto.LibroDAO;
import com.eafit.biblioteca.dto.LibroDAOMySQL;
import com.eafit.biblioteca.dto.Usuario;
import com.eafit.biblioteca.general.Correo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.eafit.biblioteca.backup.ManejoArchivo;

public aspect BackupBD {
	
	Libro libro = null;
	LibroDAO libroDao = new LibroDAOMySQL();
	ManejoArchivo manejoArchivo = new ManejoArchivo();

	pointcut pruebaPT(ManejoArchivo manejoArchivo, String mensaje): 
		call(* com.eafit.biblioteca.backup.ManejoArchivo.prueba(String)) && args(mensaje) && target(manejoArchivo);

	before(ManejoArchivo manejoArchivo, String mensaje): pruebaPT(manejoArchivo, mensaje)  {
		System.out.println(" hhff"+mensaje);
	}
	
	pointcut escribir(): 
		execution(* com.eafit.biblioteca.dto.LibroDAO.agregar(*));

	after(): escribir()  {
		System.out.println("aaaa");
		XSSFWorkbook workbook = new XSSFWorkbook(); 
		  
        // Create a blank sheet 
        XSSFSheet sheet = workbook.createSheet("Libros"); 
        
        try {
	        String[][] arrayLibros = manejoArchivo.MapLibro(libroDao.obtenerTodosConEstado());
	
	        int rownum = 0; 
	        for (String[] libro : arrayLibros) { 
	          // this creates a new row in the sheet 
	          Row row = sheet.createRow(rownum++); 
	          int cellnum = 0; 
	          for (String campo : libro) { 
	              // this line creates a cell in the next column of that row 
	              Cell cell = row.createCell(cellnum++); 
	              
	              cell.setCellValue(campo); 
	              
	          } 
	        } 
		    
	        // this Writes the workbook gfgcontribute 
	        FileOutputStream out = new FileOutputStream(new File("backupgenerado.xlsx")); 
	        workbook.write(out); 
	        out.close(); 
	        workbook.close();
	    } 
	    catch (Exception e) { 
	        e.printStackTrace(); 
	    } 
	}

	
	pointcut lecturaExcel(): 
		call(* com.eafit.biblioteca.backup.ManejoArchivo.LeerValoresPrueba());

	before(): lecturaExcel()  {
		File excelFile = new File("Backup.xlsx");
		try {
			FileInputStream fis = new FileInputStream(excelFile);
	        XSSFWorkbook workbook = new XSSFWorkbook(fis);
	        XSSFSheet sheet = workbook.getSheetAt(0);
	        
			int filas = sheet.getPhysicalNumberOfRows();
	        int columnas = sheet.getRow(0).getPhysicalNumberOfCells();
	
	        String [][] valores = new String[filas][columnas];
	        int i = 0;
	        int j;
	
	        Iterator<Row> itFilas = sheet.iterator();
	
	        while (itFilas.hasNext()) {
	            Row fila = itFilas.next();
	            j = 0;
	            Iterator<Cell> itCelda = fila.cellIterator();
	            while (itCelda.hasNext()) {
	            	Cell celda = itCelda.next(); 
	            	if(celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
	            		valores[i][j] = Double.toString(celda.getNumericCellValue());
	            	}else if(celda.getCellType() == Cell.CELL_TYPE_STRING) {
	            		valores[i][j] =  celda.getStringCellValue();
	            	}
	                j++;
	            }
	            i++;
	        }
	        for(String[] registro: valores) {
	        	libro = new Libro(registro[0], registro[1], registro[2], registro[3], 
	        			Boolean.parseBoolean(registro[4]));       	
        		libroDao.agregar(libro);
	        }
        }catch(Exception e) {}
	}
}
