/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignacio.tienda.DAL;

import com.ignacio.tienda.DAL.exception.CompraNoExisteException;
import com.ignacio.tienda.DAL.exception.ClienteNoExisteException;
import com.ignacio.tienda.BLL.Cliente;
import com.ignacio.tienda.DAL.exception.CodigoRepetidoException;
import com.ignacio.tienda.DAL.exception.SinBaseDatosException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ignacio
 */
public class ClienteDAL {

	public static Cliente get(int rut) throws ClienteNoExisteException, SinBaseDatosException {
		Cliente c = null;
		BD bd = new BD();
		ArrayList<Object[]> a = bd.select("cliente",
				"rut=" + rut, "rut", "nombre");
		if (a != null && a.size() > 0) {
			Object[] o = a.get(0);
			c = new Cliente(
					(int) o[0],
					(String) o[1]);
		}
		bd.close();
		if (c == null) {
			throw new ClienteNoExisteException();
		}
		return c;
	}

	public static ArrayList<Cliente> getAll() throws SinBaseDatosException {
		ArrayList<Cliente> a = null;
		BD bd;
		try {
			bd = new BD();
			a = new ArrayList<>();
			ResultSet r = bd.createStatement().executeQuery(
					"select rut, nombre from cliente");
			while (r.next()) {
				a.add(new Cliente(
						r.getInt("rut"),
						r.getString("nombre")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(ClienteDAL.class.getName()).log(Level.SEVERE, null, ex);
		}
		return a;
	}

	public static String findMejorCliente() throws SinBaseDatosException {
		String nombre = null;
		BD bd;
		try {
			bd = new BD();
			String consulta = "select c.nombre, count(c.nombre) "
					+ "from detalle "
					+ "join venta as v on detalle.id_venta=v.idVenta "
					+ "join cliente as c on v.rut=c.rut "
					+ "group by c.nombre "
					+ "order by count(c.nombre) DESC limit 1";
			ResultSet r = bd.createStatement().executeQuery(consulta);
			r.next();
			nombre = r.getString(1);
		} catch (SQLException ex) {
			Logger.getLogger(ClienteDAL.class.getName()).log(Level.SEVERE, null, ex);
		}
		return nombre;
	}

	public static Cliente findPorCompra(int codigo) throws CompraNoExisteException, SinBaseDatosException {
		Cliente c = null;
		BD bd = null;
		try {
			bd = new BD();
			StringBuilder sb = new StringBuilder();
			sb.append("select c.rut,c.nombre from cliente as c").
					append(" join venta as v on c.rut=v.rut ").
					append("where v.idVenta=").
					append(codigo);
			ResultSet r = bd.createStatement().executeQuery(sb.toString());
			if (r.next()) {
				c = new Cliente(r.getInt("rut"), r.getString("nombre"));
			}
			r.close();
		} catch (SQLException ex) {
			Logger.getLogger(ClienteDAL.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (bd != null) {
				bd.close();
			}
		}
		if (c == null) {
			throw new CompraNoExisteException(
					"No hay un cliente para la compra " + codigo);
		}
		return c;
	}

	public boolean guardar(int rut, String nombre) throws CodigoRepetidoException, SinBaseDatosException {
		boolean salida = false;
		BD bd = null;
		try {
			bd = new BD();
			salida = bd.update(
					"insert into cliente (rut, nombre) values (?,?)",
					rut, nombre);
		} finally {
			if (bd != null) {
				bd.close();
			}
		}
		return salida;
	}

	public boolean actualizar(int rut, String nombre, int rutPrevio) throws SinBaseDatosException {
		boolean salida = false;
		BD bd = null;
		try {
			bd = new BD();
			salida = bd.update(
					"update cliente set rut=?, nombre=? where rut=?",
					rut, nombre, rutPrevio);
		} catch (CodigoRepetidoException ex) {
			Logger.getLogger(ComicDAL.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (bd != null) {
				bd.close();
			}
		}
		return salida;
	}

	public boolean borrar(int rut) throws SinBaseDatosException {
		boolean salida = false;
		BD bd = null;
		try {
			bd = new BD();
			salida = bd.update("delete from cliente where rut=?", rut);
		} catch (CodigoRepetidoException ex) {
			Logger.getLogger(ComicDAL.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (bd != null) {
				bd.close();
			}
		}
		return salida;
	}

}
