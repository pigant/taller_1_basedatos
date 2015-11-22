/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignacio.tienda.DAL;

import com.ignacio.tienda.BLL.Venta;
import com.ignacio.tienda.DAL.exception.CodigoRepetidoException;
import com.ignacio.tienda.DAL.exception.SinBaseDatosException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ignacio
 */
public class VentaDAL {

	public static Venta get(int id) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public Integer guardar(int rut) {
		Integer i = null;
		BD bd;
		try {
			bd = new BD();
			boolean x = bd.update("insert into venta (rut) values (?)", rut);
			if (x) {
				i = bd.lastId();
			}
		} catch (SinBaseDatosException ex) {
			Logger.getLogger(VentaDAL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (CodigoRepetidoException ex) {
			Logger.getLogger(VentaDAL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(VentaDAL.class.getName()).log(Level.SEVERE, null, ex);
		}

		return i;
	}

	public boolean actualizar(int rut, int idVenta) {
		boolean s = false;
		BD bd;
		try {
			bd = new BD();
			bd.update("update venta set rut=? where idVenta=?", rut, idVenta);
		} catch (SinBaseDatosException ex) {
			Logger.getLogger(VentaDAL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (CodigoRepetidoException ex) {
			Logger.getLogger(VentaDAL.class.getName()).log(Level.SEVERE, null, ex);
		}
		return s;
	}
}
