package com.eafit.biblioteca.aspectos;

import com.eafit.biblioteca.dto.Libro;
import com.eafit.biblioteca.dto.LibroDAO;
import com.eafit.biblioteca.dto.LibroDAOMySQL;
import com.eafit.biblioteca.dto.Usuario;
import com.eafit.biblioteca.general.Correo;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.eafit.biblioteca.backup.ManejoArchivo;

public aspect BackupBD {
	
	Libro libro = null;
	LibroDAO libroDao = new LibroDAOMySQL();

	pointcut pruebaPT(ManejoArchivo manejoArchivo, String mensaje): 
		call(* com.eafit.biblioteca.backup.ManejoArchivo.prueba(String)) && args(mensaje) && target(manejoArchivo)
		;

	before(ManejoArchivo manejoArchivo, String mensaje): pruebaPT(manejoArchivo, mensaje)  {
		System.out.println(" hhff"+mensaje);
	}
	
	pointcut lecturaExcel(ManejoArchivo manejoArchivo, XSSFSheet sheet): 
		call(* com.eafit.biblioteca.backup.ManejoArchivo.LeerValoresPrueba(*)) && args(sheet) 
		&& target(manejoArchivo);

	before(ManejoArchivo manejoArchivo, XSSFSheet sheet): lecturaExcel(manejoArchivo, sheet)  {
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
        	
        	try {
        		libroDao.agregar(libro);
        	}catch(Exception e) {}
        	
        }

	}
}
