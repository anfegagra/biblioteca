package com.eafit.biblioteca.aspectos;

import com.eafit.biblioteca.dto.Prestamo;
import com.eafit.biblioteca.dto.PrestamoDAO;
import com.eafit.biblioteca.dto.PrestamoDAOMySQL;
import com.eafit.biblioteca.dto.Usuario;
import com.eafit.biblioteca.excepcion.LibroExistenteException;
import com.eafit.biblioteca.general.Correo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.eafit.biblioteca.backup.ManejoArchivo;

public aspect BackupPrestamos {
	
	Prestamo prestamo = null;
	PrestamoDAO prestamoDAO = new PrestamoDAOMySQL();
	ManejoArchivo manejoArchivo = new ManejoArchivo();

	
	pointcut escribirPrestamos(): 
		execution(* com.eafit.biblioteca.dto.PrestamoDAO.prestarLibro(..))
		|| execution(* com.eafit.biblioteca.dto.PrestamoDAO.renovarPrestamo(..))
		|| execution(* com.eafit.biblioteca.dto.PrestamoDAO.devolverLibro(..));

	after(): escribirPrestamos()  {
		XSSFWorkbook workbook = new XSSFWorkbook(); 
        XSSFSheet sheet = workbook.createSheet("Prestamos"); 
        
        try {
	        String[][] arrayPrestamos = manejoArchivo.MapPrestamo(prestamoDAO.consultarPrestamos());
	
	        int rownum = 0; 
	        for (String[] prestamo : arrayPrestamos) { 
	          Row row = sheet.createRow(rownum++); 
	          int cellnum = 0; 
	          for (String campo : prestamo) { 
	              Cell cell = row.createCell(cellnum++); 
	              
	              cell.setCellValue(campo); 
	              
	          } 
	        } 

	        FileOutputStream out = new FileOutputStream(new File("BackupGeneradoPrestamos.xlsx")); 
	        workbook.write(out); 
	        out.close(); 
	        workbook.close();
	    } 
	    catch (Exception e) { 
	        e.printStackTrace(); 
	    } 
	}

	
	pointcut lecturaExcel(): 
		call(* com.eafit.biblioteca.backup.ManejoArchivo.CargarPrestamos());

	before(): lecturaExcel()  {
		File excelFile = new File("BackupPrestamos.xlsx");
		try {
			FileInputStream fis = new FileInputStream(excelFile);
	        XSSFWorkbook workbook = new XSSFWorkbook(fis);
	        XSSFSheet sheet = workbook.getSheetAt(0);
	        
			int filas = sheet.getPhysicalNumberOfRows();
	        int columnas = sheet.getRow(0).getPhysicalNumberOfCells();
	        
	        System.out.println(filas + " "+ columnas);
	
	        String [][] valores = new String[filas][columnas - 2];
	        int i = 0;
	        int j;
	
	        Iterator<Row> itFilas = sheet.iterator();
	
	        while (itFilas.hasNext()) {
	            Row fila = itFilas.next();
	            j = 0;
	            Iterator<Cell> itCelda = fila.cellIterator();
	            while (itCelda.hasNext()) {
	            	Cell celda = itCelda.next(); 
	            	if(j<4) {
		            	if(celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
		            		valores[i][j] = Double.toString(celda.getNumericCellValue());
		            	}else if(celda.getCellType() == Cell.CELL_TYPE_STRING) {
		            		valores[i][j] =  celda.getStringCellValue();
		            	}
	            	}            	
	                j++;
	            }
	            i++;
	        }
	        for(String[] registro: valores) {
        		prestamo = new Prestamo(Integer.parseInt(registro[0]), registro[1], registro[2], registro[3]);       	
        		prestamoDAO.agregarRegistroPrestamo(prestamo);	        	
	        }
        }catch(Exception e) {
        	System.out.println(e);
        }
	}
}
