package com.eafit.biblioteca.backup;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.eafit.biblioteca.dto.Libro;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class ManejoArchivo {
	
	private static final CellType STRING = null;
	private static final CellType NUMERIC = null;

	public String [][] LeerValores(XSSFSheet sheet){
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
        return valores;
    }
	
	public void GenerarBackup(List<Libro> libros) {
		XSSFWorkbook workbook = new XSSFWorkbook(); 
		  
        // Create a blank sheet 
        XSSFSheet sheet = workbook.createSheet("1"); 
        
        String[][] arrayLibros = MapLibro(libros);

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

//        for (String[] libro : arrayLibros) { 
//            // this creates a new row in the sheet 
//            Row row = sheet.createRow(rownum++); 
//            int cellnum = 0; 
//            for (String campo : libro) { 
//                // this line creates a cell in the next column of that row 
//                Cell cell = row.createCell(cellnum++); 
//                
//                cell.setCellValue(campo); 
//                
//            } 
//        } 
        try { 
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

}
