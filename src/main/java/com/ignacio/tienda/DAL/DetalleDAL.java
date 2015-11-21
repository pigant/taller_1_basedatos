package com.ignacio.tienda.DAL;

import com.ignacio.tienda.BLL.Comic;
import com.ignacio.tienda.BLL.Detalle;
import com.ignacio.tienda.BLL.Venta;
import com.ignacio.tienda.DAL.exception.CodigoRepetidoException;
import com.ignacio.tienda.DAL.exception.SinBaseDatosException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ignacio
 */
public class DetalleDAL {

	public static Detalle get(int id) {
		Detalle d = null;
		BD bd;
		try {
			bd = new BD();
			ArrayList<Object[]> a = bd.select("detalle", "idDetalle=" + id,
					"idDetalle",
					"id_venta",
					"codigoComic"
			);
			for (Object[] o : a) {
				d = new Detalle(
						(int) o[0],
						Comic.get((int) o[2]),
						Venta.get((int) o[1])
				);
			}
		} catch (SinBaseDatosException ex) {
			Logger.getLogger(DetalleDAL.class.getName()).log(Level.SEVERE, null, ex);
		}
		return d;
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
