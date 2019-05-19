package com.eafit.biblioteca.aspectos;

import com.eafit.biblioteca.dto.Libro;
import com.eafit.biblioteca.dto.Usuario;
import com.eafit.biblioteca.general.Correo;

public aspect Notificacion {

	String correoDestino = "prueba@hotmail.com";
	Correo correo = new Correo();

	pointcut login(): 
		call(* com.eafit.biblioteca.dto.UsuarioDAO.iniciarSesion(Usuario));

	after() returning (Usuario u): login()  {
		correoDestino = u.getCorreo();
	}

	pointcut enviarCorreoPrestamo(Libro libro, String usuario): 
		call(void com.eafit.biblioteca.dto.PrestamoDAO.prestarLibro(Libro, String))
		&& args(libro, usuario);

	after(Libro libro, String usuario) : enviarCorreoPrestamo(libro, usuario)  {
		correo.enviarCorreo(correoDestino, "Préstamo exitoso!", "Hola " + usuario + ",\n" + " El libro con nombre '"
				+ libro.getNombre() + "' ha sido prestado exitosamente!.");
	}

	pointcut enviarCorreoRenovacion(): 
		call(void com.eafit.biblioteca.dto.PrestamoDAO.renovarPrestamo(Libro, String, String));

	after() : enviarCorreoRenovacion()  {
		correo.enviarCorreo(correoDestino, "Renovación exitosa!", "Has renovado el préstamo del libro exitosamente!.");
	}

	pointcut enviarCorreoDevolución(): 
		call(void com.eafit.biblioteca.dto.PrestamoDAO.devolverLibro(Libro, String));

	after() : enviarCorreoDevolución()  {
		correo.enviarCorreo(correoDestino, "Devolución exitosa!", "Has devuelto el libro exitosamente!.");
	}

}
