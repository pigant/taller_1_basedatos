package com.ignacio.tienda.DAL.exception;

import java.sql.SQLException;

/**
 *
 * @author ignacio
 */
public class CodigoRepetidoException extends Exception {

	public CodigoRepetidoException(SQLException ex) {
		super(ex);
	}
	
}
