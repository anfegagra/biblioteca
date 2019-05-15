package com.eafit.biblioteca.aspectos;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.eafit.biblioteca.dto.Libro;

public aspect RegistroLibro {

	pointcut registrarLibro(): 
		call(void com.eafit.biblioteca.dto.LibroDAO.agregar(Libro)) ;

	after() : registrarLibro()  {
		System.out.println("Se agrego un libro");

		final String usuario = "anfegagra.94@gmail.com";
		final String contrasena = "prueba.7";
		String receptor = "pipe-777@hotmail.com";

		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usuario, contrasena);
			}
		});

		try {

			Message mensaje = new MimeMessage(session);
			mensaje.setFrom(new InternetAddress(usuario));
			mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receptor));
			mensaje.setSubject("Registro exitoso");
			mensaje.setText("El libro se ha registrado correctamente en la base de datos.");

			Transport.send(mensaje);

			System.out.println("El mensaje ha sido enviado exitosamente!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
