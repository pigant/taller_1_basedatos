/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignacio.tienda.DAL;

import com.ignacio.tienda.BLL.Detalle;
import com.ignacio.tienda.DAL.exception.CodigoRepetidoException;
import com.ignacio.tienda.DAL.exception.SinBaseDatosException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ignacio
 */
public class DetalleDAL {

	public static Detalle get(int id) {
		throw new UnsupportedOperationException("No implementado");
	}

	public Integer guardar(int idVenta, int codigoComic) {
		Integer s = null;
		BD bd = null;
		try {
			bd = new BD();
			boolean b = bd.update(
					"insert into detalle (id_venta, codigoComic) values (?,?)",
					idVenta, codigoComic);
			if (b) {
				s = bd.lastId();
			}
		} catch (SinBaseDatosException ex) {
			Logger.getLogger(DetalleDAL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (CodigoRepetidoException ex) {
			Logger.getLogger(DetalleDAL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(DetalleDAL.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			bd.close();
		}
		return s;
	}

	public boolean actualizar(final Integer idVenta,
			final Integer codigo, final Integer idDetalle) {
		//
		boolean s = false;
		try {
			BD bd;
			bd = new BD();
			s = bd.update("update detalle "
					+ "set id_venta=?, codigoComic=? "
					+ "where idDetalle=? ",
					idVenta, codigo, idDetalle);
		} catch (SinBaseDatosException ex) {
			Logger.getLogger(DetalleDAL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (CodigoRepetidoException ex) {
			Logger.getLogger(DetalleDAL.class.getName()).log(Level.SEVERE, null, ex);
		}
		return s;
	}

	public boolean borrar(final int idDetalle) {
		boolean s = false;
		BD bd;
		try {
			bd = new BD();
			s = bd.update("delete from detalle where idDetalle=?", idDetalle);
		} catch (SinBaseDatosException ex) {
			Logger.getLogger(DetalleDAL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (CodigoRepetidoException ex) {
			Logger.getLogger(DetalleDAL.class.getName()).log(Level.SEVERE, null, ex);
		}
		return s;
	}

}
