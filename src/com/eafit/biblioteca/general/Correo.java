package com.eafit.biblioteca.general;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Correo {

	public void enviarCorreo(String destinatario, String asunto, String msj) {
		final String usuario = "anfegagra.94@gmail.com";
		final String contrasena = "prueba.7";
		String receptor = destinatario;

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
			mensaje.setSubject(asunto);
			mensaje.setText(msj);

			Transport.send(mensaje);

			System.out.println("El mensaje ha sido enviado exitosamente!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
