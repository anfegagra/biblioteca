/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eafit.biblioteca.bd;

import com.eafit.biblioteca.general.Config;
/**
 *
 * @author andres.granados
 */
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Conexion {

	private static final String SERVIDOR = Config.SERVIDOR;
	private static final int PUERTO = Config.PUERTO;
	private static final String BD = Config.BD;
	private static final String NOMBRE_USUARIO = Config.NOMBRE_USUARIO;
	private static final String CONTRASENA_USUARIO = Config.CONTRASENA_USUARIO;

	public static Connection getConexion() throws SQLException {
		MysqlDataSource ds = new MysqlDataSource();
		ds.setServerName(SERVIDOR);
		ds.setPortNumber(PUERTO);
		ds.setDatabaseName(BD);
		ds.setUser(NOMBRE_USUARIO);
		ds.setPassword(CONTRASENA_USUARIO);
		ds.setRequireSSL(false);
		ds.setUseSSL(false);

		return ds.getConnection();
	}
}
