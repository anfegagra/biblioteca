package com.eafit.biblioteca.general;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.eafit.biblioteca.principal.Main;

public class Log {

	private Logger LOGGER = Logger.getLogger(Main.class.getName());

	public Logger obtenerLogger() {
		Handler consoleHandler = null;
		Handler fileHandler = null;

		consoleHandler = new ConsoleHandler();
		try {
			fileHandler = new FileHandler("./biblioteca.log");
		} catch (SecurityException | IOException exception) {
			LOGGER.log(Level.SEVERE, "Ocurrió un error en el manejador de archivos.", exception);
		}

		LOGGER.addHandler(consoleHandler);
		LOGGER.addHandler(fileHandler);

		consoleHandler.setLevel(Level.ALL);
		fileHandler.setLevel(Level.ALL);
		LOGGER.setLevel(Level.ALL);

		LOGGER.removeHandler(consoleHandler);

		return LOGGER;
	}

}
