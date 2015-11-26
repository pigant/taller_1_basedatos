package com.ignacio.tienda.DAL;

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
public class VentaDAL {

	public static Venta get(int id) throws SinBaseDatosException {
		throw new UnsupportedOperationException("Aun no implementado");
	}

	public static ArrayList<Integer> findCodigosVenta() throws SinBaseDatosException {
		ArrayList<Integer> a;
		BD bd = new BD();
		a = new ArrayList<>();
		ArrayList<Object[]> codigos = bd.select("venta",
				"true order by idVenta DESC", "idVenta");
		for (Object[] c : codigos) {
			a.add((Integer) c[0]);
		}
		bd.close();
		return a;
	}

	public boolean guardar(int idVenta, int rut) throws CodigoRepetidoException, SinBaseDatosException {
		boolean i = false;
		BD bd;
		bd = new BD();
		i = bd.update(
				"insert into venta (idVenta, rut) values (?,?)",
				idVenta, rut);
		return i;
	}

	public boolean actualizar(int rut, int idVenta) throws SinBaseDatosException {
		boolean s = false;
		BD bd;
		try {
			bd = new BD();
			bd.update("update venta set rut=? where idVenta=?", rut, idVenta);
		} catch (CodigoRepetidoException ex) {
			Logger.getLogger(VentaDAL.class.getName()).log(Level.SEVERE, null, ex);
		}
		return s;
	}

	public boolean borrar(Integer idVenta) throws SinBaseDatosException {
		boolean s = false;
		BD bd = new BD();
		try {
			bd.update("delete from venta where idVenta=?", idVenta);
		} catch (CodigoRepetidoException ex) {
			//No aplica este error en este caso
		}
		return s;
	}
}
