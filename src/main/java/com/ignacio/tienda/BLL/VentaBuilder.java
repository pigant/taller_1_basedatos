/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignacio.tienda.BLL;

import com.ignacio.tienda.DAL.BD;
import com.ignacio.tienda.DAL.ClienteNoExisteException;
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
public class VentaBuilder {

	public static boolean crear(final int idVenta,
		final int rut,
		final ArrayList<Comic> comics) throws ClienteNoExisteException, CodigoRepetidoException {
		//
		boolean salida = false;
		Venta v = new Venta(idVenta, Cliente.get(rut));
		for (Comic c : comics) {
			v.addDetalle(new Detalle(c));
		}
		salida = v.guardar();
		return salida;
	}

	private Cliente c;
	private ArrayList<Detalle> ld = new ArrayList();

	public VentaBuilder addDetalle(Detalle d) {
		return this;
	}

	public VentaBuilder setCliente(Cliente c) {
		this.c = c;
		return this;
	}

	public boolean guardar() throws CodigoRepetidoException {
		Venta v = new Venta();
		v.setCliente(c);
		boolean x = v.guardar();
		if (x) {
			for (Detalle d : ld) {
				v.addDetalle(d);
				d.guardar();
			}
		}
		return false;
	}

	public static Venta getVenta(int codigo) {
		BD bd;
		ResultSet r;
		Cliente c;
		Venta v = null;
		try {
			bd = new BD();
			//Cliente
			StringBuilder sb = new StringBuilder();
			sb.append("select c.rut,c.nombre from cliente as c").
				append(" join venta as v on c.rut=v.rut ").
				append("where v.idVenta=").
				append(codigo);
			r = bd.createStatement().executeQuery(sb.toString());
			r.next();
			c = new Cliente(r.getInt("rut"), r.getString("nombre"));
			r.close();
			//detalles
			sb = new StringBuilder();
			sb.append("select d.idDetalle, d.codigoComic, c.codigo, c.nombre, c.numero from detalle as d").
				append(" join comic as c on d.codigoComic=c.codigo ").
				append("where d.id_venta=").
				append(codigo);
			r = bd.createStatement().executeQuery(sb.toString());
			//Se crea la venta despues de haber realizado las consultas,
			//por mejoror el rendimiento
			v = new Venta();
			while (r.next()) {
				int id = r.getInt("idDetalle");
				int codigoComic = r.getInt("codigoComic");
				int c_codigoComic = r.getInt("codigo");
				String c_nombre = r.getString("nombre");
				int c_numero = r.getInt("numero");
				v.addDetalle(new Detalle(new Comic(c_codigoComic, c_nombre, c_numero), v));
			}
			r.close();
			v.setCliente(c);

		} catch (SinBaseDatosException ex) {
			Logger.getLogger(VentaBuilder.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(VentaBuilder.class.getName()).log(Level.SEVERE, null, ex);
		}
		return v;
	}

}
